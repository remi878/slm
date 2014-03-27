
DROP TABLE IF EXISTS `slm`.`comment`;
DROP TABLE IF EXISTS `slm`.`event_rate`;
DROP TABLE IF EXISTS `slm`.`presence`;

DROP TABLE IF EXISTS `slm`.`mp3`;
DROP TABLE IF EXISTS `slm`.`picture`;
DROP TABLE IF EXISTS `slm`.`bandmember`;

DROP TABLE IF EXISTS `slm`.`event`;

DROP TABLE IF EXISTS `slm`.`artist`;
DROP TABLE IF EXISTS `slm`.`fan`;

DROP TABLE IF EXISTS `slm`.`user`;
DROP TABLE IF EXISTS `slm`.`avatar`;


CREATE TABLE  `slm`.`avatar` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'tech ID',
  
  -- if you want to give some generic avatar sample you have to know if the avatar is deletable on user's avatar change :
  `is_system` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT 'is systeme avatar not deletable on avatar change',

  `in_date` date NOT NULL COMMENT 'tech date when the avatar was upload in here',

  -- avatar upload should be available after user is comfirmed to prevent bad usage (security, storage limitation, ...)
  `avatar_data` blob COMMENT 'avatar picture (small ?) blob(<64ko)/MEDIUMBLOB(<16Mo) or link to fileSystem ?',

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='user avatar table';

CREATE TABLE  `slm`.`user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'tech ID',
  
  `email` varchar(64) NOT NULL,
  `password` varchar(96) NOT NULL COMMENT 'encrypted/hashed password',
  
  -- tech info
  `auth_token` varchar(45) DEFAULT NULL COMMENT 'what is it ? email confirm ? sso : fb, google ? ',
  `pwd_reset` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT 'has reset pwd => need new pwd',
  `is_admin` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT 'is admin',
  `is_enabled` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT 'is account enabled',
  
  `creation_date` date NOT NULL COMMENT 'tech date when the user sign up',
  `last_login` date DEFAULT NULL COMMENT 'tech date of the user''s last login, needed for future purges of dead users ?',
   
   -- personal info
  `first_name` varchar(64) NOT NULL,
  `last_name` varchar(64) NOT NULL,
  `gender` tinyint(1) unsigned DEFAULT NULL COMMENT '0=>F ; 1=>M ; null =>NC // or FK to a gender table',
  `birthdate` date DEFAULT NULL,
  `nickname` varchar(32) DEFAULT NULL COMMENT 'name to display',
  `description` varchar(512) DEFAULT NULL,
  `website_url` varchar(64) DEFAULT NULL COMMENT 'why not ? people like to link other site',
  
  -- avatar FK
  `id_avatar` int(10) unsigned COMMENT 'FK to avatar',
  
  PRIMARY KEY (`id`),
  KEY `FK_USER_AVATAR` (`id_avatar`),
  CONSTRAINT `FK_USER_AVATAR` FOREIGN KEY (`id_avatar`) REFERENCES `avatar` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='registered user table (fan or artist or admin)';

CREATE TABLE `slm`.`artist` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'tech ID',
  `id_user` int(10) unsigned NOT NULL COMMENT 'FK to user mandatory',

  -- artist fields
  `youtube` varchar(128) DEFAULT NULL COMMENT 'link to youtube page ?',
  `wimeo` varchar(128) DEFAULT NULL COMMENT 'link to wimeo page ?',
  `group_phylos` varchar(512) DEFAULT NULL COMMENT 'artist/group desc ?',

  -- nb_view & nb_like ?
  
  -- why not a music genre (like rock or pop , ...) and or somthing like 3 choise of music genre for the artist ?
  -- (with a table music genre and foreign key here)
  
  PRIMARY KEY (`id`),
  KEY `FK_ARTIST_USER` (`id_user`),
  CONSTRAINT `FK_ARTIST_USER` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='specific user table for artist info';

CREATE TABLE  `slm`.`fan` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'tech ID',
  `id_user` int(10) unsigned NOT NULL COMMENT 'FK to user mandatory',
  
  -- fan fields
  -- nothing yet

  PRIMARY KEY (`id`),
  KEY `FK_FAN_USER` (`id_user`),
  CONSTRAINT `FK_FAN_USER` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='specific user table for fan info';

CREATE TABLE  `slm`.`bandmember` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'tech ID',
  `id_artist` int(10) unsigned NOT NULL COMMENT 'FK to artist mandatory',

  `order` int(3) unsigned NOT NULL DEFAULT 0 COMMENT 'order to display group member ?',

  -- member fields
  `name` varchar(64) NOT NULL COMMENT 'name/nickname of the member',
  `gender` tinyint(1) unsigned DEFAULT NULL COMMENT '0=>F ; 1=>M ; null =>NC // or FK to a gender table',
  `birthdate` date DEFAULT NULL COMMENT 'birthdate or now-age to have not static age',
  `role` varchar(64) NOT NULL COMMENT 'role of the member / FK to role table ?',
  -- no need of a picture / avatar of each memeber ?

  PRIMARY KEY (`id`),
  KEY `FK_MEMBER_ARTIST` (`id_artist`),
  CONSTRAINT `FK_MEMBER_ARTIST` FOREIGN KEY (`id_artist`) REFERENCES `artist` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='extra artist info table : list of bandmember';

CREATE TABLE  `slm`.`mp3` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'tech ID',
  `id_artist` int(10) unsigned NOT NULL COMMENT 'FK to artist mandatory',

  `in_date` date NOT NULL COMMENT 'tech date when the mp3 was upload in here',
  -- or/and
  `order` int(3) unsigned NOT NULL DEFAULT 0 COMMENT 'order to display mp3 ?',

  -- mp3 fields
  `comment` varchar(512) NOT NULL COMMENT 'why not a comment of the artist about the mp3 ?',
  `mp3_data` MEDIUMBLOB COMMENT 'MEDIUMBLOB(<16Mo) or link to fileSystem ?',

  PRIMARY KEY (`id`),
  KEY `FK_MP3_ARTIST` (`id_artist`),
  CONSTRAINT `FK_MP3_ARTIST` FOREIGN KEY (`id_artist`) REFERENCES `artist` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='artist submited mp3 table';

CREATE TABLE  `slm`.`picture` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'tech ID',
  `id_artist` int(10) unsigned NOT NULL COMMENT 'FK to artist mandatory',

  `in_date` date NOT NULL COMMENT 'tech date when the picture was upload in here',
  -- or/and
  `order` int(3) unsigned NOT NULL DEFAULT 0 COMMENT 'order to display picture ?',

  -- mp3 fields
  `comment` varchar(512) NOT NULL COMMENT 'why not a comment of the artist about the picture ?',
  `picture_data` MEDIUMBLOB COMMENT 'MEDIUMBLOB(<16Mo) or link to fileSystem ?',

  PRIMARY KEY (`id`),
  KEY `FK_PICTURE_ARTIST` (`id_artist`),
  CONSTRAINT `FK_PICTURE_ARTIST` FOREIGN KEY (`id_artist`) REFERENCES `artist` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='artist submited picture table';

CREATE TABLE  `slm`.`event` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'tech ID',
  `id_artist` int(10) unsigned NOT NULL,
  `title` varchar(45) NOT NULL,
  `description` varchar(512) NOT NULL,
  `date_time` datetime NOT NULL,
  `duration` float DEFAULT NULL,
  `lat` float NOT NULL,
  `long` float NOT NULL,
  `adress` varchar(256) NOT NULL,
  -- nb_view & nb_like ?
  PRIMARY KEY (`id`),
  KEY `FK_EVENT_ARTIST` (`id_artist`),
  CONSTRAINT `FK_EVENT_ARTIST` FOREIGN KEY (`id_artist`) REFERENCES `artist` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='artist''s event table';

CREATE TABLE  `slm`.`presence` (
  `id_user` int(10) unsigned NOT NULL COMMENT 'composite PK : FK to user who is present',
  `id_event` int(10) unsigned NOT NULL COMMENT 'composite PK : FK to event',
  PRIMARY KEY (`id_user`,`id_event`) USING BTREE,
  KEY `FK_PRES_USER` (`id_user`),
  KEY `FK_PRES_EVENT` (`id_event`),
  CONSTRAINT `FK_PRES_USER` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_PRES_EVENT` FOREIGN KEY (`id_event`) REFERENCES `event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='user event presence table';

CREATE TABLE  `slm`.`event_rate` (
  `id_event` int(10) unsigned NOT NULL COMMENT 'composite PK : FK to rated event',
  `id_user` int(10) unsigned NOT NULL COMMENT 'composite PK : FK to user who rate',
  `rate` int(3) unsigned NOT NULL COMMENT 'the rate : 0-10 ?',
  PRIMARY KEY (`id_event`,`id_user`),
  KEY `FK_ER_EVENT` (`id_event`),
  KEY `FK_ER_USER` (`id_user`),
  CONSTRAINT `FK_ER_EVENT` FOREIGN KEY (`id_event`) REFERENCES `event` (`id`),
  CONSTRAINT `FK_ER_USER` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='user event rate table';

CREATE TABLE  `slm`.`artist_rate` (
  `id_artist` int(10) unsigned NOT NULL COMMENT 'composite PK : FK to rated artist',
  `id_user` int(10) unsigned NOT NULL COMMENT 'composite PK : FK to user who rate',
  `rate` int(3) unsigned NOT NULL COMMENT 'the rate : 0-10 ?',
  PRIMARY KEY (`id_artist`,`id_user`),
  KEY `FK_AR_ARTIST` (`id_artist`),
  KEY `FK_AR_USER` (`id_user`),
  CONSTRAINT `FK_AR_ARTIST` FOREIGN KEY (`id_artist`) REFERENCES `artist` (`id`),
  CONSTRAINT `FK_AR_USER` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='user artist rate table';

CREATE TABLE  `slm`.`comment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'tech ID',
  `id_user` int(10) unsigned NOT NULL COMMENT 'FK to user who comment',
  `id_event` int(10) unsigned DEFAULT NULL COMMENT 'possible FK to commented event',
  -- can we comment artist mp3 and picture (and bandMember) also ?
  `id_artist` int(10) unsigned DEFAULT NULL COMMENT 'possible FK to commented artist',
  `id_mp3` int(10) unsigned DEFAULT NULL COMMENT 'possible FK to commented mp3',
  `id_picture` int(10) unsigned DEFAULT NULL COMMENT 'possible FK to commented picture',
  `comment` varchar(512) NOT NULL COMMENT 'the comment',
  `date_in` datetime NOT NULL COMMENT 'date of the comment',
  PRIMARY KEY (`id`),
  KEY `FK_COM_USER` (`id_user`),
  KEY `FK_COM_EVENT` (`id_event`),
  KEY `FK_COM_ARTIST` (`id_artist`),
  KEY `FK_COM_MP3` (`id_mp3`),
  KEY `FK_COM_PICTURE` (`id_picture`),
  CONSTRAINT `FK_COM_USER` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_COM_EVENT` FOREIGN KEY (`id_event`) REFERENCES `event` (`id`),
  CONSTRAINT `FK_COM_ARTIST` FOREIGN KEY (`id_artist`) REFERENCES `artist` (`id`),
  CONSTRAINT `FK_COM_MP3` FOREIGN KEY (`id_mp3`) REFERENCES `mp3` (`id`),
  CONSTRAINT `FK_COM_PICTURE` FOREIGN KEY (`id_picture`) REFERENCES `picture` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='user comment table';

CREATE TABLE  `slm`.`session_log`(
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'tech ID',
  `start_date` date NOT NULL COMMENT 'session start',
  `end_date` date NOT NULL COMMENT 'session end',
  `ip` TEXT NOT NULL COMMENT 'remote ip',
  `referer` TEXT COMMENT 'remote referer',
  `header` TEXT COMMENT 'first http request header',
  `requested_host` TEXT COMMENT 'first http request header',
  `user_ids` TEXT COMMENT 'concat of user ids (who log on with this session)',
  `last_login_attempt` date COMMENT 'last login attempt date',
  `nb_successive_login_attempts` int COMMENT 'nb login attempts',
  `nb_request_pages` int COMMENT 'nb pages',
  `nb_request_ressources` int COMMENT 'nb ressources',
  `server_time` bigint COMMENT 'server time used',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='session log table';
	