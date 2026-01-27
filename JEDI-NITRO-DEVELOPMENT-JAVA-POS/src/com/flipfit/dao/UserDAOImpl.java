package com.flipfit.dao;

import com.flipfit.bean.*;
import com.flipfit.helper.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
//
//    @Override
//    public boolean addUser(User user) {
//        // Determine the role string based on the object type
//        String role = "CUSTOMER";
//        if (user instanceof Admin) role = "ADMIN";
//        else if (user instanceof GymOwner) role = "OWNER";
//
//        String query = "INSERT INTO User (name, email, password, role) VALUES (?, ?, ?, ?)";
//        
//        try (Connection conn = DatabaseConnector.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            
//            stmt.setString(1, user.getName());
//            stmt.setString(2, user.getEmail());
//            stmt.setString(3, user.getPassword());
//            stmt.setString(4, role);
//            
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
	@Override
	public boolean addUser(User user) {
	    String role = "CUSTOMER";
	    if (user instanceof Admin) role = "ADMIN";
	    else if (user instanceof GymOwner) role = "OWNER";

	    // Queries for both tables
	    String userQuery = "INSERT INTO User (name, email, password, role) VALUES (?, ?, ?, ?)";
	    String ownerQuery = "INSERT INTO GymOwner (id, isVerified) VALUES (?, FALSE)";

	    Connection conn = null;
	    try {
	        conn = DatabaseConnector.getConnection();
	        conn.setAutoCommit(false); // Enable Transaction

	        // 1. Insert into User Table
	        PreparedStatement stmt = conn.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS);
	        stmt.setString(1, user.getName());
	        stmt.setString(2, user.getEmail());
	        stmt.setString(3, user.getPassword());
	        stmt.setString(4, role);
	        stmt.executeUpdate();

	        // 2. If Owner, Insert into GymOwner Table
	        if ("OWNER".equals(role)) {
	            try (ResultSet rs = stmt.getGeneratedKeys()) {
	                if (rs.next()) {
	                    int generatedId = rs.getInt(1);
	                    PreparedStatement ownerStmt = conn.prepareStatement(ownerQuery);
	                    ownerStmt.setInt(1, generatedId);
	                    ownerStmt.executeUpdate();
	                }
	            }
	        }

	        conn.commit(); // Save both
	        return true;
	    } catch (SQLException e) {
	        if (conn != null) try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
	        e.printStackTrace();
	    }
	    return false;
	}

    @Override
    public User getUserByEmail(String email) {
        String query = "SELECT * FROM User WHERE email = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String role = rs.getString("role");
                    User user;

                    // Instantiate the correct bean based on role from DB
                    if ("ADMIN".equalsIgnoreCase(role)) user = new Admin();
                    else if ("OWNER".equalsIgnoreCase(role)) user = new GymOwner();
                    else user = new GymCustomer();

                    user.setId(rs.getInt("id")); // Matching your 'id' column
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(role);
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
    public User getUserById(int userId) {
        String query = "SELECT * FROM User WHERE id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateUser(int userId, String name, String email, String password) {
        String query = "UPDATE User SET name = ?, email = ?, password = ? WHERE id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setInt(4, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}