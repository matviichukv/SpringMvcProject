package project.spring.mvc.dao.interfaces;

import project.spring.mvc.models.Blog;

import java.util.List;

public interface BlogDao {
    int addBlog(int owner_id, String name, String desc);

    boolean deleteBlog(int blog_id);

    boolean editBlog(int blog_id, String name, String desc);

    List<Blog> listBlogs();

    List<Blog> listBlogs(int owner_id);

    Blog getBlogById(int id);
}
