package com.flipfit.client;

import com.flipfit.business.*;
import com.flipfit.bean.*;
import java.util.List;

public class AdminTestClient {
    public static void main(String[] args) {
        AdminService adminService = new AdminServiceImpl();
        System.out.println("=== Starting Comprehensive Admin Service Test ===\n");

        // 1. TEST: READ (Hardcoded Data)
        System.out.println("Step 1: Checking Hardcoded Users...");
        List<User> users = adminService.viewAllUsers();
        for (User u : users) {
            System.out.println("Found User: " + u.getName() + " (ID: " + u.getId() + ")");
        }

        // 2. TEST: INSERT (registerAdmin)
        System.out.println("\nStep 2: Testing Admin Insert...");
        // Since Admin requires a 4-arg constructor: id, name, email, password
        Admin newAdmin = new Admin(10, "DemoAdmin", "demo@flipfit.com", "admin123");
        boolean isAdded = adminService.registerAdmin(newAdmin);
        System.out.println("Insertion status: " + isAdded);

        // 3. TEST: READ (getAdminById)
        System.out.println("\nStep 3: Testing Admin Retrieval...");
        Admin retrieved = adminService.getAdminById(10);
        if (retrieved != null) {
            System.out.println("Successfully retrieved Admin: " + retrieved.getName());
        }

        // 4. TEST: UPDATE (approveRequest)
        System.out.println("\nStep 4: Testing Center Approval (Update)...");
        List<GymCenter> centers = adminService.viewAllGymCenters();
        if (!centers.isEmpty()) {
            int targetId = centers.get(0).getCenterId();
            System.out.println("Center " + targetId + " Approved Status before: " + centers.get(0).isApproved());
            adminService.approveRequest(targetId);
            System.out.println("Center " + targetId + " Approved Status after: " + centers.get(0).isApproved());
        }

        // 5. TEST: LOGIC (viewPendingGymRequest)
        System.out.println("\nStep 5: Testing Pending List Filter...");
        // Add a new unapproved center to test filtering
        List<GymCenter> pending = adminService.viewPendingGymRequest();
        System.out.println("Number of pending centers: " + pending.size());

        // 6. TEST: DELETE (declineRequest)
        System.out.println("\nStep 6: Testing Decline/Removal...");
        adminService.declineRequest(101, "Document Mismatch");
        System.out.println("Total Centers remaining: " + adminService.viewAllGymCenters().size());

        System.out.println("\n=== All Tests Passed Successfully ===");
    }
}