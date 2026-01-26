package com.flipfit.business;

import com.flipfit.bean.*;
import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    // These act as our in-memory database tables
    private static List<Admin> adminList = new ArrayList<>();
    private static List<GymCenter> gymCenters = new ArrayList<>();
    private static List<GymOwner> gymOwners = new ArrayList<>();
    private static List<User> users = new ArrayList<>();

    static {
        // Hardcoded Users (using default constructor + setters)
        User u1 = new User();
        u1.setId(1); u1.setName("Poojitha"); u1.setEmail("poojitha@flipfit.com");
        users.add(u1);

        User u2 = new User();
        u2.setId(2); u2.setName("Saumyajeet"); u2.setEmail("saumya@flipkart.com");
        users.add(u2);

        // Hardcoded Gym Center (using your 5-arg constructor)
        gymCenters.add(new GymCenter(101, "FlipFit HSR", "Bangalore", false, 501));
    }

    // 1. INSERT: Add a new Admin to the collection
    @Override
    public boolean registerAdmin(Admin admin) {
        if(admin == null) return false;
        return adminList.add(admin);
    }

    // 2. READ: Get specific Admin by ID (Fixes the "new Admin()" error)
    @Override
    public Admin getAdminById(int adminId) {
        for (Admin a : adminList) {
            if (a.getId() == adminId) return a;
        }
        return null; 
    }

    // 3. READ: Filter list for unapproved centers
    @Override
    public List<GymCenter> viewPendingGymRequest() {
        List<GymCenter> pending = new ArrayList<>();
        for (GymCenter c : gymCenters) {
            if (!c.isApproved()) pending.add(c);
        }
        return pending;
    }

    // 4. UPDATE: Change 'approved' status to true
    @Override
    public boolean approveRequest(int centerId) {
        for (GymCenter c : gymCenters) {
            if (c.getCenterId() == centerId) {
                c.setApproved(true);
                return true;
            }
        }
        return false;
    }

    // 5. DELETE/REMOVE: Remove center from collection
    @Override
    public boolean declineRequest(int centerId, String reason) {
        System.out.println("Declining Center " + centerId + " Reason: " + reason);
        return gymCenters.removeIf(c -> c.getCenterId() == centerId);
    }

    // 6. UPDATE: Verify an owner
    @Override
    public boolean verifyGymOwner(int ownerId) {
        for (GymOwner o : gymOwners) {
            if (o.getId() == ownerId) {
                o.setVerified(true);
                return true;
            }
        }
        return false;
    }

    // 7. READ ALL: Return full lists
    @Override public List<User> viewAllUsers() { return users; }
    @Override public List<GymCenter> viewAllGymCenters() { return gymCenters; }
    @Override public List<GymOwner> viewAllGymOwners() { return gymOwners; }

    // 8. REPORT: Logic based on collection size
    @Override
    public String getMonthlyDetails() {
        return "System Summary: " + users.size() + " Users, " + gymCenters.size() + " Centers.";
    }
}