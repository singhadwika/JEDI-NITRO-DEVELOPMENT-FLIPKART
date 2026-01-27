package com.flipfit.dao;

import com.flipfit.bean.Slot;
import com.flipfit.helper.DatabaseConnector;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SlotDAOImpl implements SlotDAO {

    @Override
    public Slot createSlot(int centerId, Slot slot) {
        String query = "INSERT INTO Slot (startTime, endTime, totalSeats, availableSeats, centerId) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setTime(1, Time.valueOf(slot.getStartTime()));
            stmt.setTime(2, Time.valueOf(slot.getEndTime()));
            stmt.setInt(3, slot.getTotalSeats());
            stmt.setInt(4, slot.getTotalSeats()); // Available seats initially equal total seats
            stmt.setInt(5, centerId);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        slot.setSlotId(rs.getInt(1));
                    }
                }
                return slot;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Slot getSlotById(int slotId) {
        String query = "SELECT * FROM Slot WHERE slotId = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, slotId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToSlot(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Slot> getSlotsByCenter(int centerId) {
        List<Slot> slots = new ArrayList<>();
        String query = "SELECT * FROM Slot WHERE centerId = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, centerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    slots.add(mapResultSetToSlot(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slots;
    }

    @Override
    public List<Slot> getAvailableSlotsByCenter(int centerId) {
        List<Slot> slots = new ArrayList<>();
        String query = "SELECT * FROM Slot WHERE centerId = ? AND availableSeats > 0";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, centerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    slots.add(mapResultSetToSlot(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slots;
    }

    @Override
    public boolean lockSlot(int slotId) {
        // Atomic decrement of seats to prevent overbooking
        String query = "UPDATE Slot SET availableSeats = availableSeats - 1 WHERE slotId = ? AND availableSeats > 0";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, slotId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean unlockSlot(int slotId) {
        String query = "UPDATE Slot SET availableSeats = availableSeats + 1 WHERE slotId = ? AND availableSeats < totalSeats";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, slotId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteSlot(int slotId) {
        String query = "DELETE FROM Slot WHERE slotId = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, slotId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isSlotAvailable(int slotId) {
        String query = "SELECT availableSeats FROM Slot WHERE slotId = ? AND availableSeats > 0";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, slotId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateSlot(int slotId, LocalTime startTime, LocalTime endTime, int totalSeats) {
        String query = "UPDATE Slot SET startTime = ?, endTime = ?, totalSeats = ?, availableSeats = ? WHERE slotId = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setTime(1, Time.valueOf(startTime));
            stmt.setTime(2, Time.valueOf(endTime));
            stmt.setInt(3, totalSeats);
            stmt.setInt(4, totalSeats);
            stmt.setInt(5, slotId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Slot mapResultSetToSlot(ResultSet rs) throws SQLException {
        Slot slot = new Slot();
        slot.setSlotId(rs.getInt("slotId"));
        slot.setStartTime(rs.getTime("startTime").toLocalTime());
        slot.setEndTime(rs.getTime("endTime").toLocalTime());
        slot.setTotalSeats(rs.getInt("totalSeats"));
        slot.setAvailableSeats(rs.getInt("availableSeats"));
        return slot;
    }
}