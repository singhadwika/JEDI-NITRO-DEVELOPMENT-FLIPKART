package com.flipfit.helper;

import com.flipfit.bean.*;
import com.flipfit.business.*;

import java.time.LocalTime;

public class MockDataLoader {

    public static void loadMockData() {

        System.out.println("Loading FlipFit Mock Data...");

        UserService userService = new UserServiceImpl();
        AdminService adminService = new AdminServiceImpl();
        GymOwnerService ownerService = new GymOwnerServiceImpl();
        GymCustomerService customerService = new GymCustomerServiceImpl();
        GymCenterService centerService = new GymCenterServiceImpl();
        SlotService slotService = new SlotServiceImpl();
        BookingService bookingService = new BookingServiceImpl();

        // ------------------ ADMIN ------------------
        // Note: Default admin is already inserted via schema.sql

        // ------------------ GYM OWNERS ------------------

        GymOwner owner1 = new GymOwner();
        owner1.setName("Ayush Samal");
        owner1.setEmail("ayush@flipfit.com");
        owner1.setPassword("ayush123");
        owner1.setRole("GYM_OWNER");
        userService.register(owner1);

        GymOwner owner2 = new GymOwner();
        owner2.setName("Anshuman Mahabhoi");
        owner2.setEmail("anshuman@flipfit.com");
        owner2.setPassword("anshuman123");
        owner2.setRole("GYM_OWNER");
        userService.register(owner2);

        // ------------------ CUSTOMERS ------------------

        GymCustomer customer1 = new GymCustomer();
        customer1.setName("Saumyajeet");
        customer1.setEmail("saumyajeet@gmail.com");
        customer1.setPassword("saumyajeet123");
        customer1.setRole("GYM_CUSTOMER");
        userService.register(customer1);

        GymCustomer customer2 = new GymCustomer();
        customer2.setName("Aman Rai");
        customer2.setEmail("aman@gmail.com");
        customer2.setPassword("aman123");
        customer2.setRole("GYM_CUSTOMER");
        userService.register(customer2);

        // ------------------ GYM CENTERS ------------------

        GymCenter center1 = new GymCenter();
        center1.setName("FlipFit HSR");
        center1.setLocation("Bangalore");

        ownerService.addGymCenter(owner1.getId(), center1);

        GymCenter center2 = new GymCenter();
        center2.setName("FlipFit Indiranagar");
        center2.setLocation("Bangalore");

        ownerService.addGymCenter(owner2.getId(), center2);

        // Admin approves centers
        adminService.approveRequest(center1.getCenterId());
        adminService.approveRequest(center2.getCenterId());

        // ------------------ SLOTS ------------------

        slotService.createSlot(
                center1.getCenterId(),
                LocalTime.of(6, 0),
                LocalTime.of(7, 0),
                5
        );

        slotService.createSlot(
                center1.getCenterId(),
                LocalTime.of(7, 0),
                LocalTime.of(8, 0),
                5
        );

        slotService.createSlot(
                center2.getCenterId(),
                LocalTime.of(6, 0),
                LocalTime.of(7, 0),
                3
        );

        // ------------------ BOOKINGS ------------------

        bookingService.createBooking(
                customer1.getId(),
                1,
                center1.getCenterId(),
                java.time.LocalDate.now()
        );

        bookingService.confirmBooking(1);

        bookingService.createBooking(
                customer2.getId(),
                2,
                center1.getCenterId(),
                java.time.LocalDate.now()
        );

        bookingService.confirmBooking(2);
        
        System.out.println("Mock Data Loaded Successfully!");
    }
}
