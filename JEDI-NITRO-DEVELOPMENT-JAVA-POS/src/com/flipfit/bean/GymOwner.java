package com.flipfit.bean;

import java.util.List;

public class GymOwner extends User {
    private boolean isVerified;
    private List<GymCenter> gymCenters;

    public GymOwner() {}

    public GymOwner(int id, String name, String email, String password, boolean isVerified) {}

    public boolean isVerified() { return false; }
    public void setVerified(boolean verified) {}
    public List<GymCenter> getGymCenters() { return null; }
    public void setGymCenters(List<GymCenter> gymCenters) {}

    public void updateCenterDetails(int centerId, String name, String location) {}
    public void manageSlots(int centerId) {}
    public void viewBookings() {}
    public void addGymCenter(GymCenter center) {}
}
