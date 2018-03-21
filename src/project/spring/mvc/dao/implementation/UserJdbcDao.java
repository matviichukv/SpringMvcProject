package project.spring.mvc.dao.implementation;


import org.springframework.jdbc.core.support.JdbcDaoSupport;
import project.spring.mvc.dao.interfaces.UserDao;
import project.spring.mvc.models.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;

public class UserJdbcDao extends JdbcDaoSupport implements UserDao {


    @Override
    public boolean addUser(User user) {
        String sql = "INSERT INTO users (username, password, avatar, email) " +
                "values (?, ?, ?, ?)";
        BCrypt crypt = new BCrypt();
        return 1 == getJdbcTemplate().update(sql,
                user.getUsername(),
                BCrypt.hashpw(user.getPassword(), "$2a$10$B7UV7ZUfeJiPF7nYosWdo."),
                "default.jsp",
                user.getEmail());
    }

    @Override
    public List<User> list() {
        return null;
    }
}
