package com.flipfit.business;

import com.flipfit.bean.Booking;
import java.time.LocalDate;
import java.util.List;

public interface BookingServiceInterface {
    Booking createBooking(int userId, int slotId, int centerId, LocalDate bookingDate);

    boolean confirmBooking(int bookingId);

    boolean cancelBooking(int bookingId);

    Booking getBookingById(int bookingId);

    List<Booking> getBookingsByUser(int userId);

    List<Booking> getBookingsBySlot(int slotId);

    List<Booking> getBookingsByCenter(int centerId);

    List<Booking> getBookingsByDate(LocalDate date);
}

