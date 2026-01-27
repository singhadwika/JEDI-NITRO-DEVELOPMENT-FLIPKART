package com.flipfit.dao;

import com.flipfit.bean.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of GymAdminDAO interface
 * Handles all admin-related database operations
 */
public class GymAdminDAOImpl implements GymAdminDAO {

    private GymCenterDAO gymCenterDAO = new GymCenterDAOImpl();
    private GymOwnerDAO gymOwnerDAO = new GymOwnerDAOImpl();
    private GymUserDAO gymUserDAO = new GymUserDAOImpl();

    @Override
    public boolean addAdmin(Admin admin) {
        return gymUserDAO.addUser(admin);
    }

    @Override
    public Admin getAdminById(int adminId) {
        String sql = "SELECT u.* FROM User u INNER JOIN Admin a ON u.id = a.id WHERE u.id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, adminId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Admin admin = new Admin();
                    admin.setId(rs.getInt("id"));
                    admin.setName(rs.getString("name"));
                    admin.setEmail(rs.getString("email"));
                    admin.setPassword(rs.getString("password"));
                    admin.setRole(rs.getString("role"));
                    return admin;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting admin by ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<GymCenter> getPendingGymCenters() {
        return gymCenterDAO.getPendingGymCenters();
    }

    @Override
    public boolean approveGymCenter(int centerId) {
        return gymCenterDAO.approveGymCenter(centerId);
    }

    @Override
    public boolean declineGymCenter(int centerId, String reason) {
        System.out.println("Gym Center Declined Reason: " + reason);
        return gymCenterDAO.rejectGymCenter(centerId);
    }

    @Override
    public List<User> getAllUsers() {
        return gymUserDAO.getAllUsers();
    }

    @Override
    public List<GymCenter> getAllGymCenters() {
        return gymCenterDAO.getAllGymCenters();
    }

    @Override
    public List<GymOwner> getAllGymOwners() {
        return gymOwnerDAO.getAllGymOwners();
    }

    @Override
    public boolean verifyGymOwner(int ownerId) {
        String sql = "UPDATE GymOwner SET is_verified = TRUE WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, ownerId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error verifying gym owner: " + e.getMessage());
        }
        return false;
    }

    @Override
    public String getMonthlyReport() {
        StringBuilder report = new StringBuilder("Monthly Report: \n");
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Total Users
            try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM User")) {
                if (rs.next()) {
                    report.append("Total Users: ").append(rs.getInt("count")).append("\n");
                }
            }
            
            // Total Gym Centers
            try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM GymCenter")) {
                if (rs.next()) {
                    report.append("Total Gym Centers: ").append(rs.getInt("count")).append("\n");
                }
            }
            
            // Total Gym Owners
            try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM GymOwner")) {
                if (rs.next()) {
                    report.append("Total Gym Owners: ").append(rs.getInt("count")).append("\n");
                }
            }
            
            // Total Bookings
            try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM Booking")) {
                if (rs.next()) {
                    report.append("Total Bookings: ").append(rs.getInt("count"));
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error generating monthly report: " + e.getMessage());
        }
        
        return report.toString();
    }
}
