package com.flipfit.client;

import com.flipfit.business.*;
import com.flipfit.bean.*;

import java.util.Scanner;

public class FlipFitApplication {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserService userService = new UserServiceImpl();

        int choice;

        do {
            System.out.println("\n===== FLIPFIT APPLICATION =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();

                    System.out.print("Enter Role (ADMIN / OWNER / CUSTOMER): ");
                    String role = sc.nextLine().toUpperCase();

                    User user = null;

                    if (role.equals("ADMIN")) {
                        user = new Admin();
                    } 
                    else if (role.equals("OWNER")) {
                        user = new GymOwner();
                    } 
                    else if (role.equals("CUSTOMER")) {
                        user = new GymCustomer();
                    } 
                    else {
                        System.out.println("Invalid Role!");
                        break;
                    }

                    user.setName(name);
                    user.setEmail(email);
                    user.setPassword(password);

                    userService.register(user);
                    System.out.println("Registration Successful!");

                    break;

                case 2:
                    System.out.print("Enter Email: ");
                    String loginEmail = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String loginPassword = sc.nextLine();

                    User loggedInUser = userService.login(loginEmail, loginPassword);

                    if (loggedInUser == null) {
                        System.out.println("Invalid Credentials!");
                        break;
                    }

                    System.out.println("Login Successful!");

                    // ROLE BASED ROUTING
                    if (loggedInUser instanceof Admin) {
                        AdminMenu.showMenu(loggedInUser.getId());
                    } 
                    else if (loggedInUser instanceof GymOwner) {
                        GymOwnerMenu.showMenu(loggedInUser.getId());
                    } 
                    else if (loggedInUser instanceof GymCustomer) {
                        GymCustomerMenu.showMenu(loggedInUser.getId());
                    }

                    break;

                case 0:
                    System.out.println("Thank you for using FlipFit!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 0);

        sc.close();
    }
}
