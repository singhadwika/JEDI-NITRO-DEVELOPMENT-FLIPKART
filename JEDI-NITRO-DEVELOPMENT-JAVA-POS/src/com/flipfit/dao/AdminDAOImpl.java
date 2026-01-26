package com.flipfit.dao;

import com.flipfit.bean.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {

    // Delegate to actual DAOs instead of maintaining separate lists
    private GymCenterDAO gymCenterDAO = new GymCenterDAOImpl();
    private GymOwnerDAO gymOwnerDAO = new GymOwnerDAOImpl();
    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean addAdmin(Admin admin) {
        return userDAO.addUser(admin);
    }

    @Override
    public Admin getAdminById(int adminId) {
        User user = userDAO.getUserById(adminId);
        if (user instanceof Admin) {
            return (Admin) user;
        }
        return null;
    }

    @Override
    public List<GymCenter> getPendingGymCenters() {
        return gymCenterDAO.getPendingGymCenters();
    }

    @Override
    public boolean approveGymCenter(int centerId) {
        return gymCenterDAO.approveGymCenter(centerId);
    }

    @Override
    public boolean declineGymCenter(int centerId, String reason) {
        System.out.println("Gym Center Declined Reason: " + reason);
        return gymCenterDAO.rejectGymCenter(centerId);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public List<GymCenter> getAllGymCenters() {
        return gymCenterDAO.getAllGymCenters();
    }

    @Override
    public List<GymOwner> getAllGymOwners() {
        return gymOwnerDAO.getAllGymOwners();
    }

    @Override
    public boolean verifyGymOwner(int ownerId) {
        GymOwner owner = gymOwnerDAO.getGymOwnerById(ownerId);
        if (owner != null) {
            owner.setVerified(true);
            return true;
        }
        return false;
    }

    @Override
    public String getMonthlyReport() {
        return "Monthly Report: \n"
                + "Total Users: " + userDAO.getAllUsers().size() + "\n"
                + "Total Gym Centers: " + gymCenterDAO.getAllGymCenters().size() + "\n"
                + "Total Gym Owners: " + gymOwnerDAO.getAllGymOwners().size();
    }
}
