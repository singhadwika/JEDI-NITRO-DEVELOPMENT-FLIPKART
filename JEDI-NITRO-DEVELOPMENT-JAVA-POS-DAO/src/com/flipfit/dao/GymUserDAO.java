package com.flipfit.dao;

import com.flipfit.bean.User;
import java.util.List;

/**
 * DAO Interface for GymUser operations
 * Provides methods for user-related database operations
 */
public interface GymUserDAO {

    /**
     * Add a new user to the system
     * @param user User object to add
     * @return true if user added successfully, false otherwise
     */
    boolean addUser(User user);

    /**
     * Get user by their unique ID
     * @param userId ID of the user
     * @return User object if found, null otherwise
     */
    User getUserById(int userId);

    /**
     * Get user by their email address
     * @param email Email address of the user
     * @return User object if found, null otherwise
     */
    User getUserByEmail(String email);

    /**
     * Update user details
     * @param userId ID of the user to update
     * @param name New name for the user
     * @param email New email for the user
     * @param password New password for the user
     * @return true if updated successfully, false otherwise
     */
    boolean updateUser(int userId, String name, String email, String password);

    /**
     * Delete a user from the system
     * @param userId ID of the user to delete
     * @return true if deleted successfully, false otherwise
     */
    boolean deleteUser(int userId);

    /**
     * Get all users in the system
     * @return List of all User objects
     */
    List<User> getAllUsers();

    /**
     * Authenticate user with email and password
     * @param email User's email
     * @param password User's password
     * @return User object if authentication successful, null otherwise
     */
    User authenticateUser(String email, String password);

    /**
     * Check if email already exists in the system
     * @param email Email to check
     * @return true if email exists, false otherwise
     */
    boolean emailExists(String email);
}
