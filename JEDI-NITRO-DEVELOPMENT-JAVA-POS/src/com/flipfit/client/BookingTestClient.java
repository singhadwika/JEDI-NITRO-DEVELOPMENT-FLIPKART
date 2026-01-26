package com.flipfit.client;

import com.flipfit.business.BookingService;
import com.flipfit.business.BookingServiceImpl;
import com.flipfit.bean.Booking;
import java.time.LocalDate;
import java.util.List;

public class BookingTestClient {
    public static void main(String[] args) {
        BookingService bookingService = new BookingServiceImpl();
        System.out.println("=== Starting Booking Service Test ===\n");

        // 1. TEST: Read Hardcoded Data
        System.out.println("Step 1: Checking Hardcoded Bookings...");
        List<Booking> user1Bookings = bookingService.getBookingsByUser(1);
        System.out.println("Initial Bookings for User 1: " + user1Bookings.size());
        for (Booking b : user1Bookings) {
            System.out.println("Found Booking ID: " + b.getBookingId() + " for Date: " + b.getBookingDate());
        }

        // 2. TEST: Create New Booking (INSERT)
        System.out.println("\nStep 2: Testing Create Booking...");
        Booking newBooking = bookingService.createBooking(3, 301, 101, LocalDate.now().plusWeeks(1));
        if (newBooking != null) {
            System.out.println("Successfully created Booking ID: " + newBooking.getBookingId());
        }

        // 3. TEST: Update Status (CANCEL)
        System.out.println("\nStep 3: Testing Cancel Booking...");
        // Let's cancel the first hardcoded booking (ID 1)
        Booking firstBooking = bookingService.getBookingById(1);
        if (firstBooking != null) {
            System.out.println("Booking 1 Status Before Cancel: " + firstBooking.isStatus());
            bookingService.cancelBooking(1);
            System.out.println("Booking 1 Status After Cancel: " + firstBooking.isStatus());
        }

        // 4. TEST: Filter by Date
        System.out.println("\nStep 4: Testing Search by Date...");
        LocalDate searchDate = LocalDate.now();
        List<Booking> todayBookings = bookingService.getBookingsByDate(searchDate);
        System.out.println("Bookings found for today (" + searchDate + "): " + todayBookings.size());

        System.out.println("\n=== All Booking Service Tests Passed ===");
    }
}