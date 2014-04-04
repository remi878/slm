package jp.slm.web.controller;

import javax.validation.Valid;

import jp.slm.business.bean.Fan;
import jp.slm.business.bean.User;
import jp.slm.business.service.FanService;
import jp.slm.business.service.UserService;
import jp.slm.web.controller.generic.GenericController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = FanController.FAN_CONTEXT_URL)
public class FanController extends GenericController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FanService fanService;
	
	@RequestMapping(method = RequestMethod.GET, value = PROFILE_EXT_URL + URL_WILDCARD)
	@Secured({ User.ADMIN_ROLE_STR, User.FAN_ROLE_STR })
	public String viewProfile(Model model) {
		String view = FAN_PROFILE_PAGE;
		UserDetails userDetails = getUserDetails();
		if (userDetails != null && userDetails instanceof Fan) {
			Fan fan = (Fan) userDetails;
			fan = fanService.findById(fan.getId());
			refreshUserPrincipal(fan);
			model.addAttribute(FAN, fan);
			model.addAttribute(USER_STR, fan.getUser());
		} else {
			view = handleAuthenticationError();
		}
		return view;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = EDIT_PROFILE_EXT_URL + URL_WILDCARD)
	@Secured({ User.ADMIN_ROLE_STR, User.FAN_ROLE_STR })
	public String editProfile(Model model) {
		String view = FAN_EDIT_PROFILE_PAGE;
		UserDetails userDetails = getUserDetails();
		if (userDetails != null && userDetails instanceof Fan) {
			Fan fan = (Fan) userDetails;
			fan = fanService.findById(fan.getId());
			refreshUserPrincipal(fan);
			model.addAttribute(FAN, fan);
			model.addAttribute(USER_STR, fan.getUser());
		} else {
			view = handleAuthenticationError();
		}
		return view;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = SAVE_PROFILE_EXT_URL + URL_WILDCARD)
	@Secured({ User.ADMIN_ROLE_STR, User.FAN_ROLE_STR })
	public String saveProfile(@Valid @ModelAttribute(FAN) Fan fanForm, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		String view = FAN_EDIT_PROFILE_PAGE;
		UserDetails userDetails = getUserDetails();
		if (!result.hasErrors() && userDetails != null && userDetails instanceof Fan) {
			Fan fan = (Fan) userDetails;
			fan = fanService.findById(fan.getId());
			if (!fan.getUsername().equals(fanForm.getUsername()) && userService.userExists(fanForm.getUsername())) {
				result.rejectValue(EMAIL, "user.already.exists", "Email already used");
			}
			fan.mergeWithForm(fanForm);
			fanService.update(fan);
			refreshUserPrincipal(fan);
			redirectAttributes.addAttribute(MESSAGE, FAN_UPDATE_DONE);
			view = REDIRECT + FAN_PROFILE_URL;
		} else {
			view = handleAuthenticationError();
		}
		return view;
	}
}
