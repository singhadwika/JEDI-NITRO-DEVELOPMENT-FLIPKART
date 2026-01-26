package com.flipfit.dao;

import com.flipfit.bean.User;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private static List<User> userList = new ArrayList<>();
    private static int userCounter = 1;

    @Override
    public boolean addUser(User user) {

        user.setId(userCounter++);
        userList.add(user);

        return true;
    }

    @Override
    public User getUserById(int userId) {

        for (User user : userList) {
            if (user.getId() == userId) {
                return user;
            }
        }

        return null;
    }

    @Override
    public User getUserByEmail(String email) {

        for (User user : userList) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public boolean updateUser(int userId, String name, String email, String password) {

        User user = getUserById(userId);

        if (user != null) {

            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);

            return true;
        }

        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return userList;
    }
}
