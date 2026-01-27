package com.flipfit.client;

import com.flipfit.business.*;
import com.flipfit.bean.GymCenter;

import java.time.LocalTime;
import java.util.Scanner;

public class GymOwnerMenu {

    public static void showMenu(int ownerId) {

        Scanner sc = new Scanner(System.in);
        GymOwnerService ownerService = new GymOwnerServiceImpl();

        int choice;

        do {
            System.out.println("\n===== GYM OWNER MENU =====");
            System.out.println("1. Add Gym Center");
            System.out.println("2. View My Centers");
            System.out.println("3. Add Slot");
            System.out.println("4. View Bookings");
            System.out.println("0. Logout");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    sc.nextLine();
                    System.out.print("Enter Center Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Location: ");
                    String location = sc.nextLine();

                    GymCenter center = new GymCenter();
                    center.setName(name);
                    center.setLocation(location);

                    ownerService.addGymCenter(ownerId, center);
                    System.out.println("Center Added (Pending Approval)");

                    break;

                case 2:
                    ownerService.getGymCentersByOwner(ownerId).forEach(c -> System.out.println(c.getCenterId() + " " + c.getName()));
                    break;

                case 3:
                    System.out.print("Enter Center ID: ");
                    int centerId = sc.nextInt();

                    System.out.print("Start Hour: ");
                    int sh = sc.nextInt();

                    System.out.print("End Hour: ");
                    int eh = sc.nextInt();

                    System.out.print("Total Seats: ");
                    int seats = sc.nextInt();

                    ownerService.addSlot(centerId, LocalTime.of(sh, 0), LocalTime.of(eh, 0), seats);

                    System.out.println("Slot Added!");
                    break;

                case 4:
                    ownerService.viewBookings(ownerId).forEach(b -> System.out.println("Booking ID: " + b.getBookingId()));
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
