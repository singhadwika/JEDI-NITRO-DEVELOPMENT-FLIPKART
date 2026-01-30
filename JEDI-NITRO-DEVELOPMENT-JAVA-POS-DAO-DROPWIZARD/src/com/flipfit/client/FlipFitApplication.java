package com.flipfit.client;

import com.flipfit.business.*;
import com.flipfit.bean.*;
import com.flipfit.helper.MockDataLoader;
import com.flipfit.exception.InvalidCredentialsException;
import com.flipfit.exception.RegistrationNotDoneException;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Class FlipFitApplication.
 * Main entry point for the FlipFit application.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class FlipFitApplication {

    /**
     * The main method.
     * Launches the FlipFit application and handles user registration and login.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //        MockDataLoader.loadMockData();
        
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
                        user.setRole("ADMIN");
                    } 
                    else if (role.equals("OWNER")) {
                        user = new GymOwner();
                        user.setRole("GYM_OWNER");
                    } 
                    else if (role.equals("CUSTOMER")) {
                        user = new GymCustomer();
                        user.setRole("GYM_CUSTOMER");
                    } 
                    else {
                        System.out.println("Invalid Role!");
                        break;
                    }

                    user.setName(name);
                    user.setEmail(email);
                    user.setPassword(password);

                    try {
                        userService.register(user);
                        System.out.println("Registration Successful!");
                    } catch (Exception e) {
                        System.out.println("Registration failed: " + e.getMessage());
                    }

                    break;

                case 2:
                    System.out.print("Enter Email: ");
                    String loginEmail = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String loginPassword = sc.nextLine();

                    try {
                        User loggedInUser = userService.login(loginEmail, loginPassword);

                        if (loggedInUser == null) {
                            throw new InvalidCredentialsException("Email or password is incorrect");
                        }

                        // Display welcome message with date and time
                        displayWelcomeMessage(loggedInUser.getName());
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
                    } catch (InvalidCredentialsException e) {
                        System.out.println("Invalid Credentials: " + e.getMessage());
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

    /**
     * Display welcome message with user name and current date/time.
     * Uses Java 17 LocalDateTime and DateTimeFormatter APIs.
     *
     * @param userName the name of the logged-in user
     */
    private static void displayWelcomeMessage(String userName) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = now.format(dateTimeFormatter);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.printf("%-30s %30s%n", "Welcome " + userName, formattedDateTime);
        System.out.println("=".repeat(60) + "\n");
    }
}
