package com.flipfit.dao;

import com.flipfit.bean.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {

    private static List<Admin> adminList = new ArrayList<>();
    private static List<GymCenter> gymCenters = new ArrayList<>();
    private static List<GymOwner> gymOwners = new ArrayList<>();
    private static List<User> users = new ArrayList<>();

    @Override
    public boolean addAdmin(Admin admin) {
        return adminList.add(admin);
    }

    @Override
    public Admin getAdminById(int adminId) {
        for (Admin admin : adminList) {
            if (admin.getId() == adminId) {
                return admin;
            }
        }
        return null;
    }

    @Override
    public List<GymCenter> getPendingGymCenters() {
        List<GymCenter> pendingCenters = new ArrayList<>();

        for (GymCenter center : gymCenters) {
            if (!center.isApproved()) {
                pendingCenters.add(center);
            }
        }

        return pendingCenters;
    }

    @Override
    public boolean approveGymCenter(int centerId) {
        for (GymCenter center : gymCenters) {
            if (center.getCenterId() == centerId) {
                center.setApproved(true);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean declineGymCenter(int centerId, String reason) {
        for (GymCenter center : gymCenters) {
            if (center.getCenterId() == centerId) {
                gymCenters.remove(center);
                System.out.println("Gym Center Declined Reason: " + reason);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public List<GymCenter> getAllGymCenters() {
        return gymCenters;
    }

    @Override
    public List<GymOwner> getAllGymOwners() {
        return gymOwners;
    }

    @Override
    public boolean verifyGymOwner(int ownerId) {
        for (GymOwner owner : gymOwners) {
            if (owner.getId() == ownerId) {
                owner.setVerified(true);
                return true;
            }
        }
        return false;
    }

    @Override
    public String getMonthlyReport() {
        return "Monthly Report: \n"
                + "Total Users: " + users.size() + "\n"
                + "Total Gym Centers: " + gymCenters.size() + "\n"
                + "Total Gym Owners: " + gymOwners.size();
    }
}
