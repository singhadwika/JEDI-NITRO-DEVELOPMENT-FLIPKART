package com.flipfit.client;

import com.flipfit.bean.*;
import com.flipfit.business.*;
import java.util.Scanner;

/**
 * Menu handler for Gym Customer operations.
 */
public class GymCustomerMenu {
    
    private static GymCustomerService customerService = new GymCustomerService();
    private static BookingService bookingService = new BookingService();
    private static WaitlistService waitlistService = new WaitlistService();
    private static NotificationService notificationService = new NotificationService();
    
    public static void showMenu(Scanner scanner, GymCustomer customer) {
        int choice;
        do {
            System.out.println();
            System.out.println("===== Gym Customer Menu =====");
            System.out.println("1. View Gym Centers");
            System.out.println("2. View Available Slots");
            System.out.println("3. Book Slot");
            System.out.println("4. Cancel Booking");
            System.out.println("5. View Workout Plan");
            System.out.println("6. View Notifications");
            System.out.println("7. Update Profile");
            System.out.println("8. Logout");
            System.out.print("Enter choice: ");

            choice = getIntInput(scanner);
            switch (choice) {
                case 1:
                    viewGymCenters();
                    break;
                case 2:
                    viewAvailableSlots(scanner);
                    break;
                case 3:
                    bookSlot(scanner, customer);
                    break;
                case 4:
                    cancelBooking(scanner, customer);
                    break;
                case 5:
                    viewWorkoutPlan(customer);
                    break;
                case 6:
                    viewNotifications(customer);
                    break;
                case 7:
                    updateProfile(scanner, customer);
                    break;
                case 8:
                    System.out.println("Logging out (GymCustomer)...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);
    }
    
    private static void viewGymCenters() {
        System.out.println("GymCustomerMenu.viewGymCenters called (stub)");
        System.out.println("Calling GymCustomerService.viewCenters (stub)...");
        customerService.viewCenters();
    }
    
    private static void viewAvailableSlots(Scanner scanner) {
        System.out.println("GymCustomerMenu.viewAvailableSlots called (stub)");
        System.out.print("Enter Center ID: ");
        int centerId = getIntInput(scanner);
        System.out.println("Calling GymCustomerService.viewSlotsByCenter (stub)...");
        customerService.viewSlotsByCenter(centerId);
    }
    
    private static void bookSlot(Scanner scanner, GymCustomer customer) {
        System.out.println("GymCustomerMenu.bookSlot called (stub). customer=" + String.valueOf(customer));
        System.out.print("Enter Center ID: ");
        int centerId = getIntInput(scanner);
        System.out.print("Enter Slot ID: ");
        int slotId = getIntInput(scanner);
        System.out.println("Calling GymCustomerService.bookSlot (stub)...");
        customerService.bookSlot(0, slotId, centerId);
        System.out.println("If slot is full, adding to waitlist (stub)...");
        waitlistService.addToWaitlist(0, slotId);
    }
    
    private static void cancelBooking(Scanner scanner, GymCustomer customer) {
        System.out.println("GymCustomerMenu.cancelBooking called (stub). customer=" + String.valueOf(customer));
        System.out.print("Enter Booking ID: ");
        int bookingId = getIntInput(scanner);
        System.out.println("Calling GymCustomerService.cancelSlot (stub)...");
        customerService.cancelSlot(bookingId);
    }
    
    private static void viewWorkoutPlan(GymCustomer customer) {
        System.out.println("GymCustomerMenu.viewWorkoutPlan called (stub). customer=" + String.valueOf(customer));
        System.out.println("Calling GymCustomerService.viewWorkoutPlan (stub)...");
        customerService.viewWorkoutPlan(0);
    }
    
    private static void viewNotifications(GymCustomer customer) {
        System.out.println("GymCustomerMenu.viewNotifications called (stub). customer=" + String.valueOf(customer));
        System.out.println("Calling NotificationService.getNotificationsByUser (stub)...");
        notificationService.getNotificationsByUser(0);
    }
    
    private static void updateProfile(Scanner scanner, GymCustomer customer) {
        System.out.println("GymCustomerMenu.updateProfile called (stub). customer=" + String.valueOf(customer));
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
