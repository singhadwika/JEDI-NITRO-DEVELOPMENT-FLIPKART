package com.flipfit.client;

import com.flipfit.bean.*;
import com.flipfit.business.*;
import java.util.Scanner;

/**
 * Menu handler for Admin operations.
 */
public class AdminMenu {
    
    private static AdminService adminService = new AdminService();
    private static NotificationService notificationService = new NotificationService();
    
    public static void showMenu(Scanner scanner, Admin admin) {
        int choice;
        do {
            System.out.println();
            System.out.println("===== Admin Menu =====");
            System.out.println("1. View Monthly Details");
            System.out.println("2. View Pending Requests");
            System.out.println("3. Approve Gym Center");
            System.out.println("4. Decline Gym Center");
            System.out.println("5. View All Gym Centers");
            System.out.println("6. View All Gym Owners");
            System.out.println("7. Verify Gym Owner");
            System.out.println("8. View All Users");
            System.out.println("9. View All Bookings");
            System.out.println("10. Update Profile");
            System.out.println("11. Logout");
            System.out.print("Enter choice: ");

            choice = getIntInput(scanner);
            switch (choice) {
                case 1:
                    viewMonthlyDetails();
                    break;
                case 2:
                    viewPendingRequests();
                    break;
                case 3:
                    approveGymCenter(scanner);
                    break;
                case 4:
                    declineGymCenter(scanner);
                    break;
                case 5:
                    viewAllGymCenters();
                    break;
                case 6:
                    viewAllGymOwners();
                    break;
                case 7:
                    verifyGymOwner(scanner);
                    break;
                case 8:
                    viewAllUsers();
                    break;
                case 9:
                    viewAllBookings();
                    break;
                case 10:
                    updateProfile(scanner, admin);
                    break;
                case 11:
                    System.out.println("Logging out (Admin)...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 11);
    }
    
    private static void viewMonthlyDetails() {
        System.out.println("AdminMenu.viewMonthlyDetails called (stub)");
        System.out.println("Calling AdminService.getMonthlyDetails (stub)...");
        adminService.getMonthlyDetails();
    }
    
    private static void viewPendingRequests() {
        System.out.println("AdminMenu.viewPendingRequests called (stub)");
        System.out.println("Calling AdminService.viewPendingGymRequest (stub)...");
        adminService.viewPendingGymRequest();
    }
    
    private static void approveGymCenter(Scanner scanner) {
        System.out.println("AdminMenu.approveGymCenter called (stub)");
        System.out.print("Enter GymCenter ID to approve: ");
        int centerId = getIntInput(scanner);
        System.out.println("Calling AdminService.approveRequest (stub)...");
        adminService.approveRequest(centerId);
    }
    
    private static void declineGymCenter(Scanner scanner) {
        System.out.println("AdminMenu.declineGymCenter called (stub)");
        System.out.print("Enter GymCenter ID to decline: ");
        int centerId = getIntInput(scanner);
        System.out.print("Enter reason: ");
        String reason = scanner.nextLine().trim();
        System.out.println("Calling AdminService.declineRequest (stub)...");
        adminService.declineRequest(centerId, reason);
    }
    
    private static void viewAllGymCenters() {
        System.out.println("AdminMenu.viewAllGymCenters called (stub)");
        adminService.viewAllGymCenters();
    }
    
    private static void viewAllGymOwners() {
        System.out.println("AdminMenu.viewAllGymOwners called (stub)");
        adminService.viewAllGymOwners();
    }
    
    private static void verifyGymOwner(Scanner scanner) {
        System.out.println("AdminMenu.verifyGymOwner called (stub)");
        System.out.print("Enter Owner ID to verify: ");
        int ownerId = getIntInput(scanner);
        adminService.verifyGymOwner(ownerId);
    }
    
    private static void viewAllUsers() {
        System.out.println("AdminMenu.viewAllUsers called (stub)");
        adminService.viewAllUsers();
    }
    
    private static void viewAllBookings() {
        System.out.println("AdminMenu.viewAllBookings called (stub)");
        System.out.println("(No BookingService method wired here; this is a stub print only.)");
    }
    
    private static void updateProfile(Scanner scanner, Admin admin) {
        System.out.println("AdminMenu.updateProfile called (stub). admin=" + String.valueOf(admin));
        System.out.println("(Profile update not implemented here; stub print only.)");
    }
    
    private static int getIntInput(Scanner scanner) {
        while (true) {
            String raw = scanner.nextLine().trim();
            try {
                return Integer.parseInt(raw);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number, try again: ");
            }
        }
    }
}
