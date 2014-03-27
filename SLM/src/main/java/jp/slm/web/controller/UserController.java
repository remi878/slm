package jp.slm.web.controller;

import javax.validation.Valid;

import jp.slm.business.bean.Artist;
import jp.slm.business.bean.Fan;
import jp.slm.business.bean.User;
import jp.slm.business.service.UserService;
import jp.slm.web.controller.generic.GenericController;
import jp.slm.web.form.ArtistRegistrationForm;
import jp.slm.web.form.FanRegistrationForm;
import jp.slm.web.form.UserRegistrationForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController extends GenericController {
	
	public static final String REDIRECT = "redirect:/";
	
	private static final String USER_STR = "user";
	
	private static final String EMAIL = "email";
	
	private static final String SIGNUP_FORM = "-signup-form";
	
	private static final String SIGNUP_SUCESS = "signup-sucess";
	
	private static final String SIGNUP_SUCESS_PAGE = "." + SIGNUP_SUCESS;
	
	private static final String FAN = ".fan";
	
	private static final String ARTIST = ".artist";
	
	private static final String FAN_SIGNUP_PAGE = FAN + SIGNUP_FORM;
	
	private static final String ARTIST_SIGNUP_PAGE = ARTIST + SIGNUP_FORM;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET, value = { "/fan-signup*", "/fan-sign-up*" })
	public String fanSignupForm(Model model) {
		model.addAttribute(USER_STR, new FanRegistrationForm());
		return FAN_SIGNUP_PAGE;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = { "/fan-signup*", "/fan-sign-up*" })
	public String fanSignup(@Valid @ModelAttribute(USER_STR) FanRegistrationForm fanForm, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		String view = FAN_SIGNUP_PAGE;
		if (!result.hasErrors() && validateSignUp(fanForm, result)) {
			Fan fan = userService.signUpFan(fanForm);
			if (fan == null) {
				// LOG.error("signUpFan error [fan="+fanForm+"]");
				result.addError(new ObjectError(USER_STR, "Error : sign-up process error, re-try later !"));
			} else {
				// model.addAttribute("fan", fan);
				redirectAttributes.addFlashAttribute("userId", fan.getUser().getId());
				view = REDIRECT + SIGNUP_SUCESS;
			}
		} else {
			fanForm.setCaptchaResponse(null);
		}
		return view;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = { "/artist-signup*", "/artist-sign-up*" })
	public String artistSignupForm(Model model) {
		model.addAttribute(USER_STR, new ArtistRegistrationForm());
		return ARTIST_SIGNUP_PAGE;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = { "/artist-signup*", "/artist-sign-up*" })
	public String artistSignup(@Valid @ModelAttribute(USER_STR) ArtistRegistrationForm artistForm, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		String view = ARTIST_SIGNUP_PAGE;
		if (!result.hasErrors() && validateSignUp(artistForm, result)) {
			Artist artist = userService.signUpArtist(artistForm);
			if (artist == null) {
				// LOG.error("signUpartist error [artist="+artistForm+"]");
				result.addError(new ObjectError(USER_STR, "{sign-up.error}"));
			} else {
				// model.addAttribute("artist", artist);
				redirectAttributes.addFlashAttribute("userId", artist.getUser().getId());
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
	
	@RequestMapping(value = { "/" + SIGNUP_SUCESS + "*" })
	public String signupSucess(Model model) {
		String view = SIGNUP_SUCESS_PAGE;
		User user = null;
		Long userId = (Long) model.asMap().get("userId");
		if (userId!=null) {
			user = userService.findById(userId);
		}
		if (user != null) {
			model.addAttribute("user", user);
		} else {
			view = REDIRECT;
		}
		return view;
	}
}
