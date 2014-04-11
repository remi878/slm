package jp.slm.web.controller;

import java.util.Date;

import javax.validation.Valid;

import jp.slm.business.bean.Artist;
import jp.slm.business.bean.Avatar;
import jp.slm.business.bean.Fan;
import jp.slm.business.bean.User;
import jp.slm.business.service.AvatarService;
import jp.slm.business.service.UserService;
import jp.slm.common.util.PasswordEncoderHolder;
import jp.slm.web.controller.generic.GenericController;
import jp.slm.web.form.ArtistRegistrationForm;
import jp.slm.web.form.FanRegistrationForm;
import jp.slm.web.form.NewPasswordForm;
import jp.slm.web.form.UserRegistrationForm;
import jp.slm.web.util.AvatarUtil;
import jp.slm.web.util.FormConverter;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController extends GenericController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AvatarService avatarService;
	
	@RequestMapping(method = RequestMethod.GET, value = LOST_PASSWORD_URL + URL_WILDCARD)
	@Secured(User.ANONYMOUS_ROLE_STR)
	public String lostPasswordForm(Model model) {
		model.addAttribute(EMAIL, null);
		return LOST_PASSWORD_PAGE;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = LOST_PASSWORD_URL + URL_WILDCARD)
	@Secured(User.ANONYMOUS_ROLE_STR)
	public String lostPassword(@RequestParam(EMAIL) @NotBlank @Valid String email, BindingResult result, Model model) {
		String view = LOST_PASSWORD_PAGE;
		if (!result.hasErrors()) {
			if (StringUtils.isNotBlank(email) && userService.userExists(email)) {
				User user = (User) userService.loadUserByUsername(email);
				if (user.isEnabled()) {
					userService.lostPassword(user);
					view = REDIRECT+LOST_PASSWORD_URL;
				} else {
					result.addError(new ObjectError(EMAIL, "error.account.disabled"));
				}
			} else {
				result.addError(new ObjectError(EMAIL, "error.unknown.user"));
			}
		}
		return view;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = FAN_SIGNUP_URL + URL_WILDCARD)
	@Secured(User.ANONYMOUS_ROLE_STR)
	public String fanSignupForm(Model model) {
		model.addAttribute(USER_STR, new FanRegistrationForm());
		return FAN_SIGNUP_PAGE;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = ARTIST_SIGNUP_URL + URL_WILDCARD)
	@Secured(User.ANONYMOUS_ROLE_STR)
	public String artistSignupForm(Model model) {
		model.addAttribute(USER_STR, new ArtistRegistrationForm());
		return ARTIST_SIGNUP_PAGE;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = FAN_SIGNUP_URL + URL_WILDCARD)
	@Secured(User.ANONYMOUS_ROLE_STR)
	public String fanSignup(@Valid @ModelAttribute(USER_STR) FanRegistrationForm fanForm, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		String view = FAN_SIGNUP_PAGE;
		if (!result.hasErrors() && validateSignUp(fanForm, result)) {
			Fan fan = userService.signUpFan(FormConverter.toFan(fanForm));
			if (fan == null) {
				// LOG.error("signUpFan error [fan="+fanForm+"]");
				result.addError(new ObjectError(USER_STR, "Error : sign-up process error, re-try later !"));
			} else {
				// model.addAttribute("fan", fan);
				redirectAttributes.addFlashAttribute(USER_ID, fan.getUser().getId());
				view = REDIRECT + SIGNUP_SUCESS;
			}
		} else {
			fanForm.setCaptchaResponse(null);
		}
		return view;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = ARTIST_SIGNUP_URL + URL_WILDCARD)
	@Secured(User.ANONYMOUS_ROLE_STR)
	public String artistSignup(@Valid @ModelAttribute(USER_STR) ArtistRegistrationForm artistForm, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		String view = ARTIST_SIGNUP_PAGE;
		if (!result.hasErrors() && validateSignUp(artistForm, result)) {
			Artist artist = userService.signUpArtist(FormConverter.toArtist(artistForm));
			if (artist == null) {
				// LOG.error("signUpartist error [artist="+artistForm+"]");
				result.addError(new ObjectError(USER_STR, "{sign-up.error}"));
			} else {
				// model.addAttribute("artist", artist);
				redirectAttributes.addFlashAttribute(USER_ID, artist.getUser().getId());
				view = REDIRECT + SIGNUP_SUCESS;
				
			}
		} else {
			artistForm.setCaptchaResponse(null);
		}
		return view;
	}
	
	/**
	 * For specific server-side validations
	 */
	private boolean validateSignUp(UserRegistrationForm user, BindingResult result) {
		boolean valid = true;
		if (userService.userExists(user.getEmail())) {
			result.rejectValue(EMAIL, "user.already.exists", "Email already used");
			valid = false;
		}
		// TODO : add specific server-side validations here
		return valid;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = SIGNUP_SUCESS_URL + URL_WILDCARD)
	@Secured(User.ANONYMOUS_ROLE_STR)
	public String signupSucess(Model model) {
		String view = SIGNUP_SUCESS_PAGE;
		User user = null;
		Long userId = (Long) model.asMap().get(USER_ID);
		if (userId != null) {
			user = userService.findById(userId);
		}
		if (user != null) {
			model.addAttribute(USER_STR, user);
		} else {
			view = REDIRECT + HOME_URL;
		}
		return view;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = USER_PROFILE_URL + URL_WILDCARD)
	@Secured({ User.ADMIN_ROLE_STR, User.ARTIST_ROLE_STR, User.FAN_ROLE_STR })
	public String userProfile(Model model) {
		String view = USER_PROFILE_PAGE;
		UserDetails userDetails = getUserDetails();
		if (userDetails != null) {
			if (userDetails instanceof User) {
				// refresh user
				User user = userService.findById(((User) userDetails).getId());
				refreshUserPrincipal(user);
				model.addAttribute(USER_STR, user);
				view = USER_PROFILE_PAGE;
			} else if (userDetails instanceof Artist) {
				view = REDIRECT + ARTIST_PROFILE_URL;
			} else if (userDetails instanceof Fan) {
				view = REDIRECT + FAN_PROFILE_URL;
			} else {
				view = handleAuthenticationError();
			}
		} else {
			view = handleAuthenticationError();
		}
		return view;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = USER_EDIT_PROFILE_URL + URL_WILDCARD)
	@Secured({ User.ADMIN_ROLE_STR, User.ARTIST_ROLE_STR, User.FAN_ROLE_STR })
	public String editProfile(Model model) {
		String view = USER_EDIT_PROFILE_PAGE;
		UserDetails userDetails = getUserDetails();
		if (userDetails != null) {
			if (userDetails instanceof User) {
				// refresh user
				User user = userService.findById(((User) userDetails).getId());
				refreshUserPrincipal(user);
				model.addAttribute(USER_STR, user);
			} else if (userDetails instanceof Artist) {
				view = REDIRECT + ARTIST_PROFILE_URL;
			} else if (userDetails instanceof Fan) {
				view = REDIRECT + FAN_PROFILE_URL;
			} else {
				view = handleAuthenticationError();
			}
		} else {
			view = handleAuthenticationError();
		}
		return view;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = SAVE_PROFILE_EXT_URL + URL_WILDCARD)
	@Secured({ User.ADMIN_ROLE_STR, User.ARTIST_ROLE_STR, User.FAN_ROLE_STR })
	public String saveProfile(@Valid @ModelAttribute(USER_STR) User userForm, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		String view = USER_EDIT_PROFILE_PAGE;
		UserDetails userDetails = getUserDetails();
		if (!result.hasErrors() && userDetails != null && userDetails instanceof User) {
			User user = userService.findById(((User) userDetails).getId());
			if (!user.getEmail().equals(userForm.getEmail()) && userService.userExists(userForm.getEmail())) {
				result.rejectValue(EMAIL, "user.already.exists", "Email already used");
			} else {
				user.mergeWithForm(userForm);
				userService.update(user);
				refreshUserPrincipal(user);
				redirectAttributes.addAttribute(MESSAGE, USER_UPDATE_DONE);
				view = REDIRECT + USER_PROFILE_URL;
			}
		} else {
			view = handleAuthenticationError();
		}
		return view;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = CHANGE_PASSWORD_URL + URL_WILDCARD)
	@Secured({ User.ADMIN_ROLE_STR, User.ARTIST_ROLE_STR, User.FAN_ROLE_STR })
	public String changePasswordForm(Model model) {
		model.addAttribute(USER_STR, new NewPasswordForm());
		return CHANGE_PASSWORD_PAGE;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = CHANGE_PASSWORD_URL + URL_WILDCARD)
	@Secured({ User.ADMIN_ROLE_STR, User.ARTIST_ROLE_STR, User.FAN_ROLE_STR })
	public String changePassword(@Valid @ModelAttribute(USER_STR) NewPasswordForm passwordForm, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		String view = CHANGE_PASSWORD_PAGE;
		if (!result.hasErrors()) {
			if (getUserDetails() != null && getUserDetails().getPassword() != null
					&& PasswordEncoderHolder.getPasswordEncoder().matches(getUserDetails().getPassword(), passwordForm.getOldPassword())) {
				try {
					userService.changePassword(passwordForm.getOldPassword(), passwordForm.getPassword());
					redirectAttributes.addAttribute(MESSAGE, PASSWORD_UPDATE_DONE);
					view = REDIRECT + USER_PROFILE_URL;
				} catch (BadCredentialsException e) {
					LOG.warn("Bad password in user service's change password method :", e);
					result.rejectValue(PASSWORD_STR, "error.invalid.password", "invalid password");
				}
			} else {
				result.rejectValue("oldPassword", "error.invalid.password", "invalid password");
			}
		}
		return view;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = CHANGE_AVATAR_URL + URL_WILDCARD)
	@Secured({ User.ADMIN_ROLE_STR, User.ARTIST_ROLE_STR, User.FAN_ROLE_STR })
	public String changeAvatarForm(Model model) {
		return CHANGE_AVATAR_PAGE;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = CHANGE_AVATAR_URL + URL_WILDCARD)
	@Secured({ User.ADMIN_ROLE_STR, User.ARTIST_ROLE_STR, User.FAN_ROLE_STR })
	public String changeAvatar(@RequestParam(FILE) MultipartFile file, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		String view = REDIRECT + CHANGE_AVATAR_URL;
		byte[] imageData = null;
		if (file != null && !file.isEmpty() & file.getContentType() != null && file.getContentType().toLowerCase().contains("image/")) {
			imageData = AvatarUtil.getScaledImage(file);
		}
		if (imageData == null) {
			result.rejectValue(FILE, "error.invalid.image", "invalid image");
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.file", result);
			redirectAttributes.addFlashAttribute("file", file);
		} else {
			User user = userService.getCurrentUser();
			Avatar avatar = new Avatar();
			avatar.setAvatarData(imageData);
			avatar.setInDate(new Date());
			avatarService.create(avatar);
			user.setAvatar(avatar);
			userService.update(user);
			redirectAttributes.addAttribute(MESSAGE, AVATAR_UPDATE_DONE);
			view = REDIRECT + USER_PROFILE_URL;
		}
		return view;
	}
}
