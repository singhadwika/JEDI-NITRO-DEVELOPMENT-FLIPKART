package com.flipfit.bean;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;

    public User() {}

    public User(int id, String name, String email, String password, String role) {}

    public int getId() { return 0; }
    public void setId(int id) {}
    public String getName() { return null; }
    public void setName(String name) {}
    public String getEmail() { return null; }
    public void setEmail(String email) {}
    public String getPassword() { return null; }
    public void setPassword(String password) {}
    public String getRole() { return null; }
    public void setRole(String role) {}

    public boolean login(String email, String password) { return false; }
    public void logout() {}
    public boolean register() { return false; }
    public void updateProfile(String name, String email, String password) {}
    public User getUserDetails() { return null; }
}
