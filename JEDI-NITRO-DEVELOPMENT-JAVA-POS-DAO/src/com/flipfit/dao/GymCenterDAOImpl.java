package com.flipfit.dao;

import com.flipfit.bean.GymCenter;
import com.flipfit.bean.Slot;
import com.flipfit.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class GymCenterDAOImpl.
 * DAO implementation for managing gym center database operations.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class GymCenterDAOImpl implements GymCenterDAO {

    /**
     * Adds a new gym center to the database.
     *
     * @param center the gym center to add
     * @return true if the gym center was added successfully, false otherwise
     */
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

    /**
     * Gets a gym center by its ID.
     *
     * @param centerId the gym center ID
     * @return the gym center if found, null otherwise
     */
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

    /**
     * Gets all gym centers from the database.
     *
     * @return list of all gym centers
     */
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

    /**
     * Gets all approved gym centers.
     *
     * @return list of approved gym centers
     */
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

    /**
     * Gets all pending gym centers (not yet approved).
     *
     * @return list of pending gym centers
     */
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

    /**
     * Gets all gym centers owned by a specific owner.
     *
     * @param ownerId the owner ID
     * @return list of gym centers owned by the owner
     */
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

    /**
     * Updates gym center information.
     *
     * @param centerId the gym center ID
     * @param name the new name
     * @param location the new location
     * @return true if the update was successful, false otherwise
     */
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

    /**
     * Approves a gym center.
     *
     * @param centerId the gym center ID to approve
     * @return true if the approval was successful, false otherwise
     */
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

    /**
     * Rejects and removes a gym center.
     *
     * @param centerId the gym center ID to reject
     * @return true if the rejection was successful, false otherwise
     */
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

    /**
     * Gets all available slots for a specific gym center.
     *
     * @param centerId the gym center ID
     * @return list of available slots
     */
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

    /**
     * Requests approval for a gym center.
     *
     * @param centerId the gym center ID
     * @return true if the request was successful, false otherwise
     */
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

    /**
     * Maps a ResultSet row to a GymCenter object.
     *
     * @param rs the result set
     * @return the gym center object
     * @throws SQLException if a database access error occurs
     */
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

    /**
     * Maps a ResultSet row to a Slot object.
     *
     * @param rs the result set
     * @return the slot object
     * @throws SQLException if a database access error occurs
     */
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
