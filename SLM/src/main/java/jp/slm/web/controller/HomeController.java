package jp.slm.web.controller;

import jp.slm.web.controller.generic.GenericController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController extends GenericController
{
    @RequestMapping({"/home*","","/","/index*"})
    public String welcome()
    {
        return ".home";
    }

    @RequestMapping("/test*")
    public String test()
    {
        return ".test";
    }

    @RequestMapping("/login*")
    public String loginForm()
    {
        return ".login-form";
    }

    @RequestMapping(method = RequestMethod.GET, value ={"/signup*","/sign-up*"})
    public String signupChoise()
    {
        return ".signup";
    }
}
