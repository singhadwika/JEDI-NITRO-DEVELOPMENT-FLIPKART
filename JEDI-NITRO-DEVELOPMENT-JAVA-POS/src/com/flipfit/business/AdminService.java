package com.flipfit.business;

import com.flipfit.bean.*;
import java.util.List;

/**
 * Implementation of AdminServiceInterface.
 */
public class AdminService implements AdminServiceInterface {
    
    @Override
    public String getMonthlyDetails() {
        System.out.println("AdminService.getMonthlyDetails called");
        return null;
    }
    
    @Override
    public List<GymCenter> viewPendingGymRequest() {
        System.out.println("AdminService.viewPendingGymRequest called");
        return null;
    }
    
    @Override
    public boolean approveRequest(int centerId) {
        System.out.println("AdminService.approveRequest called");
        return false;
    }
    
    @Override
    public boolean declineRequest(int centerId, String reason) {
        System.out.println("AdminService.declineRequest called");
        return false;
    }
    
    @Override
    public List<User> viewAllUsers() {
        System.out.println("AdminService.viewAllUsers called");
        return null;
    }
    
    @Override
    public List<GymCenter> viewAllGymCenters() {
        System.out.println("AdminService.viewAllGymCenters called");
        return null;
    }
    
    @Override
    public List<GymOwner> viewAllGymOwners() {
        System.out.println("AdminService.viewAllGymOwners called");
        return null;
    }
    
    @Override
    public boolean verifyGymOwner(int ownerId) {
        System.out.println("AdminService.verifyGymOwner called");
        return false;
    }
    
    @Override
    public Admin getAdminById(int adminId) {
        System.out.println("AdminService.getAdminById called");
        return null;
    }
    
    public boolean registerAdmin(Admin admin) {
        System.out.println("AdminService.registerAdmin called");
        return false;
    }
}
