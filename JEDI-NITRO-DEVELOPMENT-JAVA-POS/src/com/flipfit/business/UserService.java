package com.flipfit.business;

import com.flipfit.bean.User;
import java.util.List;

public interface UserService {
    
    public User login(String email, String password);

    public boolean logout(int userId);

    public boolean register(User user);

    public boolean updateProfile(int userId, String name, String email, String password);

    public User getUserDetails(int userId);

    public User getUserByEmail(String email);
    
    public List<User> getAllUsers();
}
