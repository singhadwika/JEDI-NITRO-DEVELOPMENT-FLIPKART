package com.flipfit.dao;

import com.flipfit.bean.GymCustomer;
import com.flipfit.helper.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GymCustomerDAOImpl implements GymCustomerDAO {

    @Override
    public boolean addCustomer(GymCustomer customer) {
        // Customers are stored in the User table with role 'GymCustomer'
        String query = "INSERT INTO User (name, email, password, role) VALUES (?, ?, ?, 'GymCustomer')";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getPassword());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        customer.setId(generatedKeys.getInt(1));
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
    public GymCustomer getCustomerById(int customerId) {
        String query = "SELECT * FROM User WHERE id = ? AND role = 'GymCustomer'";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    GymCustomer customer = new GymCustomer();
                    customer.setId(rs.getInt("id"));
                    customer.setName(rs.getString("name"));
                    customer.setEmail(rs.getString("email"));
                    return customer;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<GymCustomer> getAllCustomers() {
        List<GymCustomer> customers = new ArrayList<>();
        String query = "SELECT * FROM User WHERE role = 'GymCustomer'";
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                GymCustomer customer = new GymCustomer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}