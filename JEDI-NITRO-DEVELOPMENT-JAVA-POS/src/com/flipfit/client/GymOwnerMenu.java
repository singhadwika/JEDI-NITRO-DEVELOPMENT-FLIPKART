package com.flipfit.client;

import com.flipfit.bean.*;
import com.flipfit.business.*;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * Menu handler for Gym Owner operations.
 */
public class GymOwnerMenu {
    
    private static GymOwnerService ownerService = new GymOwnerService();
    private static GymCenterService centerService = new GymCenterService();
    private static NotificationService notificationService = new NotificationService();
    
    public static void showMenu(Scanner scanner, GymOwner owner) {
        int choice;
        do {
            System.out.println();
            System.out.println("===== Gym Owner Menu =====");
            System.out.println("1. View My Centers");
            System.out.println("2. Add Gym Center");
            System.out.println("3. Update Center Details");
            System.out.println("4. Manage Slots");
            System.out.println("5. Add Slot");
            System.out.println("6. Remove Slot");
            System.out.println("7. View Bookings");
            System.out.println("8. Request Approval");
            System.out.println("9. View Notifications");
            System.out.println("10. Update Profile");
            System.out.println("11. Logout");
            System.out.print("Enter choice: ");

            choice = getIntInput(scanner);
            switch (choice) {
                case 1:
                    viewMyCenters(owner);
                    break;
                case 2:
                    addGymCenter(scanner, owner);
                    break;
                case 3:
                    updateCenterDetails(scanner, owner);
                    break;
                case 4:
                    manageSlots(scanner, owner);
                    break;
                case 5:
                    addSlot(scanner, owner);
                    break;
                case 6:
                    removeSlot(scanner, owner);
                    break;
                case 7:
                    viewBookings(owner);
                    break;
                case 8:
                    requestApproval(scanner, owner);
                    break;
                case 9:
                    viewNotifications(owner);
                    break;
                case 10:
                    updateProfile(scanner, owner);
                    break;
                case 11:
                    System.out.println("Logging out (GymOwner)...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 11);
    }
    
    private static void viewMyCenters(GymOwner owner) {
        System.out.println("GymOwnerMenu.viewMyCenters called (stub). owner=" + String.valueOf(owner));
        System.out.println("Calling GymOwnerService.getGymCentersByOwner (stub)...");
        ownerService.getGymCentersByOwner(0);
    }
    
    private static void addGymCenter(Scanner scanner, GymOwner owner) {
        System.out.println("GymOwnerMenu.addGymCenter called (stub). owner=" + String.valueOf(owner));
        System.out.print("Center Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Location: ");
        String location = scanner.nextLine().trim();
        GymCenter center = new GymCenter();
        System.out.println("Calling GymOwnerService.addGymCenter (stub)...");
        ownerService.addGymCenter(0, center);
        System.out.println("GymCenter added (stub). name=" + name + ", location=" + location);
    }
    
    private static void updateCenterDetails(Scanner scanner, GymOwner owner) {
        System.out.println("GymOwnerMenu.updateCenterDetails called (stub). owner=" + String.valueOf(owner));
        System.out.print("Enter Center ID: ");
        int centerId = getIntInput(scanner);
        System.out.print("New Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("New Location: ");
        String location = scanner.nextLine().trim();
        System.out.println("Calling GymOwnerService.updateCenterDetails (stub)...");
        ownerService.updateCenterDetails(centerId, name, location);
    }
    
    private static void manageSlots(Scanner scanner, GymOwner owner) {
        System.out.println("GymOwnerMenu.manageSlots called (stub). owner=" + String.valueOf(owner));
        System.out.print("Enter Center ID: ");
        int centerId = getIntInput(scanner);
        System.out.println("Calling GymOwnerService.manageSlots (stub)...");
        ownerService.manageSlots(centerId);
    }
    
    private static void addSlot(Scanner scanner, GymOwner owner) {
        System.out.println("GymOwnerMenu.addSlot called (stub). owner=" + String.valueOf(owner));
        System.out.print("Enter Center ID: ");
        int centerId = getIntInput(scanner);
        System.out.println("Calling GymOwnerService.addSlot (stub)...");
        ownerService.addSlot(centerId, LocalTime.of(6, 0), LocalTime.of(7, 0), 10);
    }
    
    private static void removeSlot(Scanner scanner, GymOwner owner) {
        System.out.println("GymOwnerMenu.removeSlot called (stub). owner=" + String.valueOf(owner));
        System.out.print("Enter Slot ID: ");
        int slotId = getIntInput(scanner);
        System.out.println("Calling GymOwnerService.removeSlot (stub)...");
        ownerService.removeSlot(slotId);
    }
    
    private static void viewBookings(GymOwner owner) {
        System.out.println("GymOwnerMenu.viewBookings called (stub). owner=" + String.valueOf(owner));
        System.out.println("Calling GymOwnerService.viewBookings (stub)...");
        ownerService.viewBookings(0);
    }
    
    private static void requestApproval(Scanner scanner, GymOwner owner) {
        System.out.println("GymOwnerMenu.requestApproval called (stub). owner=" + String.valueOf(owner));
        System.out.print("Enter Center ID: ");
        int centerId = getIntInput(scanner);
        System.out.println("Calling GymOwnerService.requestApproval (stub)...");
        ownerService.requestApproval(centerId);
    }
    
    private static void viewNotifications(GymOwner owner) {
        System.out.println("GymOwnerMenu.viewNotifications called (stub). owner=" + String.valueOf(owner));
        System.out.println("Calling NotificationService.getNotificationsByUser (stub)...");
        notificationService.getNotificationsByUser(0);
    }
    
    private static void updateProfile(Scanner scanner, GymOwner owner) {
        System.out.println("GymOwnerMenu.updateProfile called (stub). owner=" + String.valueOf(owner));
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
