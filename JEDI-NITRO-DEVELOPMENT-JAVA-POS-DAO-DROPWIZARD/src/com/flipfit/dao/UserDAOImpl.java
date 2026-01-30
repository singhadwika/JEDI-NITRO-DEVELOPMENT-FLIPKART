package com.flipfit.dao;

import com.flipfit.bean.User;
import com.flipfit.bean.GymOwner;
import com.flipfit.bean.GymCustomer;
import com.flipfit.bean.Admin;
import com.flipfit.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean addUser(User user) {
        String insertUserSQL = "INSERT INTO User (name, email, password, role) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertUserSQL, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getRole());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int userId = generatedKeys.getInt(1);
                        user.setId(userId);
                        
                        // Insert into specific role table
                        if (user instanceof GymOwner) {
                            insertGymOwner((GymOwner) user);
                        } else if (user instanceof GymCustomer) {
                            insertGymCustomer((GymCustomer) user);
                        } else if (user instanceof Admin) {
                            insertAdmin((Admin) user);
                        }
                        
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error adding user: " + e.getMessage());
        }
        return false;
    }

    private void insertGymOwner(GymOwner owner) throws SQLException {
        String sql = "INSERT INTO GymOwner (id, is_verified) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, owner.getId());
            pstmt.setBoolean(2, owner.isVerified());
            pstmt.executeUpdate();
        }
    }

    private void insertGymCustomer(GymCustomer customer) throws SQLException {
        String sql = "INSERT INTO GymCustomer (id) VALUES (?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customer.getId());
            pstmt.executeUpdate();
        }
    }

    private void insertAdmin(Admin admin) throws SQLException {
        String sql = "INSERT INTO Admin (id) VALUES (?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, admin.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public User getUserById(int userId) {
        String sql = "SELECT u.*, go.is_verified FROM User u " +
                     "LEFT JOIN GymOwner go ON u.id = go.id " +
                     "WHERE u.id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUser(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting user by ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT u.*, go.is_verified FROM User u " +
                     "LEFT JOIN GymOwner go ON u.id = go.id " +
                     "WHERE u.email = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, email);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUser(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting user by email: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateUser(int userId, String name, String email, String password) {
        String sql = "UPDATE User SET name = ?, email = ?, password = ? WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setInt(4, userId);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating user: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT u.*, go.is_verified FROM User u " +
                     "LEFT JOIN GymOwner go ON u.id = go.id";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all users: " + e.getMessage());
        }
        return users;
    }

    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        String role = rs.getString("role");
        User user;
        
        switch (role) {
            case "GYM_OWNER":
                GymOwner owner = new GymOwner();
                owner.setVerified(rs.getBoolean("is_verified"));
                user = owner;
                break;
            case "GYM_CUSTOMER":
                user = new GymCustomer();
                break;
            case "ADMIN":
                user = new Admin();
                break;
            default:
                user = new User();
        }
        
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setRole(role);
        
        return user;
    }
}
