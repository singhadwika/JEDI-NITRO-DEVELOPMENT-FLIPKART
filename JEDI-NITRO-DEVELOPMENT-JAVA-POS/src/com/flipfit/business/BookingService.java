package com.flipfit.business;

import com.flipfit.bean.Booking;
import java.time.LocalDate;
import java.util.List;

/**
 * Implementation of BookingServiceInterface.
 */
public class BookingService implements BookingServiceInterface {
    
    @Override
    public Booking createBooking(int userId, int slotId, int centerId, LocalDate bookingDate) {
        System.out.println("BookingService.createBooking called");
        return null;
    }
    
    @Override
    public boolean confirmBooking(int bookingId) {
        System.out.println("BookingService.confirmBooking called");
        return false;
    }
    
    @Override
    public boolean cancelBooking(int bookingId) {
        System.out.println("BookingService.cancelBooking called");
        return false;
    }
    
    @Override
    public Booking getBookingById(int bookingId) {
        System.out.println("BookingService.getBookingById called");
        return null;
    }
    
    @Override
    public List<Booking> getBookingsByUser(int userId) {
        System.out.println("BookingService.getBookingsByUser called");
        return null;
    }
    
    @Override
    public List<Booking> getBookingsBySlot(int slotId) {
        System.out.println("BookingService.getBookingsBySlot called");
        return null;
    }
    
    @Override
    public List<Booking> getBookingsByCenter(int centerId) {
        System.out.println("BookingService.getBookingsByCenter called");
        return null;
    }
    
    @Override
    public List<Booking> getBookingsByDate(LocalDate date) {
        System.out.println("BookingService.getBookingsByDate called");
        return null;
    }
}
