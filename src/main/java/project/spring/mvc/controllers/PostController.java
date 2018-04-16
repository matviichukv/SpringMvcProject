package project.spring.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project.spring.mvc.dao.interfaces.PostDao;
import project.spring.mvc.models.PostAddModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = "/post")
@Controller
public class PostController implements org.springframework.web.servlet.mvc.Controller {

    @Autowired
    private PostDao postDao;

    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        return null;
    }

    @RequestMapping("/add")
    public ModelAndView addPost(@ModelAttribute PostAddModel model) {
        postDao.addPost(model.getBlogId(), model.getTitle(), model.getContent());
        return new ModelAndView("redirect:/blogs/view/"+ model.getBlogId());
    }
}
