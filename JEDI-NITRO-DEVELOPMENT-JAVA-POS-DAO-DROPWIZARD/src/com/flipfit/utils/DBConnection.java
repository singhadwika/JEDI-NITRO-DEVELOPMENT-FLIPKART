package com.flipfit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.flipfit.constant.SqlConstant;

/**
 * The Class DBConnection.
 * Database connection utility class for establishing and managing database connections.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class DBConnection {

    private static Connection connection = null;

    /**
     * Gets the database connection.
     * Creates a new connection if the current one is null or closed.
     *
     * @return the database connection
     */
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName(SqlConstant.JDBC_DRIVER);
                connection = DriverManager.getConnection(
                    SqlConstant.DB_URL,
                    SqlConstant.DB_USER,
                    SqlConstant.DB_PASSWORD
                );
            }
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
        }
        return connection;
    }

    /**
     * Closes the database connection.
     * Safely closes the connection if it's open.
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}
