package com.flipfit.business;

import com.flipfit.bean.Booking;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingServiceImpl implements BookingService {

    private static List<Booking> bookingList = new ArrayList<>();
    private static int bookingCounter = 1;

    static {
        // Fix: Use the 6-argument constructor defined in Booking.java
        // Parameters: (bookingId, bookingDate, status, userId, slotId, centerId)
        bookingList.add(new Booking(bookingCounter++, LocalDate.now(), true, 1, 201, 101));
        bookingList.add(new Booking(bookingCounter++, LocalDate.now().plusDays(1), false, 2, 202, 101));
    }

    @Override
    public Booking createBooking(int userId, int slotId, int centerId, LocalDate bookingDate) {
        // Fix: Use the 6-argument constructor here as well
        Booking newBooking = new Booking(
            bookingCounter++, 
            bookingDate, 
            true, 
            userId, 
            slotId, 
            centerId
        );
        
        bookingList.add(newBooking);
        System.out.println("Demo: Booking created successfully for User ID: " + userId);
        return newBooking;
    }

    @Override
    public boolean confirmBooking(int bookingId) {
        for (Booking booking : bookingList) {
            if (booking.getBookingId() == bookingId) {
                booking.setStatus(true);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean cancelBooking(int bookingId) {
        for (Booking booking : bookingList) {
            if (booking.getBookingId() == bookingId) {
                booking.setStatus(false);
                return true;
            }
        }
        return false;
    }

    @Override
    public Booking getBookingById(int bookingId) {
        for (Booking booking : bookingList) {
            if (booking.getBookingId() == bookingId) {
                return booking;
            }
        }
        return null;
    }

    @Override
    public List<Booking> getBookingsByUser(int userId) {
        List<Booking> result = new ArrayList<>();
        for (Booking booking : bookingList) {
            if (booking.getUserId() == userId) {
                result.add(booking);
            }
        }
        return result;
    }

    @Override
    public List<Booking> getBookingsBySlot(int slotId) {
        List<Booking> result = new ArrayList<>();
        for (Booking booking : bookingList) {
            if (booking.getSlotId() == slotId) {
                result.add(booking);
            }
        }
        return result;
    }

    @Override
    public List<Booking> getBookingsByCenter(int centerId) {
        List<Booking> result = new ArrayList<>();
        for (Booking booking : bookingList) {
            if (booking.getCenterId() == centerId) {
                result.add(booking);
            }
        }
        return result;
    }

    @Override
    public List<Booking> getBookingsByDate(LocalDate date) {
        List<Booking> result = new ArrayList<>();
        for (Booking booking : bookingList) {
            if (booking.getBookingDate().equals(date)) {
                result.add(booking);
            }
        }
        return result;
    }
}