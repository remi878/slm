package jp.slm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
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
    public String login()
    {
        return ".login-form";
    }

    @RequestMapping({"/signup*","/sign-up*"})
    public String signup()
    {
        return ".signup-form";
    }
}
