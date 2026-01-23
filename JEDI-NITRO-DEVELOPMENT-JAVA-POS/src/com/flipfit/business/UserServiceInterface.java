package com.flipfit.business;

import com.flipfit.bean.User;
import java.util.List;

public interface UserServiceInterface {
    User login(String email, String password);

    boolean logout(int userId);

    boolean register(User user);

    boolean updateProfile(int userId, String name, String email, String password);

    User getUserDetails(int userId);

    User getUserByEmail(String email);

    List<User> getAllUsers();
}
