package com.flipfit.client;

import com.flipfit.business.*;

import java.util.Scanner;

public class GymCustomerMenu {

    public static void showMenu(int customerId) {

        Scanner sc = new Scanner(System.in);

        GymCustomerService customerService = new GymCustomerServiceImpl();

        int choice;

        do {
            System.out.println("\n===== CUSTOMER MENU =====");
            System.out.println("1. View Gym Centers");
            System.out.println("2. View Slots By Center");
            System.out.println("3. Book Slot");
            System.out.println("4. View My Bookings");
            System.out.println("0. Logout");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    customerService.viewApprovedCenters().forEach(c -> System.out.println(c.getCenterId() + " " + c.getName()));
                    break;

                case 2:
                    System.out.print("Enter Center ID: ");
                    int centerId = sc.nextInt();

                    customerService.viewSlotsByCenter(centerId).forEach(s -> System.out.println(s.getSlotId() + " Seats: " + s.getAvailableSeats()));
                    
                    break;

                case 3:
                    System.out.print("Enter Center ID: ");
                    int cId = sc.nextInt();

                    System.out.print("Enter Slot ID: ");
                    int slotId = sc.nextInt();

                    customerService.bookSlot(customerId, slotId, cId);

                    System.out.println("Slot Booked!");
                    break;

                case 4:
                    customerService.getCustomerBookings(customerId).forEach(b -> System.out.println("Booking ID: " + b.getBookingId()));
                    break;

                case 0:
                    System.out.println("Logged out!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 0);
    }
}
