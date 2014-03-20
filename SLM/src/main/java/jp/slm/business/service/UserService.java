package jp.slm.business.service;

import jp.slm.business.bean.Artist;
import jp.slm.business.bean.Fan;
import jp.slm.business.bean.User;
import jp.slm.business.service.generic.GenericLongIdBeanService;
import jp.slm.web.form.ArtistRegistrationForm;
import jp.slm.web.form.FanRegistrationForm;

import org.springframework.security.provisioning.UserDetailsManager;

/**
 * The Interface UserService.
 * 
 */
public interface UserService extends GenericLongIdBeanService<User>, UserDetailsManager {
	
	Fan signUpFan(FanRegistrationForm fanForm);
	
	Artist signUpArtist(ArtistRegistrationForm artistForm);
	
}
