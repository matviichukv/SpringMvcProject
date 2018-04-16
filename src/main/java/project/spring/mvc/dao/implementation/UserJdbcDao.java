package project.spring.mvc.dao.implementation;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import project.spring.mvc.dao.interfaces.UserDao;
import project.spring.mvc.models.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserJdbcDao extends JdbcDaoSupport implements UserDao {



    public boolean addUser(User user) {
        if (userExists(user.getEmail(), user.getUsername())) {
            return false;
        }

        String sql = "INSERT INTO users (username, password, avatar, email) " +
                "values (?, ?, ?, ?)";

        return 1 == getJdbcTemplate().update(sql,
                user.getUsername(),
                BCrypt.hashpw(user.getPassword(), "$2a$10$B7UV7ZUfeJiPF7nYosWdo."),
                "default.jpg",
                user.getEmail());
    }

    public List<User> list() {
        String sql = "SELECT * FROM users";
        return getJdbcTemplate().query(sql, new UserRowMapper());
    }

    public User validateUser(String email, String password) {
        String sql = "SELECT * FROM users WHERE email LIKE ?";

        User user = getJdbcTemplate().query(sql, new UserRowMapper(), email).get(0);
        if (user == null) {
            return null;
        }
        if (BCrypt.hashpw(password, "$2a$10$B7UV7ZUfeJiPF7nYosWdo.").equals(user.getPassword().trim())) {

            return user;
        }
        return null;
    }

    public int getUserIdByEmail(String email) {
        return getJdbcTemplate().query("SELECT * FROM users WHERE email LIKE ?",
                new UserRowMapper(),
                email).get(0).getId();
    }

    public boolean userExists(String email, String username) {
        String sql = "SELECT * FROM users WHERE email = ? OR username = ?";
        return getJdbcTemplate().query(sql, new UserRowMapper(), email, username).size() > 0;
    }

    public class UserRowMapper implements RowMapper<User> {

        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setEmail(resultSet.getString("email"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setId(resultSet.getInt("id"));
            user.setAvatar(resultSet.getString("avatar"));
            user.setAuthKey(resultSet.getString("authKey"));
            user.setAccessToken(resultSet.getString("accessToken"));
            return user;
        }
    }
}
