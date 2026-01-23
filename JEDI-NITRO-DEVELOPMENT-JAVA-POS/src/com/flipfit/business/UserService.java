package com.flipfit.business;

import com.flipfit.bean.User;
import java.util.List;

/**
 * Implementation of UserServiceInterface.
 */
public class UserService implements UserServiceInterface {
    
    @Override
    public User login(String email, String password) {
        System.out.println("UserService.login called");
        return null;
    }
    
    @Override
    public boolean logout(int userId) {
        System.out.println("UserService.logout called");
        return false;
    }
    
    @Override
    public boolean register(User user) {
        System.out.println("UserService.register called");
        return false;
    }
    
    @Override
    public boolean updateProfile(int userId, String name, String email, String password) {
        System.out.println("UserService.updateProfile called");
        return false;
    }
    
    @Override
    public User getUserDetails(int userId) {
        System.out.println("UserService.getUserDetails called");
        return null;
    }
    
    @Override
    public User getUserByEmail(String email) {
        System.out.println("UserService.getUserByEmail called");
        return null;
    }
    
    public List<User> getAllUsers() {
        System.out.println("UserService.getAllUsers called");
        return null;
    }
}
