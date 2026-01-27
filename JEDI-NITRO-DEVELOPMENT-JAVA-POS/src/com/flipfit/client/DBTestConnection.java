package com.flipfit.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBTestConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Flipfit_DB";
        String user = "root";
        String pass = "vaefae4R!@#%";

        // Query to select all columns from the User table
       // Updated query using 'id' instead of 'userId'
String query = "SELECT id, name, email, role FROM User"; 

try (Connection conn = DriverManager.getConnection(url, user, pass);
     Statement stmt = conn.createStatement();
     ResultSet rs = stmt.executeQuery(query)) {

    System.out.println("ID | Name | Email | Role");
    while (rs.next()) {
        // Change rs.getInt("userId") to rs.getInt("id")
        int id = rs.getInt("id"); 
        String name = rs.getString("name");
        String email = rs.getString("email");
        String role = rs.getString("role");
        
        System.out.println(id + " | " + name + " | " + email + " | " + role);
    }
}
         catch (Exception e) {
            e.printStackTrace();
        }
    }
}