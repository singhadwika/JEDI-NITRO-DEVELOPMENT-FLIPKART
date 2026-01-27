package com.flipfit.dao;

import com.flipfit.bean.Booking;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingDAOImpl implements BookingDAO {

    private static List<Booking> bookingList = new ArrayList<>();
    private static int bookingCounter = 1;

    @Override
    public Booking addBooking(Booking booking) {

        booking.setBookingId(bookingCounter++);
        bookingList.add(booking);

        return booking;
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
