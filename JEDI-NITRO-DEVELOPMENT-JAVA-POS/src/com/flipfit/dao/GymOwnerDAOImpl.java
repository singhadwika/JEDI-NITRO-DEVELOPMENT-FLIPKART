package com.flipfit.dao;

import com.flipfit.bean.GymOwner;
import com.flipfit.helper.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GymOwnerDAOImpl implements GymOwnerDAO {

    @Override
    public boolean addGymOwner(GymOwner owner) {
        Connection conn = null;
        try {
            conn = DatabaseConnector.getConnection();
            conn.setAutoCommit(false); // Start transaction

            // 1. Insert into User table first
            String userQuery = "INSERT INTO User (name, email, password, role) VALUES (?, ?, ?, 'OWNER')";
            PreparedStatement userStmt = conn.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS);
            userStmt.setString(1, owner.getName());
            userStmt.setString(2, owner.getEmail());
            userStmt.setString(3, owner.getPassword());
            userStmt.executeUpdate();

            // Get the generated ID
            ResultSet rs = userStmt.getGeneratedKeys();
            int generatedId = 0;
            if (rs.next()) {
                generatedId = rs.getInt(1);
                owner.setId(generatedId);
            }

            // 2. Insert into GymOwner table
            String ownerQuery = "INSERT INTO GymOwner (id, isVerified) VALUES (?, ?)";
            PreparedStatement ownerStmt = conn.prepareStatement(ownerQuery);
            ownerStmt.setInt(1, generatedId);
            ownerStmt.setBoolean(2, false); // Default to unverified
            ownerStmt.executeUpdate();

            conn.commit(); // Save changes
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
        return false;
    }

    @Override
    public GymOwner getGymOwnerById(int ownerId) {
        // Join User and GymOwner tables to get full details
        String query = "SELECT u.id, u.name, u.email, g.isVerified FROM User u " +
                       "JOIN GymOwner g ON u.id = g.id WHERE u.id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ownerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    GymOwner owner = new GymOwner();
                    owner.setId(rs.getInt("id"));
                    owner.setName(rs.getString("name"));
                    owner.setEmail(rs.getString("email"));
                    owner.setVerified(rs.getBoolean("isVerified"));
                    return owner;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<GymOwner> getAllGymOwners() {
        List<GymOwner> owners = new ArrayList<>();
        String query = "SELECT u.id, u.name, u.email, g.isVerified FROM User u " +
                       "JOIN GymOwner g ON u.id = g.id";
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                GymOwner owner = new GymOwner();
                owner.setId(rs.getInt("id"));
                owner.setName(rs.getString("name"));
                owner.setEmail(rs.getString("email"));
                owner.setVerified(rs.getBoolean("isVerified"));
                owners.add(owner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return owners;
    }
}