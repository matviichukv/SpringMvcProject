package project.spring.mvc.controllers;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class HomeController implements org.springframework.web.servlet.mvc.Controller {
    public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest httpServletRequest,
                                      javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {
        return null;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("layout/layout", "command", null);
        mav.addObject("body", "../welcome");
        return mav;
    }
}
