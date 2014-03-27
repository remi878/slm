/* root connection needed : mysql -u root (-p pwd) */

CREATE USER user `slm` identified by `slm`;

grant usage on *.* to slm@localhost identified by `slm`;
grant usage on *.* to slm@* identified by `slm`;

DROP DATABASE `slm`;
CREATE DATABASE `slm` /*!40100 DEFAULT CHARACTER SET utf8 */;

grant all privileges on slm.* to slm@localhost ;
grant all privileges on slm.* to slm@* ;

/*
DROP DATABASE `slm_auto`;
CREATE DATABASE `slm_auto` /*!40100 DEFAULT CHARACTER SET utf8 */;

grant all privileges on slm_auto.* to slm@localhost ;
grant all privileges on slm_auto.* to slm@* ; 
*/