package com.flipfit.bean;

import java.util.List;

public class Admin extends User {

    public Admin() {}

    public Admin(int id, String name, String email, String password) {}

    public void getMonthlyDetails() {}
    public List<GymCenter> viewPendingGymRequest(List<GymCenter> allCenters) { return null; }
    public boolean approveRequest(GymCenter center) { return false; }
    public boolean declineRequest(GymCenter center, String reason) { return false; }
    public void viewAllUsers(List<User> users) {}
    public void viewAllGymCenters(List<GymCenter> centers) {}
}
