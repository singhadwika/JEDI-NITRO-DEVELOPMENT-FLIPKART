package com.flipfit.business;

import com.flipfit.bean.*;
import java.util.List;

/**
 * Implementation of AdminServiceInterface.
 */
public class AdminService implements AdminServiceInterface {
    
    @Override
    public String getMonthlyDetails() {
        // TODO: Implement
        return null;
    }
    
    @Override
    public List<GymCenter> viewPendingGymRequest() {
        // TODO: Implement
        return null;
    }
    
    @Override
    public boolean approveRequest(int centerId) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public boolean declineRequest(int centerId, String reason) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public List<User> viewAllUsers() {
        // TODO: Implement
        return null;
    }
    
    @Override
    public List<GymCenter> viewAllGymCenters() {
        // TODO: Implement
        return null;
    }
    
    @Override
    public List<GymOwner> viewAllGymOwners() {
        // TODO: Implement
        return null;
    }
    
    @Override
    public boolean verifyGymOwner(int ownerId) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public Admin getAdminById(int adminId) {
        // TODO: Implement
        return null;
    }
    
    public boolean registerAdmin(Admin admin) {
        // TODO: Implement
        return false;
    }
}
