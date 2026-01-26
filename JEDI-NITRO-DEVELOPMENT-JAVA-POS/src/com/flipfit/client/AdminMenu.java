package com.flipfit.client;

import com.flipfit.business.*;
import java.util.Scanner;

public class AdminMenu {

    public static void showMenu(int adminId) {

        Scanner sc = new Scanner(System.in);
        AdminService adminService = new AdminServiceImpl();

        int choice;

        do {
            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. View All Users");
            System.out.println("2. View Pending Gym Centers");
            System.out.println("3. Approve Gym Center");
            System.out.println("4. Monthly Report");
            System.out.println("0. Logout");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    adminService.viewAllUsers().forEach(u -> System.out.println(u.getName()));
                    break;

                case 2:
                    adminService.viewPendingGymRequest().forEach(c -> System.out.println(c.getName()));
                    break;

                case 3:
                    System.out.print("Enter Center ID: ");
                    int centerId = sc.nextInt();

                    if (adminService.approveRequest(centerId)) {
                        System.out.println("Center Approved!");
                    } 
                    else {
                        System.out.println("Approval Failed!");
                    }

                    break;

                case 4:
                    System.out.println(adminService.getMonthlyDetails());
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
