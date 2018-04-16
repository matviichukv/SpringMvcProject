package project.spring.mvc.dao.interfaces;

import project.spring.mvc.models.Post;

import java.util.List;

public interface PostDao {

    List<Post> getPostsByBlogId(int id);

    boolean addPost(int blog_id, String title, String content);

    boolean deletePost(int post_id);
}
