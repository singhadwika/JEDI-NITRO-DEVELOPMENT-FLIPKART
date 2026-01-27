package com.flipfit.dao;

import com.flipfit.bean.GymCustomer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GymCustomerDAOImpl implements GymCustomerDAO {

    @Override
    public boolean addCustomer(GymCustomer customer) {
        // First insert into User table
        String insertUserSQL = "INSERT INTO User (name, email, password, role) VALUES (?, ?, ?, 'GYM_CUSTOMER')";
        String insertCustomerSQL = "INSERT INTO GymCustomer (id) VALUES (?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertUserSQL, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getEmail());
            pstmt.setString(3, customer.getPassword());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int userId = generatedKeys.getInt(1);
                        customer.setId(userId);
                        
                        // Insert into GymCustomer table
                        try (PreparedStatement customerStmt = conn.prepareStatement(insertCustomerSQL)) {
                            customerStmt.setInt(1, userId);
                            customerStmt.executeUpdate();
                        }
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error adding gym customer: " + e.getMessage());
        }
        return false;
    }

    @Override
    public GymCustomer getCustomerById(int customerId) {
        String sql = "SELECT u.* FROM User u " +
                     "INNER JOIN GymCustomer gc ON u.id = gc.id " +
                     "WHERE u.id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, customerId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToGymCustomer(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting gym customer by ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<GymCustomer> getAllCustomers() {
        List<GymCustomer> customers = new ArrayList<>();
        String sql = "SELECT u.* FROM User u " +
                     "INNER JOIN GymCustomer gc ON u.id = gc.id";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                customers.add(mapResultSetToGymCustomer(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all gym customers: " + e.getMessage());
        }
        return customers;
    }

    private GymCustomer mapResultSetToGymCustomer(ResultSet rs) throws SQLException {
        GymCustomer customer = new GymCustomer();
        customer.setId(rs.getInt("id"));
        customer.setName(rs.getString("name"));
        customer.setEmail(rs.getString("email"));
        customer.setPassword(rs.getString("password"));
        customer.setRole(rs.getString("role"));
        return customer;
    }
}
