package project.spring.mvc.dao.interfaces;

import project.spring.mvc.models.User;

import java.util.List;

public interface UserDao {
    boolean addUser(User user);

    List<User> list();
}
