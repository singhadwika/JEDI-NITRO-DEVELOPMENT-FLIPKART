package com.flipfit.dao;

import com.flipfit.bean.Booking;
import com.flipfit.utils.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingDAOImpl implements BookingDAO {

    @Override
    public Booking addBooking(Booking booking) {
        String sql = "INSERT INTO Booking (booking_date, status, user_id, slot_id, center_id) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setDate(1, Date.valueOf(booking.getBookingDate()));
            pstmt.setBoolean(2, booking.isStatus());
            pstmt.setInt(3, booking.getUserId());
            pstmt.setInt(4, booking.getSlotId());
            pstmt.setInt(5, booking.getCenterId());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        booking.setBookingId(generatedKeys.getInt(1));
                        return booking;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error adding booking: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean confirmBooking(int bookingId) {
        String sql = "UPDATE Booking SET status = TRUE WHERE booking_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, bookingId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error confirming booking: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean cancelBooking(int bookingId) {
        String sql = "UPDATE Booking SET status = FALSE WHERE booking_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, bookingId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error cancelling booking: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Booking getBookingById(int bookingId) {
        String sql = "SELECT * FROM Booking WHERE booking_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, bookingId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToBooking(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting booking by ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Booking> getBookingsByUser(int userId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM Booking WHERE user_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    bookings.add(mapResultSetToBooking(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting bookings by user: " + e.getMessage());
        }
        return bookings;
    }

    @Override
    public List<Booking> getBookingsBySlot(int slotId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM Booking WHERE slot_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, slotId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    bookings.add(mapResultSetToBooking(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting bookings by slot: " + e.getMessage());
        }
        return bookings;
    }

    @Override
    public List<Booking> getBookingsByCenter(int centerId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM Booking WHERE center_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, centerId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    bookings.add(mapResultSetToBooking(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting bookings by center: " + e.getMessage());
        }
        return bookings;
    }

    @Override
    public List<Booking> getBookingsByDate(LocalDate date) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM Booking WHERE booking_date = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDate(1, Date.valueOf(date));
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    bookings.add(mapResultSetToBooking(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting bookings by date: " + e.getMessage());
        }
        return bookings;
    }

    private Booking mapResultSetToBooking(ResultSet rs) throws SQLException {
        Booking booking = new Booking();
        booking.setBookingId(rs.getInt("booking_id"));
        booking.setBookingDate(rs.getDate("booking_date").toLocalDate());
        booking.setStatus(rs.getBoolean("status"));
        booking.setUserId(rs.getInt("user_id"));
        booking.setSlotId(rs.getInt("slot_id"));
        booking.setCenterId(rs.getInt("center_id"));
        return booking;
    }
}
