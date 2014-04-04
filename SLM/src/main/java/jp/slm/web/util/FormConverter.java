package jp.slm.web.util;

import java.util.Date;

import jp.slm.business.bean.Artist;
import jp.slm.business.bean.Fan;
import jp.slm.business.bean.User;
import jp.slm.web.form.ArtistRegistrationForm;
import jp.slm.web.form.FanRegistrationForm;
import jp.slm.web.form.UserRegistrationForm;

public class FormConverter {
	
	public static User toUser(UserRegistrationForm urf) {
		User user = new User();
		user.setEmail(urf.getEmail());
		user.setUncryptedPassword(urf.getPassword());
		user.setCreationDate(new Date());
		user.setFirstName(urf.getFirstName());
		user.setLastName(urf.getLastName());
		user.setGender(urf.getGender());
		user.setBirthdate(urf.getBirthdate());
		user.setNickname(urf.getNickname());
		user.setDescription(urf.getDescription());
		user.setWebsiteUrl(urf.getWebsiteUrl());
		return user;
	}
	
	public static Fan toFan(FanRegistrationForm frf) {
		Fan fan = new Fan();
		fan.setUser(toUser(frf));
		return fan;
	}
	
	public static Artist toArtist(ArtistRegistrationForm arf) {
		Artist artist = new Artist();
		artist.setUser(toUser(arf));
		artist.setYoutube(arf.getYoutube());
		artist.setVimeo(arf.getVimeo());
		artist.setGroupPhilos(arf.getGroupPhylos());
		return artist;
	}
	
}
