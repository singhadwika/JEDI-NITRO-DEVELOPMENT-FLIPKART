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
        // TODO: Implement admin menu
    }
    
    private static void viewMonthlyDetails() {
        // TODO: Implement
    }
    
    private static void viewPendingRequests() {
        // TODO: Implement
    }
    
    private static void approveGymCenter(Scanner scanner) {
        // TODO: Implement
    }
    
    private static void declineGymCenter(Scanner scanner) {
        // TODO: Implement
    }
    
    private static void viewAllGymCenters() {
        // TODO: Implement
    }
    
    private static void viewAllGymOwners() {
        // TODO: Implement
    }
    
    private static void verifyGymOwner(Scanner scanner) {
        // TODO: Implement
    }
    
    private static void viewAllUsers() {
        // TODO: Implement
    }
    
    private static void viewAllBookings() {
        // TODO: Implement
    }
    
    private static void updateProfile(Scanner scanner, Admin admin) {
        // TODO: Implement
    }
    
    private static int getIntInput(Scanner scanner) {
        // TODO: Implement
        return -1;
    }
}
