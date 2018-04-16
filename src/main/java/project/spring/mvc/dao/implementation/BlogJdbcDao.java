package project.spring.mvc.dao.implementation;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import project.spring.mvc.dao.interfaces.BlogDao;
import project.spring.mvc.models.Blog;

import java.sql.*;
import java.util.List;

public class BlogJdbcDao extends JdbcDaoSupport implements BlogDao {

    public int addBlog(final int owner_id, final String name, final String desc) {
        final String sql = "INSERT INTO blogs (owner_id, name, description) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        getJdbcTemplate().update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id"});
                ps.setInt(1, owner_id);
                ps.setString(2, name);
                ps.setString(3, desc);
                return ps;
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public boolean deleteBlog(int blog_id) {
        String sql = "DELETE FROM blogs WHERE id = ?";
        return 1 == getJdbcTemplate().update(sql, blog_id);
    }

    public boolean editBlog(int blog_id, String name, String desc) {
        String sql = "UPDATE blogs SET description = ? WHERE id = ?";
        return 1 == getJdbcTemplate().update(sql, desc, blog_id);
    }

    public List<Blog> listBlogs() {
        return getJdbcTemplate().query("SELECT * FROM blogs", new BlogRowMapper());
    }

    public List<Blog> listBlogs(int owner_id) {
        return getJdbcTemplate().query("SELECT * FROM blogs WHERE owner_id = ?",
                new BlogRowMapper(),
                owner_id);
    }

    public Blog getBlogById(int id) {
        return getJdbcTemplate().query("SELECT * FROM blogs WHERE id = ?",
                new BlogRowMapper(),
                id).get(0);
    }


    class BlogRowMapper implements RowMapper<Blog> {

        public Blog mapRow(ResultSet resultSet, int i) throws SQLException {
            Blog blog = new Blog();
            blog.setName(resultSet.getString("name"));
            blog.setId(resultSet.getInt("id"));
            blog.setOwner_id(resultSet.getInt("owner_id"));
            blog.setDescription(resultSet.getString("description"));
            return blog;
        }
    }
}
