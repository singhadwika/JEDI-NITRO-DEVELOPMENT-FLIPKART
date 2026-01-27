package com.flipfit.dao;

import com.flipfit.bean.User;
import java.util.List;

public interface UserDAO {

    boolean addUser(User user);

    User getUserById(int userId);

    User getUserByEmail(String email);

    boolean updateUser(int userId, String name, String email, String password);

    List<User> getAllUsers();
}
