package com.flipfit.client;

import com.flipfit.business.*;
import com.flipfit.bean.Slot;
import com.flipfit.bean.Booking;

import java.util.List;
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

                    List<Slot> slots = customerService.viewSlotsByCenter(centerId);
                    if (slots.isEmpty()) {
                        System.out.println("No slots available for this center.");
                    } else {
                        slots.forEach(s -> System.out.println("Slot ID: " + s.getSlotId() + " | Time: " + s.getStartTime() + "-" + s.getEndTime() + " | Available Seats: " + s.getAvailableSeats()));
                    }
                    
                    break;

                case 3:
                    System.out.print("Enter Center ID: ");
                    int cId = sc.nextInt();

                    System.out.print("Enter Slot ID: ");
                    int slotId = sc.nextInt();

                    Booking booking = customerService.bookSlot(customerId, slotId, cId);

                    if (booking != null) {
                        System.out.println("Slot Booked! Booking ID: " + booking.getBookingId());
                    } else {
                        System.out.println("Booking failed! Please check if the slot exists and has available seats.");
                    }
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
