package com.flipfit.business;

import com.flipfit.bean.*;
import com.flipfit.dao.*;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    private AdminDAO adminDAO = new AdminDAOImpl();

    @Override
    public String getMonthlyDetails() {
        return adminDAO.getMonthlyReport();
    }

    @Override
    public List<GymCenter> viewPendingGymRequest() {
        return adminDAO.getPendingGymCenters();
    }

    @Override
    public boolean approveRequest(int centerId) {
        return adminDAO.approveGymCenter(centerId);
    }

    @Override
    public boolean declineRequest(int centerId, String reason) {
        return adminDAO.declineGymCenter(centerId, reason);
    }

    @Override
    public List<User> viewAllUsers() {
        return adminDAO.getAllUsers();
    }

    @Override
    public List<GymCenter> viewAllGymCenters() {
        return adminDAO.getAllGymCenters();
    }

    @Override
    public List<GymOwner> viewAllGymOwners() {
        return adminDAO.getAllGymOwners();
    }

    @Override
    public boolean verifyGymOwner(int ownerId) {
        return adminDAO.verifyGymOwner(ownerId);
    }

    @Override
    public Admin getAdminById(int adminId) {
        return adminDAO.getAdminById(adminId);
    }

    @Override
    public boolean registerAdmin(Admin admin) {
        return adminDAO.addAdmin(admin);
    }
}
