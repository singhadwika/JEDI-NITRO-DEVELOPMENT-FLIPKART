package com.flipfit.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    // Database credentials for your MySQL environment
    private static final String URL = "jdbc:mysql://localhost:3306/Flipfit_DB"; 
    private static final String USER = "root";      
    private static final String PASS = "vaefae4R!@#%"; 

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found", e);
        }
    }
}