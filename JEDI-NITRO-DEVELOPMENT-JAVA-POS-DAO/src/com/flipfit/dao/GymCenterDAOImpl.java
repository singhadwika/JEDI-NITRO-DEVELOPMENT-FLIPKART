package com.flipfit.dao;

import com.flipfit.bean.GymCenter;
import com.flipfit.bean.Slot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GymCenterDAOImpl implements GymCenterDAO {

    @Override
    public boolean addGymCenter(GymCenter center) {
        String sql = "INSERT INTO GymCenter (name, location, is_approved, owner_id) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, center.getName());
            pstmt.setString(2, center.getLocation());
            pstmt.setBoolean(3, false); // default pending
            pstmt.setInt(4, center.getOwnerId());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        center.setCenterId(generatedKeys.getInt(1));
                        center.setApproved(false);
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error adding gym center: " + e.getMessage());
        }
        return false;
    }

    @Override
    public GymCenter getGymCenterById(int centerId) {
        String sql = "SELECT * FROM GymCenter WHERE center_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, centerId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToGymCenter(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting gym center by ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<GymCenter> getAllGymCenters() {
        List<GymCenter> centers = new ArrayList<>();
        String sql = "SELECT * FROM GymCenter";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                centers.add(mapResultSetToGymCenter(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all gym centers: " + e.getMessage());
        }
        return centers;
    }

    @Override
    public List<GymCenter> getApprovedGymCenters() {
        List<GymCenter> centers = new ArrayList<>();
        String sql = "SELECT * FROM GymCenter WHERE is_approved = TRUE";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                centers.add(mapResultSetToGymCenter(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error getting approved gym centers: " + e.getMessage());
        }
        return centers;
    }

    @Override
    public List<GymCenter> getPendingGymCenters() {
        List<GymCenter> centers = new ArrayList<>();
        String sql = "SELECT * FROM GymCenter WHERE is_approved = FALSE";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                centers.add(mapResultSetToGymCenter(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error getting pending gym centers: " + e.getMessage());
        }
        return centers;
    }

    @Override
    public List<GymCenter> getGymCentersByOwner(int ownerId) {
        List<GymCenter> centers = new ArrayList<>();
        String sql = "SELECT * FROM GymCenter WHERE owner_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, ownerId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    centers.add(mapResultSetToGymCenter(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting gym centers by owner: " + e.getMessage());
        }
        return centers;
    }

    @Override
    public boolean updateGymCenter(int centerId, String name, String location) {
        String sql = "UPDATE GymCenter SET name = ?, location = ? WHERE center_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, name);
            pstmt.setString(2, location);
            pstmt.setInt(3, centerId);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating gym center: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean approveGymCenter(int centerId) {
        String sql = "UPDATE GymCenter SET is_approved = TRUE WHERE center_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, centerId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error approving gym center: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean rejectGymCenter(int centerId) {
        String sql = "DELETE FROM GymCenter WHERE center_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, centerId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error rejecting gym center: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Slot> getAvailableSlots(int centerId) {
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
    public boolean requestForApproval(int centerId) {
        String sql = "UPDATE GymCenter SET is_approved = FALSE WHERE center_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, centerId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error requesting approval: " + e.getMessage());
        }
        return false;
    }

    private GymCenter mapResultSetToGymCenter(ResultSet rs) throws SQLException {
        GymCenter center = new GymCenter();
        center.setCenterId(rs.getInt("center_id"));
        center.setName(rs.getString("name"));
        center.setLocation(rs.getString("location"));
        center.setApproved(rs.getBoolean("is_approved"));
        center.setOwnerId(rs.getInt("owner_id"));
        center.setSlots(new ArrayList<>()); // Slots loaded separately
        return center;
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
