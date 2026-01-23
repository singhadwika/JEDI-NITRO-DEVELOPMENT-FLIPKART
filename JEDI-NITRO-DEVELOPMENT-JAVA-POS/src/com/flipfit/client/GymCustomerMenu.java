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
        // TODO: Implement customer menu
    }
    
    private static void viewGymCenters() {
        // TODO: Implement
    }
    
    private static void viewAvailableSlots(Scanner scanner) {
        // TODO: Implement
    }
    
    private static void bookSlot(Scanner scanner, GymCustomer customer) {
        // TODO: Implement
    }
    
    private static void cancelBooking(Scanner scanner, GymCustomer customer) {
        // TODO: Implement
    }
    
    private static void viewWorkoutPlan(GymCustomer customer) {
        // TODO: Implement
    }
    
    private static void viewNotifications(GymCustomer customer) {
        // TODO: Implement
    }
    
    private static void updateProfile(Scanner scanner, GymCustomer customer) {
        // TODO: Implement
    }
    
    private static int getIntInput(Scanner scanner) {
        // TODO: Implement
        return -1;
    }
}
