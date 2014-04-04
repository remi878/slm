package jp.slm.web.controller;

import jp.slm.web.controller.generic.GenericController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController extends GenericController {

	@RequestMapping(method = RequestMethod.GET, value = { HOME_URL })
	public String welcome() {
		return HOME_PAGE;
	}
	
	@RequestMapping(value ={EMPTY, (URL_SEP+HOME_STR+URL_WILDCARD), (URL_SEP+INDEX_STR+URL_WILDCARD)})
	public String welcomeOther() {
		return REDIRECT + HOME_URL;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = {HOME_URL})
	public String welcomePost() {
		return REDIRECT + HOME_URL;
	}
	
	@RequestMapping("/test*")
	public String test() {
		return ".test";
	}
	
	@RequestMapping(value = LOGIN_URL + URL_WILDCARD)
	public String loginForm() {
		return LOGIN_FORM_PAGE;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = { SIGNUP_URL + URL_WILDCARD })
	public String signupChoise() {
		return SIGNUP_PAGE;
	}
}
