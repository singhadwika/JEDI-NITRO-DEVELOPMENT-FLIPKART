package com.flipfit.business;

import com.flipfit.bean.User;
import com.flipfit.dao.UserDAO;
import com.flipfit.dao.UserDAOImpl;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public User login(String email, String password) {

        User user = userDAO.getUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    @Override
    public boolean logout(int userId) {

        User user = userDAO.getUserById(userId);

        return user != null;
    }

    @Override
    public boolean register(User user) {
        return userDAO.addUser(user);
    }

    @Override
    public boolean updateProfile(int userId, String name, String email, String password) {
        return userDAO.updateUser(userId, name, email, password);
    }

    @Override
    public User getUserDetails(int userId) {
        return userDAO.getUserById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
