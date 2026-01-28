package com.flipfit.business;

import com.flipfit.bean.User;
import java.util.List;

/**
 * The Interface UserService.
 * Defines business logic operations for user management in the FlipFit system.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public interface UserService {
    
    /**
     * Authenticates a user with provided credentials.
     *
     * @param email the user email
     * @param password the user password
     * @return the authenticated user if credentials are valid, null otherwise
     */
    public User login(String email, String password);

    /**
     * Logs out a user from the system.
     *
     * @param userId the user id
     * @return true if logout is successful, false otherwise
     */
    public boolean logout(int userId);

    /**
     * Registers a new user in the system.
     *
     * @param user the user to register
     * @return true if registration is successful, false otherwise
     */
    public boolean register(User user);

    /**
     * Updates the profile of an existing user.
     *
     * @param userId the user id
     * @param name the new user name
     * @param email the new user email
     * @param password the new user password
     * @return true if update is successful, false otherwise
     */
    public boolean updateProfile(int userId, String name, String email, String password);

    /**
     * Retrieves user details by user ID.
     *
     * @param userId the user id
     * @return the user details
     */
    public User getUserDetails(int userId);

    /**
     * Retrieves user by email address.
     *
     * @param email the user email
     * @return the user with the specified email
     */
    public User getUserByEmail(String email);
    
    /**
     * Retrieves all users from the system.
     *
     * @return list of all users
     */
    public List<User> getAllUsers();
}
