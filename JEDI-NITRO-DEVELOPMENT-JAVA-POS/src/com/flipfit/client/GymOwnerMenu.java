package com.flipfit.client;

import com.flipfit.bean.*;
import com.flipfit.business.*;
import java.util.Scanner;

/**
 * Menu handler for Gym Owner operations.
 */
public class GymOwnerMenu {
    
    private static GymOwnerService ownerService = new GymOwnerService();
    private static GymCenterService centerService = new GymCenterService();
    private static NotificationService notificationService = new NotificationService();
    
    public static void showMenu(Scanner scanner, GymOwner owner) {
        // TODO: Implement owner menu
    }
    
    private static void viewMyCenters(GymOwner owner) {
        // TODO: Implement
    }
    
    private static void addGymCenter(Scanner scanner, GymOwner owner) {
        // TODO: Implement
    }
    
    private static void updateCenterDetails(Scanner scanner, GymOwner owner) {
        // TODO: Implement
    }
    
    private static void manageSlots(Scanner scanner, GymOwner owner) {
        // TODO: Implement
    }
    
    private static void addSlot(Scanner scanner, GymOwner owner) {
        // TODO: Implement
    }
    
    private static void removeSlot(Scanner scanner, GymOwner owner) {
        // TODO: Implement
    }
    
    private static void viewBookings(GymOwner owner) {
        // TODO: Implement
    }
    
    private static void requestApproval(Scanner scanner, GymOwner owner) {
        // TODO: Implement
    }
    
    private static void viewNotifications(GymOwner owner) {
        // TODO: Implement
    }
    
    private static void updateProfile(Scanner scanner, GymOwner owner) {
        // TODO: Implement
    }
    
    private static int getIntInput(Scanner scanner) {
        // TODO: Implement
        return -1;
    }
}
