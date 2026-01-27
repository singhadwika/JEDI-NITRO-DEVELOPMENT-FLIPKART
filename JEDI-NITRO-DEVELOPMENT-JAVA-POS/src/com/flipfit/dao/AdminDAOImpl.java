package com.flipfit.dao;

import com.flipfit.bean.*;
import com.flipfit.helper.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {

    @Override
    public boolean addAdmin(Admin admin) {
        String query = "INSERT INTO User (name, email, password, role) VALUES (?, ?, ?, 'ADMIN')";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, admin.getName());
            stmt.setString(2, admin.getEmail());
            stmt.setString(3, admin.getPassword());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Admin getAdminById(int adminId) {
        String query = "SELECT * FROM User WHERE id = ? AND role = 'ADMIN'";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, adminId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Admin admin = new Admin();
                    admin.setId(rs.getInt("id"));
                    admin.setName(rs.getString("name"));
                    admin.setEmail(rs.getString("email"));
                    return admin;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<GymCenter> getPendingGymCenters() {
        List<GymCenter> pendingCenters = new ArrayList<>();
        // Schema uses isApproved BOOLEAN
        String query = "SELECT * FROM GymCenter WHERE isApproved = FALSE";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                pendingCenters.add(mapResultSetToGymCenter(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pendingCenters;
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
    public boolean declineGymCenter(int centerId, String reason) {
        // Since schema is BOOLEAN, decline sets to FALSE (0)
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
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM User";
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
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
    public List<GymOwner> getAllGymOwners() {
        List<GymOwner> owners = new ArrayList<>();
        // Join with User to get Names as GymOwner table only has ID and status
        String query = "SELECT u.id, u.name, g.isVerified FROM User u JOIN GymOwner g ON u.id = g.id";
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                GymOwner owner = new GymOwner();
                owner.setId(rs.getInt("id"));
                owner.setName(rs.getString("name"));
                owner.setVerified(rs.getBoolean("isVerified"));
                owners.add(owner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return owners;
    }

    @Override
    public boolean verifyGymOwner(int ownerId) {
        String query = "UPDATE GymOwner SET isVerified = TRUE WHERE id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ownerId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getMonthlyReport() {
        try (Connection conn = DatabaseConnector.getConnection()) {
            int userCount = 0, centerCount = 0, ownerCount = 0;
            
            ResultSet rsUsers = conn.createStatement().executeQuery("SELECT COUNT(*) FROM User");
            if (rsUsers.next()) userCount = rsUsers.getInt(1);
            
            ResultSet rsCenters = conn.createStatement().executeQuery("SELECT COUNT(*) FROM GymCenter");
            if (rsCenters.next()) centerCount = rsCenters.getInt(1);
            
            ResultSet rsOwners = conn.createStatement().executeQuery("SELECT COUNT(*) FROM GymOwner");
            if (rsOwners.next()) ownerCount = rsOwners.getInt(1);

            return String.format("Monthly Report:\nTotal Users: %d\nTotal Centers: %d\nTotal Owners: %d", 
                                 userCount, centerCount, ownerCount);
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error generating report.";
        }
    }

    // Helper to map GymCenter ensuring column names match schema
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