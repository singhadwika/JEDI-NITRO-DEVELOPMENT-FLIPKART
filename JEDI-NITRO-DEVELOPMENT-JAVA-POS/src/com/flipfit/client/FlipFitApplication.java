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
        // TODO: Implement main application loop
    }
    
    private static void showMainMenu() {
        // TODO: Implement main menu
    }
    
    private static void showUserMenu() {
        // TODO: Implement user menu routing
    }
    
    private static void login() {
        // TODO: Implement login
    }
    
    private static void registerCustomer() {
        // TODO: Implement customer registration
    }
    
    private static void registerGymOwner() {
        // TODO: Implement gym owner registration
    }
    
    public static void logout() {
        // TODO: Implement logout
    }
    
    private static int getIntInput() {
        // TODO: Implement input handling
        return -1;
    }
}
