package com.flipfit.business;

import com.flipfit.bean.Booking;
import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    
    Booking createBooking(int userId, int slotId, int centerId, LocalDate bookingDate);
    
    public boolean confirmBooking(int bookingId);
    
    public boolean cancelBooking(int bookingId);
    
    public Booking getBookingById(int bookingId);
    
    public List<Booking> getBookingsByUser(int userId);
    
    public List<Booking> getBookingsBySlot(int slotId);
    
    public List<Booking> getBookingsByCenter(int centerId);
    
    public List<Booking> getBookingsByDate(LocalDate date);
}
