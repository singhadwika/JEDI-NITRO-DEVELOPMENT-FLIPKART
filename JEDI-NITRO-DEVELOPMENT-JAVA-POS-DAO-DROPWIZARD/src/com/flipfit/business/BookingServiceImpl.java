package com.flipfit.business;

import com.flipfit.bean.Booking;
import com.flipfit.dao.BookingDAO;
import com.flipfit.dao.BookingDAOImpl;

import java.time.LocalDate;
import java.util.List;

public class BookingServiceImpl implements BookingService {

    private BookingDAO bookingDAO = new BookingDAOImpl();

    @Override
    public Booking createBooking(int userId, int slotId, int centerId, LocalDate bookingDate) {

        Booking booking = new Booking();

        booking.setUserId(userId);
        booking.setSlotId(slotId);
        booking.setCenterId(centerId);
        booking.setBookingDate(bookingDate);
        booking.setStatus(false); // Initially unconfirmed

        return bookingDAO.addBooking(booking);
    }

    @Override
    public boolean confirmBooking(int bookingId) {
        return bookingDAO.confirmBooking(bookingId);
    }

    @Override
    public boolean cancelBooking(int bookingId) {
        return bookingDAO.cancelBooking(bookingId);
    }

    @Override
    public Booking getBookingById(int bookingId) {
        return bookingDAO.getBookingById(bookingId);
    }

    @Override
    public List<Booking> getBookingsByUser(int userId) {
        return bookingDAO.getBookingsByUser(userId);
    }

    @Override
    public List<Booking> getBookingsBySlot(int slotId) {
        return bookingDAO.getBookingsBySlot(slotId);
    }

    @Override
    public List<Booking> getBookingsByCenter(int centerId) {
        return bookingDAO.getBookingsByCenter(centerId);
    }

    @Override
    public List<Booking> getBookingsByDate(LocalDate date) {
        return bookingDAO.getBookingsByDate(date);
    }
}
