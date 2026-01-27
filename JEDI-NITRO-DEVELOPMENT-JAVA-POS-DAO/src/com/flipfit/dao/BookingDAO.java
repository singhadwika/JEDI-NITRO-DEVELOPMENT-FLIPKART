package com.flipfit.dao;

import com.flipfit.bean.Booking;
import java.time.LocalDate;
import java.util.List;

public interface BookingDAO {

    Booking addBooking(Booking booking);

    boolean confirmBooking(int bookingId);

    boolean cancelBooking(int bookingId);

    Booking getBookingById(int bookingId);

    List<Booking> getBookingsByUser(int userId);

    List<Booking> getBookingsBySlot(int slotId);

    List<Booking> getBookingsByCenter(int centerId);

    List<Booking> getBookingsByDate(LocalDate date);
}
