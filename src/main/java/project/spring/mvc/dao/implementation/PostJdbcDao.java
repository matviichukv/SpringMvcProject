package project.spring.mvc.dao.implementation;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import project.spring.mvc.dao.interfaces.PostDao;
import project.spring.mvc.models.Post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PostJdbcDao extends JdbcDaoSupport implements PostDao  {

    public List<Post> getPostsByBlogId(int id) {
        String sql = "SELECT * FROM posts WHERE blog_id = ? ORDER BY date DESC";
        return getJdbcTemplate().query(sql, new PostRowMapper(), id);
    }

    public boolean addPost(int blog_id, String title, String content) {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String sql = String.format("INSERT INTO posts (blog_id, date, title, content) " +
                "VALUES (?, to_timestamp('%s', 'dd-mm-yyyy hh24:mi:ss'), ?, ?)", df.format(date));
        return 1 == getJdbcTemplate().update(sql, blog_id, title, content );
    }

    public boolean deletePost(int post_id) {
        String sql = "DELETE FROM posts WHERE id = ?";
        return 1 == getJdbcTemplate().update(sql, post_id);
    }

    class PostRowMapper implements RowMapper<Post> {

        public Post mapRow(ResultSet resultSet, int i) throws SQLException {
            Post post = new Post();
            post.setBlogId(resultSet.getInt("blog_id"));
            post.setContent(resultSet.getString("content"));
            post.setTitle(resultSet.getString("title"));
            post.setId(resultSet.getInt("id"));
            post.setDate(resultSet.getDate("date"));
            return post;
        }
    }
}
