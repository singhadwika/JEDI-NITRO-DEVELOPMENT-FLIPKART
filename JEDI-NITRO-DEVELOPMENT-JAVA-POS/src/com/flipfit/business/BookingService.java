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
        // TODO: Implement
        return null;
    }
    
    @Override
    public boolean confirmBooking(int bookingId) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public boolean cancelBooking(int bookingId) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public Booking getBookingById(int bookingId) {
        // TODO: Implement
        return null;
    }
    
    @Override
    public List<Booking> getBookingsByUser(int userId) {
        // TODO: Implement
        return null;
    }
    
    @Override
    public List<Booking> getBookingsBySlot(int slotId) {
        // TODO: Implement
        return null;
    }
    
    @Override
    public List<Booking> getBookingsByCenter(int centerId) {
        // TODO: Implement
        return null;
    }
    
    @Override
    public List<Booking> getBookingsByDate(LocalDate date) {
        // TODO: Implement
        return null;
    }
}
