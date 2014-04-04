package jp.slm.business.service;

import jp.slm.business.bean.Artist;
import jp.slm.business.bean.Fan;
import jp.slm.business.bean.User;
import jp.slm.business.service.generic.GenericLongIdBeanService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Interface UserService.
 * 
 */
@Transactional
public interface UserService extends GenericLongIdBeanService<User>, UserDetailsManager {
	
	Fan signUpFan(Fan fan);
	
	Artist signUpArtist(Artist artist);
	
	void lostPassword(User user);
	
	User getCurrentUser();
	
	UserDetails getCurrentUserDetails();
	
}
