package com.flipfit.business;

import com.flipfit.bean.Booking;
import java.time.LocalDate;
import java.util.List;

/**
 * The Interface BookingService.
 * Defines business logic operations for booking management in the FlipFit system.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public interface BookingService {
    
    /**
     * Creates a new booking for a user at a specific slot.
     *
     * @param userId the user id
     * @param slotId the slot id
     * @param centerId the center id
     * @param bookingDate the booking date
     * @return the created booking
     */
    Booking createBooking(int userId, int slotId, int centerId, LocalDate bookingDate);
    
    /**
     * Confirms an existing booking.
     *
     * @param bookingId the booking id
     * @return true if confirmation is successful, false otherwise
     */
    public boolean confirmBooking(int bookingId);
    
    /**
     * Cancels an existing booking.
     *
     * @param bookingId the booking id
     * @return true if cancellation is successful, false otherwise
     */
    public boolean cancelBooking(int bookingId);
    
    /**
     * Retrieves a booking by its ID.
     *
     * @param bookingId the booking id
     * @return the booking details
     */
    public Booking getBookingById(int bookingId);
    
    /**
     * Retrieves all bookings made by a specific user.
     *
     * @param userId the user id
     * @return list of bookings by the user
     */
    public List<Booking> getBookingsByUser(int userId);
    
    /**
     * Retrieves all bookings for a specific slot.
     *
     * @param slotId the slot id
     * @return list of bookings for the slot
     */
    public List<Booking> getBookingsBySlot(int slotId);
    
    /**
     * Retrieves all bookings for a specific gym center.
     *
     * @param centerId the center id
     * @return list of bookings for the center
     */
    public List<Booking> getBookingsByCenter(int centerId);
    
    /**
     * Retrieves all bookings for a specific date.
     *
     * @param date the booking date
     * @return list of bookings for the date
     */
    public List<Booking> getBookingsByDate(LocalDate date);
}
