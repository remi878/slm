package jp.slm.business.service.impl;

import jp.slm.business.bean.Artist;
import jp.slm.business.bean.Fan;
import jp.slm.business.bean.User;
import jp.slm.business.service.ArtistService;
import jp.slm.business.service.FanService;
import jp.slm.business.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional(readOnly = true)
public class SlmUserDetailsService implements UserDetailsService {
	
	private static final String USER_ID = "user.id";
	
	private static final String EMAIL = "email";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ArtistService artistService;
	
	@Autowired
	private FanService fanService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserDetails ud = null;
		if (StringUtils.hasText(email)) {
			User user = userService.findByProperty(EMAIL, email);
			if (user != null) {
				ud = user;
				if (!user.isAdmin()) {
					Artist a = artistService.findByProperty(USER_ID, user.getId());
					if (a != null) {
						ud = a;
					} else {
						Fan fan = fanService.findByProperty(USER_ID, user.getId());
						if (fan != null) {
							ud = fan;
						} else {
							if (user.getId() != null) {
								throw new UsernameNotFoundException("User [id=" + user.getId() + ", email=" + email + "] not link to an Artist nor a Fan and not an admin !");
							} else {
								throw new UsernameNotFoundException("User [id null, email=" + email + "] not link to an Artist nor a Fan and not an admin !");
							}
						}
					}
				}
			} else {
				throw new UsernameNotFoundException("User not found [email=" + email + "] !");
			}
		} else {
			throw new UsernameNotFoundException("Email null or empty => User not found !");
		}
		return ud;
	}
}
