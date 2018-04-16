package project.spring.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import project.spring.mvc.dao.interfaces.BlogDao;
import project.spring.mvc.dao.interfaces.PostDao;
import project.spring.mvc.dao.interfaces.UserDao;
import project.spring.mvc.models.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/blogs")
@org.springframework.stereotype.Controller
public class BlogController implements Controller {

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PostDao postDao;

    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        return null;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request) {
        List<Blog> blogs = blogDao.listBlogs();
        request.setAttribute("blogs", blogs);
        return new ModelAndView("blogs/all", "command",  blogs);
    }

    @RequestMapping(value = "/your", method = RequestMethod.GET)
    public ModelAndView userBlogs(HttpSession session, HttpServletRequest request) {
        List<Blog> blogs = blogDao.listBlogs(
                userDao.getUserIdByEmail(
                        ((LoginModel)session.getAttribute("login")).getEmail()
                ));

        request.setAttribute("blogs", blogs);
        return new ModelAndView("blogs/all", "command", blogs);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addBlog() {
        return new ModelAndView("blogs/add", "command", new BlogAddModel());
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ModelAndView addBlog(HttpSession session, @ModelAttribute BlogAddModel model) {

        int blogId = blogDao.addBlog(
          userDao.getUserIdByEmail(((LoginModel)session.getAttribute("login")).getEmail()),
          model.getName(),
          model.getDescription()
        );
        return new ModelAndView("redirect:/blogs/view/"+ blogId);
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView viewBlog(@PathVariable int id){
        Blog blog = blogDao.getBlogById(id);
        List<Post> posts = postDao.getPostsByBlogId(id);
        ModelAndView mav = new ModelAndView("blogs/view", "command", blog);
        mav.addObject("posts", posts);
        mav.addObject("blog", blog);
        mav.addObject("postAddModel", new PostAddModel());
        return mav;
    }
}
