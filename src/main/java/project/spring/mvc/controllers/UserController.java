package project.spring.mvc.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import project.spring.mvc.dao.interfaces.UserDao;
import project.spring.mvc.models.LoginModel;
import project.spring.mvc.models.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute User user, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("users/register", "command", user);
        }

        userDao.addUser(user);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("body", "../welcome");
        return new ModelAndView("layout/layout", map);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() {
        return new ModelAndView("users/register", "command", new User());
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("users/login", "command", new LoginModel());
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute LoginModel loginModel, HttpSession session,
                              HttpServletResponse response) {
        User user = userDao.validateUser(loginModel.getEmail(), loginModel.getPassword());
        if ( user != null ) {
            session.setMaxInactiveInterval(60 * 60 * 24 *3);
            session.setAttribute("login", loginModel);
            return new ModelAndView("redirect:/");
        } else {
            return new ModelAndView("users/login", "command", loginModel);
        }

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return new ModelAndView("redirect:/");
    }
}
