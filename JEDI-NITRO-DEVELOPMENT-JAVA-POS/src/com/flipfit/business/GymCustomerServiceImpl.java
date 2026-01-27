package com.flipfit.business;

import com.flipfit.bean.*;
import com.flipfit.dao.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GymCustomerServiceImpl implements GymCustomerService {

    private GymCustomerDAO customerDAO = new GymCustomerDAOImpl();
    private GymCenterDAO gymCenterDAO = new GymCenterDAOImpl();
    private BookingService bookingService = new BookingServiceImpl();

    // ---------------- CUSTOMER ----------------

    @Override
    public boolean registerCustomer(GymCustomer customer) {
        return customerDAO.addCustomer(customer);
    }

    @Override
    public GymCustomer getCustomerById(int customerId) {
        return customerDAO.getCustomerById(customerId);
    }

    // ---------------- SLOT VIEW ----------------

    @Override
    public List<Slot> viewSlots() {

        List<Slot> allSlots = new ArrayList<>();

        List<GymCenter> centers = gymCenterDAO.getApprovedGymCenters();

        for (GymCenter center : centers) {
            allSlots.addAll(center.getSlots());
        }

        return allSlots;
    }

    @Override
    public List<Slot> viewSlotsByCenter(int centerId) {
        return gymCenterDAO.getAvailableSlots(centerId);
    }

    // ---------------- CENTER VIEW ----------------

    @Override
    public List<GymCenter> viewCenters() {
        return gymCenterDAO.getAllGymCenters();
    }

    @Override
    public List<GymCenter> viewApprovedCenters() {
        return gymCenterDAO.getApprovedGymCenters();
    }

    // ---------------- BOOKING ----------------

    @Override
    public Booking bookSlot(int customerId, int slotId, int centerId) {

        LocalDate today = LocalDate.now();

        Booking booking = bookingService.createBooking(customerId, slotId, centerId, today);

        bookingService.confirmBooking(booking.getBookingId());

        return booking;
    }

    @Override
    public boolean cancelSlot(int bookingId) {
        return bookingService.cancelBooking(bookingId);
    }

    // ---------------- WORKOUT PLAN ----------------

    @Override
    public List<Booking> viewWorkoutPlan(int customerId) {
        return bookingService.getBookingsByUser(customerId);
    }

    @Override
    public List<Booking> getCustomerBookings(int customerId) {
        return bookingService.getBookingsByUser(customerId);
    }
}
