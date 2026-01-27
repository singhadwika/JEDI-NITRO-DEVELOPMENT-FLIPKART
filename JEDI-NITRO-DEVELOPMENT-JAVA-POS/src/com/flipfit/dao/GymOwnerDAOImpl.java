package com.flipfit.dao;

import com.flipfit.bean.GymOwner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GymOwnerDAOImpl implements GymOwnerDAO {

    @Override
    public boolean addGymOwner(GymOwner owner) {
        // First insert into User table
        String insertUserSQL = "INSERT INTO User (name, email, password, role) VALUES (?, ?, ?, 'GYM_OWNER')";
        String insertOwnerSQL = "INSERT INTO GymOwner (id, is_verified) VALUES (?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertUserSQL, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, owner.getName());
            pstmt.setString(2, owner.getEmail());
            pstmt.setString(3, owner.getPassword());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int userId = generatedKeys.getInt(1);
                        owner.setId(userId);
                        
                        // Insert into GymOwner table
                        try (PreparedStatement ownerStmt = conn.prepareStatement(insertOwnerSQL)) {
                            ownerStmt.setInt(1, userId);
                            ownerStmt.setBoolean(2, false); // admin verification required
                            ownerStmt.executeUpdate();
                        }
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error adding gym owner: " + e.getMessage());
        }
        return false;
    }

    @Override
    public GymOwner getGymOwnerById(int ownerId) {
        String sql = "SELECT u.*, go.is_verified FROM User u " +
                     "INNER JOIN GymOwner go ON u.id = go.id " +
                     "WHERE u.id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, ownerId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToGymOwner(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting gym owner by ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<GymOwner> getAllGymOwners() {
        List<GymOwner> owners = new ArrayList<>();
        String sql = "SELECT u.*, go.is_verified FROM User u " +
                     "INNER JOIN GymOwner go ON u.id = go.id";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                owners.add(mapResultSetToGymOwner(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all gym owners: " + e.getMessage());
        }
        return owners;
    }

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

    private GymOwner mapResultSetToGymOwner(ResultSet rs) throws SQLException {
        GymOwner owner = new GymOwner();
        owner.setId(rs.getInt("id"));
        owner.setName(rs.getString("name"));
        owner.setEmail(rs.getString("email"));
        owner.setPassword(rs.getString("password"));
        owner.setRole(rs.getString("role"));
        owner.setVerified(rs.getBoolean("is_verified"));
        return owner;
    }
}
