package jp.slm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	
	@RequestMapping(method = RequestMethod.POST, value = { "/signup", "/sign-up" })
	public String signup() {
		
		return ".signup-form";
	}
}
