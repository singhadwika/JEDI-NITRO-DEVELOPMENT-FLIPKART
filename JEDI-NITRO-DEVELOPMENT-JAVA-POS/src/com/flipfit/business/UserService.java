package com.flipfit.business;

import com.flipfit.bean.User;
import java.util.List;

/**
 * Implementation of UserServiceInterface.
 */
public class UserService implements UserServiceInterface {
    
    @Override
    public User login(String email, String password) {
        // TODO: Implement
        return null;
    }
    
    @Override
    public boolean logout(int userId) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public boolean register(User user) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public boolean updateProfile(int userId, String name, String email, String password) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public User getUserDetails(int userId) {
        // TODO: Implement
        return null;
    }
    
    @Override
    public User getUserByEmail(String email) {
        // TODO: Implement
        return null;
    }
    
    public List<User> getAllUsers() {
        // TODO: Implement
        return null;
    }
}
