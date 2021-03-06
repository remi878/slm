package jp.slm.business.service.impl;

import jp.slm.business.bean.Artist;
import jp.slm.business.bean.Fan;
import jp.slm.business.bean.User;
import jp.slm.business.dao.ArtistDao;
import jp.slm.business.dao.FanDao;
import jp.slm.business.dao.UserDao;
import jp.slm.business.service.UserService;
import jp.slm.business.service.generic.impl.GenericLongIdBeanServiceImpl;
import jp.slm.business.util.MailConfirmationToken;
import jp.slm.business.util.PasswordValidator;
import jp.slm.business.util.ResetPasswordToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@SuppressWarnings("serial")
@Service
@Transactional
public class UserServiceImpl extends GenericLongIdBeanServiceImpl<User> implements UserService {
	
	private static final String USER_ID = "user.id";
	
	private static final String EMAIL = "email";
	
	@Autowired
	private PasswordEncoder pwdEncoder;
	
	@Autowired
	private ArtistDao artistDao;
	
	@Autowired
	private FanDao fanDao;
	
	@Autowired
	private UserDao dao;
	
	@Override
	public UserDao getDao() {
		return dao;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserDetails ud = null;
		if (StringUtils.hasText(email)) {
			dao.clearClassCache();
			User user = dao.findByProperty(EMAIL, email);
			if (user != null) {
				ud = user;
				if (!user.isAdmin()) {
					Artist a = artistDao.findByProperty(USER_ID, user.getId());
					if (a != null) {
						ud = a;
					} else {
						Fan fan = fanDao.findByProperty(USER_ID, user.getId());
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
	
	@Override
	public void createUser(UserDetails user) {
		if (isAllowedToManage(null)) {
			
		}
	}
	
	@Override
	public void updateUser(UserDetails user) {
		if (isAllowedToManage(user.getUsername()) && user instanceof User) {
			dao.update((User) user);
		}
	}
	
	@Override
	public void deleteUser(String email) {
		if (isAllowedToManage(email)) {
			dao.deleteByProperty(EMAIL, email);
		} else {
			
			if (email != null && getCurrentUser() != null) {
				throw new AccessDeniedException(getCurrentUser().getUsername() + " try to delete " + email);
			} else if (email != null) {
				throw new AccessDeniedException("Unknow user try to delete " + email);
			} else if (getCurrentUser() != null) {
				throw new AccessDeniedException(getCurrentUser().getUsername() + " try to delete null");
			} else {
				throw new AccessDeniedException("Unknow user try to delete null");
			}
		}
	}
	
	@Override
	public void changePassword(String oldPassword, String newPassword) {
		User currentUser = getCurrentUser();
		if (pwdEncoder.matches(oldPassword, currentUser.getPassword()) && PasswordValidator.validate(newPassword)) {
			currentUser.setPassword(pwdEncoder.encode(newPassword));
			dao.update(currentUser);
		} else {
			if (!pwdEncoder.matches(oldPassword, currentUser.getPassword())) {
				// sleep to prevent to fast failure test (harden brute force attack...)
				try {
					Thread.sleep(5000);
				} catch (InterruptedException ignore) {}
			}
			throw new BadCredentialsException("old or new Password invalid");
		}
	}
	
	private boolean isResetPasswordTokenValid(ResetPasswordToken resetPasswordToken) {
		boolean res = false;
		if (resetPasswordToken != null && resetPasswordToken.isValid()) {
			User user = dao.findById(resetPasswordToken.getUserId());
			if (user.isEnabled() && user.getAuthToken() != null && user.getAuthToken().equals(resetPasswordToken.getUserAuthToken())) {
				res = true;
			}
		}
		return res;
	}
	
	public boolean resetPassword(String resetToken, String newPassword) {
		boolean res = false;
		ResetPasswordToken resetPasswordToken = ResetPasswordToken.readToken(resetToken);
		if (isResetPasswordTokenValid(resetPasswordToken) && PasswordValidator.validate(newPassword)) {
			User user = dao.findById(resetPasswordToken.getUserId());
			user.setUncryptedPassword(newPassword);
			dao.update(user);
			res = true;
		}
		return res;
	}
	
	@Override
	public boolean userExists(String email) {
		return dao.existByProperty(EMAIL, email);
	}
	
	public UserDetails getCurrentUserDetails() {
		UserDetails res = null;
		SecurityContext securityContext = SecurityContextHolder.getContext();
		if (securityContext != null) {
			Authentication auth = securityContext.getAuthentication();
			if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
				Object obj = auth.getPrincipal();
				if ((obj != null) && obj instanceof UserDetails) {
					res = (UserDetails) obj;
				}
			}
		}
		return res;
	}
	
	public User getCurrentUser() {
		User res = null;
		UserDetails ud = getCurrentUserDetails();
		if (ud != null) {
			if (ud instanceof User) {
				res = (User) ud;
			} else if (ud instanceof Artist) {
				res = ((Artist) ud).getUser();
			} else if (ud instanceof Fan) {
				res = ((Fan) ud).getUser();
			}
		}
		return res;
	}
	
	private boolean isAllowedToManage(String email) {
		boolean res = false;
		User currentUser = getCurrentUser();
		if (currentUser != null && currentUser.isEnabled() && currentUser.isAccountNonExpired() && currentUser.isAccountNonLocked()) {
			res = currentUser.isAdmin() || (currentUser.getUsername().equals(email) && email != null);
		}
		return res;
	}
	
	@Override
	public Fan signUpFan(Fan fan) {
		fanDao.create(fan);
		userSignup(fan.getUser());
		return fan;
	}
	
	@Override
	public Artist signUpArtist(Artist artist) {
		artistDao.create(artist);
		userSignup(artist.getUser());
		return artist;
	}
	
	private void userSignup(User user) {
		MailConfirmationToken mailConfirmationToken = MailConfirmationToken.getNewToken(user.getId());
		user.setAuthToken(mailConfirmationToken.getUserAuthToken());
		dao.update(user);
		// TODO mail ...
		
	}
	
	public void lostPassword(User user) {
		ResetPasswordToken resetPasswordToken = ResetPasswordToken.getNewToken(user.getId());
		user.setAuthToken(resetPasswordToken.getUserAuthToken());
		dao.update(user);
		// TODO handle lost password (mail with link to reset password page with resetPasswordToken.toString() in param)
	}
}
