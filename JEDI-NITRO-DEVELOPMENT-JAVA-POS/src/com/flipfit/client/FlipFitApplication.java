package com.flipfit.client;

import com.flipfit.bean.*;
import com.flipfit.business.*;
import java.util.Scanner;

/**
 * Main FlipFit Application Client.
 */
public class FlipFitApplication {
    
    private static Scanner scanner = new Scanner(System.in);
    private static UserService userService = new UserService();
    private static GymCustomerService customerService = new GymCustomerService();
    private static GymOwnerService ownerService = new GymOwnerService();
    private static AdminService adminService = new AdminService();
    private static User currentUser = null;
    
    public static void main(String[] args) {
        int choice;
        do {
            showMainMenu();
            System.out.print("Enter choice: ");
            choice = getIntInput();

            switch (choice) {
                case 1:
                    System.out.println("press 1 :-> Ask for Login");
                    login();
                    break;
                case 2:
                    registerCustomer();
                    break;
                case 3:
                    registerGymOwner();
                    break;
                case 4:
                    changePassword();
                    break;
                case 5:
                    System.out.println("Exiting FlipFit Application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }
    
    private static void showMainMenu() {
        System.out.println();
        System.out.println("Welcome to the Flipfit Application for GYM");
        System.out.println();
        System.out.println("1. Login");
        System.out.println("2. Registration of the GymCustomer");
        System.out.println("3. Registration of the GymOwner");
        System.out.println("4. change password");
        System.out.println("5. Exit");
        System.out.println();
    }
    
    private static void showUserMenu() {
        System.out.println("Menu will display based on Auth & Role Selection :-->");
    }
    
    private static void login() {
        System.out.print("Username :- ");
        String username = scanner.nextLine().trim();

        System.out.print("password :-- ");
        String password = scanner.nextLine().trim();

        System.out.println("Role :--- 1. GYMOwner 2. GymCustomer 3. GymAdmin as per per choice (Switch case).");
        System.out.print("Enter role: ");
        int roleChoice = getIntInput();

        // Service call stub (no real auth logic here)
        System.out.println("Authenticating (stub)...");
        userService.login(username, password);

        // Create a dummy user object just for routing menus.
        switch (roleChoice) {
            case 1:
                currentUser = new GymOwner();
                break;
            case 2:
                currentUser = new GymCustomer();
                break;
            case 3:
                currentUser = new Admin();
                break;
            default:
                currentUser = null;
        }

        showUserMenu();

        if (roleChoice == 3 && currentUser instanceof Admin) {
            AdminMenu.showMenu(scanner, (Admin) currentUser);
        } else if (roleChoice == 2 && currentUser instanceof GymCustomer) {
            GymCustomerMenu.showMenu(scanner, (GymCustomer) currentUser);
        } else if (roleChoice == 1 && currentUser instanceof GymOwner) {
            GymOwnerMenu.showMenu(scanner, (GymOwner) currentUser);
        } else {
            System.out.println("Invalid role selected. Returning to main menu.");
        }
    }
    
    private static void registerCustomer() {
        System.out.println("Registration of the GymCustomer (stub)");
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        System.out.println("Calling UserService.register (stub)...");
        userService.register(new User());
        System.out.println("GymCustomer registered (stub). name=" + name + ", email=" + email);
    }
    
    private static void registerGymOwner() {
        System.out.println("Registration of the GymOwner (stub)");
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        System.out.println("Calling UserService.register (stub)...");
        userService.register(new User());
        System.out.println("GymOwner registered (stub). name=" + name + ", email=" + email);
    }
    
    private static void changePassword() {
        System.out.println("change password (stub)");
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Old password: ");
        String oldPassword = scanner.nextLine().trim();
        System.out.print("New password: ");
        String newPassword = scanner.nextLine().trim();

        System.out.println("Calling UserService.updateProfile (stub)...");
        userService.updateProfile(0, "NA", email, newPassword);
        System.out.println("Password updated (stub) for email=" + email);
    }
    
    public static void logout() {
        System.out.println("FlipFitApplication.logout called");
        System.out.println("Calling UserService.logout (stub)...");
        userService.logout(0);
        currentUser = null;
    }
    
    private static int getIntInput() {
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
