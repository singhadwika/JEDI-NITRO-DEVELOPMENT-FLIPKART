package com.flipfit.dao;

import com.flipfit.bean.Slot;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SlotDAOImpl implements SlotDAO {

    @Override
    public Slot createSlot(int centerId, Slot slot) {
        String sql = "INSERT INTO Slot (start_time, end_time, total_seats, available_seats, center_id) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setTime(1, Time.valueOf(slot.getStartTime()));
            pstmt.setTime(2, Time.valueOf(slot.getEndTime()));
            pstmt.setInt(3, slot.getTotalSeats());
            pstmt.setInt(4, slot.getAvailableSeats());
            pstmt.setInt(5, centerId);
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        slot.setSlotId(generatedKeys.getInt(1));
                        slot.setCenterId(centerId);
                        return slot;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error creating slot: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Slot getSlotById(int slotId) {
        String sql = "SELECT * FROM Slot WHERE slot_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, slotId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToSlot(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting slot by ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Slot> getSlotsByCenter(int centerId) {
        List<Slot> slots = new ArrayList<>();
        String sql = "SELECT * FROM Slot WHERE center_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, centerId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    slots.add(mapResultSetToSlot(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting slots by center: " + e.getMessage());
        }
        return slots;
    }

    @Override
    public List<Slot> getAvailableSlotsByCenter(int centerId) {
        List<Slot> slots = new ArrayList<>();
        String sql = "SELECT * FROM Slot WHERE center_id = ? AND available_seats > 0";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, centerId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    slots.add(mapResultSetToSlot(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting available slots: " + e.getMessage());
        }
        return slots;
    }

    @Override
    public boolean isSlotAvailable(int slotId) {
        String sql = "SELECT available_seats FROM Slot WHERE slot_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, slotId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("available_seats") > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error checking slot availability: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean lockSlot(int slotId) {
        String sql = "UPDATE Slot SET available_seats = available_seats - 1 WHERE slot_id = ? AND available_seats > 0";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, slotId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error locking slot: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean unlockSlot(int slotId) {
        String sql = "UPDATE Slot SET available_seats = available_seats + 1 WHERE slot_id = ? AND available_seats < total_seats";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, slotId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error unlocking slot: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateSlot(int slotId, LocalTime startTime, LocalTime endTime, int totalSeats) {
        String sql = "UPDATE Slot SET start_time = ?, end_time = ?, total_seats = ?, " +
                     "available_seats = LEAST(available_seats, ?) WHERE slot_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setTime(1, Time.valueOf(startTime));
            pstmt.setTime(2, Time.valueOf(endTime));
            pstmt.setInt(3, totalSeats);
            pstmt.setInt(4, totalSeats);
            pstmt.setInt(5, slotId);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating slot: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteSlot(int slotId) {
        String sql = "DELETE FROM Slot WHERE slot_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, slotId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting slot: " + e.getMessage());
        }
        return false;
    }

    private Slot mapResultSetToSlot(ResultSet rs) throws SQLException {
        Slot slot = new Slot();
        slot.setSlotId(rs.getInt("slot_id"));
        slot.setStartTime(rs.getTime("start_time").toLocalTime());
        slot.setEndTime(rs.getTime("end_time").toLocalTime());
        slot.setTotalSeats(rs.getInt("total_seats"));
        slot.setAvailableSeats(rs.getInt("available_seats"));
        slot.setCenterId(rs.getInt("center_id"));
        return slot;
    }
}
