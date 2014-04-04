package jp.slm.web.controller.generic;

import java.security.Principal;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

public abstract class GenericController {
	
	/* KEY WORDS */
	
	protected static final String EMPTY = StringUtils.EMPTY;
	
	protected static final String DOT = ".";
	
	protected static final String PAGE_SEP = "-";
	
	protected static final String URL_SEP = "/";
	
	protected static final String HOME_STR = "home";
	
	protected static final String INDEX_STR = "index";
	
	protected static final String ID = "id";
	
	protected static final String FAN = "fan";
	
	protected static final String ARTIST = "artist";
	
	protected static final String USER_STR = "user";
	
	protected static final String ADMIN = "admin";
	
	protected static final String SUCESS = "sucess";
	
	protected static final String PROFILE_STR = "profile";
	
	protected static final String EDIT_STR = "edit";
	
	protected static final String SAVE_STR = "save";
	
	protected static final String SIGNUP_STR = "signup";
	
	protected static final String FORM_STR = "form";
	
	protected static final String LOGIN_STR = "login";
	
	protected static final String PASSWORD_STR = "password";
	
	protected static final String AVATAR_STR = "avatar";
	
	protected static final String EMAIL = "email";
	
	protected static final String FILE = "file";
	
	protected static final String REDIRECT = UrlBasedViewResolver.REDIRECT_URL_PREFIX;
	
	protected static final String FORWARD = UrlBasedViewResolver.FORWARD_URL_PREFIX;
	
	private static final String SPRING_LOGIN_URL_TOKEN = "j_spring_security_check";
	
	private static final String SPRING_LOGOUT_URL_TOKEN = "j_spring_security_check";
	
	protected static final String LOGIN_USERNAME = UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;
	
	protected static final String LOGIN_PASSWOR = UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY;
	
	protected static final String URL_WILDCARD = "*";
	
	protected static final String ERROR = "error";
	
	protected static final String TILES_PREFIX = DOT;
	
	protected static final String MESSAGE = "MESSAGE";
	
	protected static final String DONE = "done";
	
	protected static final String UPDATE_DONE = "update.done";
	
	protected static final String CREATION_DONE = "creation.done";
	
	/* Coposite Key words */
	
	protected static final String USER_ID = USER_STR + DOT + ID;
	
	protected static final String USER_PREFIX = USER_STR + PAGE_SEP;
	
	protected static final String FAN_PREFIX = FAN + PAGE_SEP;
	
	protected static final String ARTIST_PREFIX = ARTIST + PAGE_SEP;
	
	protected static final String ADMIN_PREFIX = ADMIN + PAGE_SEP;
	
	protected static final String LOGIN_FORM = LOGIN_STR + PAGE_SEP + FORM_STR;
	
	protected static final String SIGNUP_FORM = SIGNUP_STR + PAGE_SEP + FORM_STR;
	
	protected static final String SIGNUP_SUCESS = SIGNUP_STR + PAGE_SEP + SUCESS;
	
	protected static final String PROFILE_EXT_URL = URL_SEP + PROFILE_STR;
	
	protected static final String EDIT_PROFILE = EDIT_STR + PAGE_SEP + PROFILE_STR;
	
	protected static final String SAVE_PROFILE = SAVE_STR + PAGE_SEP + PROFILE_STR;
	
	protected static final String EDIT_PROFILE_EXT_URL = URL_SEP + EDIT_PROFILE;
	
	protected static final String SAVE_PROFILE_EXT_URL = URL_SEP + SAVE_PROFILE;
	
	protected static final String LOST_PASSWORD_STR = "lost-" + PASSWORD_STR;
	
	protected static final String CHANGE_PASSWORD_STR = "change-" + PASSWORD_STR;
	
	protected static final String CHANGE_AVATAR_STR = "change-" + AVATAR_STR;
	
	protected static final String USER_UPDATE_DONE = USER_STR + DOT + UPDATE_DONE;
	
	protected static final String PASSWORD_UPDATE_DONE = PASSWORD_STR + DOT + UPDATE_DONE;
	
	protected static final String AVATAR_UPDATE_DONE = AVATAR_STR + DOT + UPDATE_DONE;
	
	protected static final String FAN_UPDATE_DONE = FAN + DOT + UPDATE_DONE;
	
	protected static final String ARTIST_UPDATE_DONE = ARTIST + DOT + UPDATE_DONE;
	
	/* Contexts URLs */
	
	public static final String FAN_CONTEXT_URL = URL_SEP + FAN + URL_SEP;
	
	public static final String ARTIST_CONTEXT_URL = URL_SEP + ARTIST;
	
	public static final String USER_CONTEXT_URL = URL_SEP + USER_STR;
	
	public static final String ADMIN_CONTEXT_URL = URL_SEP + ADMIN;
	
	/* URLs */
	
	public static final String HOME_URL = URL_SEP;
	
	public static final String LOGIN_URL = URL_SEP + LOGIN_STR;
	
	public static final String LOST_PASSWORD_URL = URL_SEP + LOST_PASSWORD_STR;
	
	public static final String CHANGE_AVATAR_URL = USER_CONTEXT_URL + URL_SEP + CHANGE_AVATAR_STR;
	
	public static final String CHANGE_PASSWORD_URL = USER_CONTEXT_URL + URL_SEP + CHANGE_PASSWORD_STR;
	
	public static final String LOGIN_PROCESS_URL = URL_SEP + SPRING_LOGIN_URL_TOKEN;
	
	public static final String LOGOUT_PROCESS_URL = URL_SEP + SPRING_LOGOUT_URL_TOKEN;
	
	public static final String SIGNUP_URL = URL_SEP + SIGNUP_STR;
	
	public static final String ARTIST_SIGNUP_URL = URL_SEP + ARTIST_PREFIX + SIGNUP_STR;
	
	public static final String FAN_SIGNUP_URL = URL_SEP + FAN_PREFIX + SIGNUP_STR;
	
	public static final String SIGNUP_SUCESS_URL = URL_SEP + SIGNUP_SUCESS;
	
	public static final String USER_PROFILE_URL = USER_CONTEXT_URL + PROFILE_EXT_URL;
	
	public static final String FAN_PROFILE_URL = FAN_CONTEXT_URL + PROFILE_EXT_URL;
	
	public static final String ARTIST_PROFILE_URL = ARTIST_CONTEXT_URL + PROFILE_EXT_URL;
	
	public static final String USER_EDIT_PROFILE_URL = USER_CONTEXT_URL + EDIT_PROFILE_EXT_URL;
	
	public static final String FAN_EDIT_PROFILE_URL = FAN_CONTEXT_URL + EDIT_PROFILE_EXT_URL;
	
	public static final String ARTIST_EDIT_PROFILE_URL = ARTIST_CONTEXT_URL + EDIT_PROFILE_EXT_URL;
	
	public static final String USER_SAVE_PROFILE_URL = USER_CONTEXT_URL + SAVE_PROFILE_EXT_URL;
	
	public static final String FAN_SAVE_PROFILE_URL = FAN_CONTEXT_URL + SAVE_PROFILE_EXT_URL;
	
	public static final String ARTIST_SAVE_PROFILE_URL = ARTIST_CONTEXT_URL + SAVE_PROFILE_EXT_URL;
	
	/* Pages */
	
	protected static final String HOME_PAGE = TILES_PREFIX + HOME_STR;
	
	protected static final String LOGIN_FORM_PAGE = TILES_PREFIX + LOGIN_FORM;
	
	protected static final String LOST_PASSWORD_PAGE = TILES_PREFIX + LOST_PASSWORD_STR;
	
	protected static final String CHANGE_PASSWORD_PAGE = TILES_PREFIX + CHANGE_PASSWORD_STR;
	
	protected static final String CHANGE_AVATAR_PAGE = TILES_PREFIX + CHANGE_AVATAR_STR;
	
	protected static final String SIGNUP_PAGE = TILES_PREFIX + SIGNUP_STR;
	
	protected static final String FAN_SIGNUP_PAGE = TILES_PREFIX + FAN_PREFIX + SIGNUP_FORM;
	
	protected static final String ARTIST_SIGNUP_PAGE = TILES_PREFIX + ARTIST_PREFIX + SIGNUP_FORM;
	
	protected static final String SIGNUP_SUCESS_PAGE = TILES_PREFIX + SIGNUP_SUCESS;
	
	protected static final String USER_PROFILE_PAGE = TILES_PREFIX + USER_PREFIX + PROFILE_STR;
	
	protected static final String FAN_PROFILE_PAGE = TILES_PREFIX + FAN_PREFIX + PROFILE_STR;
	
	protected static final String ARTIST_PROFILE_PAGE = TILES_PREFIX + ARTIST_PREFIX + PROFILE_STR;
	
	protected static final String USER_EDIT_PROFILE_PAGE = TILES_PREFIX + USER_PREFIX + EDIT_PROFILE;
	
	protected static final String FAN_EDIT_PROFILE_PAGE = TILES_PREFIX + FAN_PREFIX + EDIT_PROFILE;
	
	protected static final String ARTIST_EDIT_PROFILE_PAGE = TILES_PREFIX + ARTIST_PREFIX + EDIT_PROFILE;
	
	protected static final String ERROR_PAGE = TILES_PREFIX + ERROR;
	
	protected final Logger LOG = LoggerFactory.getLogger(getClass());
	
	protected UserDetails getUserDetails(Principal principal) {
		UserDetails userDetails = null;
		if (principal != null && principal instanceof Authentication && ((Authentication) principal).getPrincipal() != null
				&& ((Authentication) principal).getPrincipal() instanceof UserDetails) {
			userDetails = (UserDetails) ((Authentication) principal).getPrincipal();
		}
		return userDetails;
	}
	
	protected UserDetails getUserDetails() {
		UserDetails userDetails = null;
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			Object d = SecurityContextHolder.getContext().getAuthentication().getDetails();
			if (d != null && d instanceof UserDetails) {
				userDetails = (UserDetails) d;
			}
		}
		return userDetails;
	}
	
	protected void refreshUserPrincipal(UserDetails user) {
		if (getUserDetails() == null && getUserDetails().getUsername().equals(user.getUsername())) {
			Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
		} else {
			LOG.error("Not the same user !");
		}
	}
	
	protected String handleAuthenticationError() {
		String view = ERROR_PAGE;
		return view;
	}
}
