package com.flipfit.dao;

import com.flipfit.bean.GymCenter;
import com.flipfit.bean.Slot;
import com.flipfit.helper.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GymCenterDAOImpl implements GymCenterDAO {

    @Override
    public boolean addGymCenter(GymCenter center) {
        // Table uses 'isApproved' and 'ownerId'
        String query = "INSERT INTO GymCenter (name, location, isApproved, ownerId) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, center.getName());
            stmt.setString(2, center.getLocation());
            stmt.setBoolean(3, false); // default pending
            stmt.setInt(4, center.getOwnerId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        center.setCenterId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public GymCenter getGymCenterById(int centerId) {
        String query = "SELECT * FROM GymCenter WHERE centerId = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, centerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToGymCenter(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<GymCenter> getAllGymCenters() {
        List<GymCenter> centers = new ArrayList<>();
        String query = "SELECT * FROM GymCenter";
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                centers.add(mapResultSetToGymCenter(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return centers;
    }

    @Override
    public List<GymCenter> getApprovedGymCenters() {
        List<GymCenter> centers = new ArrayList<>();
        String query = "SELECT * FROM GymCenter WHERE isApproved = TRUE";
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                centers.add(mapResultSetToGymCenter(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return centers;
    }

    @Override
    public List<GymCenter> getPendingGymCenters() {
        List<GymCenter> centers = new ArrayList<>();
        String query = "SELECT * FROM GymCenter WHERE isApproved = FALSE";
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                centers.add(mapResultSetToGymCenter(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return centers;
    }

    @Override
    public List<GymCenter> getGymCentersByOwner(int ownerId) {
        List<GymCenter> centers = new ArrayList<>();
        String query = "SELECT * FROM GymCenter WHERE ownerId = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ownerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    centers.add(mapResultSetToGymCenter(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return centers;
    }

    @Override
    public boolean updateGymCenter(int centerId, String name, String location) {
        String query = "UPDATE GymCenter SET name = ?, location = ? WHERE centerId = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, location);
            stmt.setInt(3, centerId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean approveGymCenter(int centerId) {
        String query = "UPDATE GymCenter SET isApproved = TRUE WHERE centerId = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, centerId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean rejectGymCenter(int centerId) {
        String query = "DELETE FROM GymCenter WHERE centerId = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, centerId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Slot> getAvailableSlots(int centerId) {
        // This usually requires a call to SlotDAO or a JOIN query
        List<Slot> slots = new ArrayList<>();
        String query = "SELECT * FROM Slot WHERE centerId = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, centerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Slot slot = new Slot();
                    slot.setSlotId(rs.getInt("slotId"));
                    // Add other slot fields as per your Slot bean
                    slots.add(slot);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slots;
    }

    @Override
    public boolean requestForApproval(int centerId) {
        String query = "UPDATE GymCenter SET isApproved = FALSE WHERE centerId = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, centerId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private GymCenter mapResultSetToGymCenter(ResultSet rs) throws SQLException {
        GymCenter center = new GymCenter();
        center.setCenterId(rs.getInt("centerId"));
        center.setName(rs.getString("name"));
        center.setLocation(rs.getString("location"));
        center.setApproved(rs.getBoolean("isApproved"));
        center.setOwnerId(rs.getInt("ownerId"));
        return center;
    }
}