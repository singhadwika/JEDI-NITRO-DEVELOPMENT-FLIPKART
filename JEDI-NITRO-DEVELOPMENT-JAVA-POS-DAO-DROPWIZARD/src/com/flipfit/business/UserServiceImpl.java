package com.flipfit.business;

import com.flipfit.bean.User;
import com.flipfit.dao.UserDAO;
import com.flipfit.dao.UserDAOImpl;

import java.util.List;

/**
 * The Class UserServiceImpl.
 * Implements business logic operations for user management in the FlipFit system.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();

    /**
     * Authenticates a user with provided credentials.
     *
     * @param email the user email
     * @param password the user password
     * @return the authenticated user if credentials are valid, null otherwise
     */
    @Override
    public User login(String email, String password) {

        User user = userDAO.getUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    /**
     * Logs out a user from the system.
     *
     * @param userId the user id
     * @return true if logout is successful, false otherwise
     */
    @Override
    public boolean logout(int userId) {

        User user = userDAO.getUserById(userId);

        return user != null;
    }

    /**
     * Registers a new user in the system.
     *
     * @param user the user to register
     * @return true if registration is successful, false otherwise
     */
    @Override
    public boolean register(User user) {
        return userDAO.addUser(user);
    }

    /**
     * Updates the profile of an existing user.
     *
     * @param userId the user id
     * @param name the new user name
     * @param email the new user email
     * @param password the new user password
     * @return true if update is successful, false otherwise
     */
    @Override
    public boolean updateProfile(int userId, String name, String email, String password) {
        return userDAO.updateUser(userId, name, email, password);
    }

    /**
     * Retrieves user details by user ID.
     *
     * @param userId the user id
     * @return the user details
     */
    @Override
    public User getUserDetails(int userId) {
        return userDAO.getUserById(userId);
    }

    /**
     * Retrieves user by email address.
     *
     * @param email the user email
     * @return the user with the specified email
     */
    @Override
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    /**
     * Retrieves all users from the system.
     *
     * @return list of all users
     */
    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
