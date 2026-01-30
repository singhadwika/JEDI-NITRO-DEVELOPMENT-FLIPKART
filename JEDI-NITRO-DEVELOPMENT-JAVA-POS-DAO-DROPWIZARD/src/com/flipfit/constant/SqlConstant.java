package com.flipfit.constant;

/**
 * The Class SqlConstant.
 * Contains all SQL constants and database configuration values.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class SqlConstant {

    // Database configuration constants
    public static final String DB_URL = "jdbc:mysql://localhost:3306/flipfit";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "30062004";
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    // User-related SQL queries
    public static final String USER_INSERT = "INSERT INTO User (name, email, password, role) VALUES (?, ?, ?, ?)";
    public static final String USER_SELECT_BY_EMAIL = "SELECT * FROM User WHERE email = ?";
    public static final String USER_SELECT_BY_ID = "SELECT * FROM User WHERE id = ?";
    public static final String USER_UPDATE = "UPDATE User SET name = ?, email = ?, password = ? WHERE id = ?";
    public static final String USER_DELETE = "DELETE FROM User WHERE id = ?";

    // GymOwner-related SQL queries
    public static final String GYM_OWNER_INSERT = "INSERT INTO GymOwner (id, is_verified) VALUES (?, ?)";
    public static final String GYM_OWNER_SELECT_BY_ID = "SELECT * FROM GymOwner WHERE id = ?";
    public static final String GYM_OWNER_UPDATE = "UPDATE GymOwner SET is_verified = ? WHERE id = ?";

    // GymCustomer-related SQL queries
    public static final String GYM_CUSTOMER_INSERT = "INSERT INTO GymCustomer (id) VALUES (?)";
    public static final String GYM_CUSTOMER_SELECT_BY_ID = "SELECT * FROM GymCustomer WHERE id = ?";

    // Admin-related SQL queries
    public static final String ADMIN_INSERT = "INSERT INTO Admin (id) VALUES (?)";
    public static final String ADMIN_SELECT_BY_ID = "SELECT * FROM Admin WHERE id = ?";

    // GymCenter-related SQL queries
    public static final String GYM_CENTER_INSERT = "INSERT INTO GymCenter (name, location, owner_id) VALUES (?, ?, ?)";
    public static final String GYM_CENTER_SELECT_BY_ID = "SELECT * FROM GymCenter WHERE center_id = ?";
    public static final String GYM_CENTER_SELECT_ALL = "SELECT * FROM GymCenter";
    public static final String GYM_CENTER_SELECT_BY_OWNER = "SELECT * FROM GymCenter WHERE owner_id = ?";
    public static final String GYM_CENTER_SELECT_APPROVED = "SELECT * FROM GymCenter WHERE is_approved = TRUE";
    public static final String GYM_CENTER_UPDATE = "UPDATE GymCenter SET name = ?, location = ? WHERE center_id = ?";
    public static final String GYM_CENTER_APPROVE = "UPDATE GymCenter SET is_approved = TRUE WHERE center_id = ?";

    // Slot-related SQL queries
    public static final String SLOT_INSERT = "INSERT INTO Slot (start_time, end_time, total_seats, available_seats, center_id) VALUES (?, ?, ?, ?, ?)";
    public static final String SLOT_SELECT_BY_ID = "SELECT * FROM Slot WHERE slot_id = ?";
    public static final String SLOT_SELECT_BY_CENTER = "SELECT * FROM Slot WHERE center_id = ?";
    public static final String SLOT_SELECT_ALL = "SELECT * FROM Slot";
    public static final String SLOT_UPDATE = "UPDATE Slot SET available_seats = ? WHERE slot_id = ?";
    public static final String SLOT_DELETE = "DELETE FROM Slot WHERE slot_id = ?";

    // Booking-related SQL queries
    public static final String BOOKING_INSERT = "INSERT INTO Booking (booking_date, status, user_id, slot_id, center_id) VALUES (?, ?, ?, ?, ?)";
    public static final String BOOKING_SELECT_BY_ID = "SELECT * FROM Booking WHERE booking_id = ?";
    public static final String BOOKING_SELECT_BY_USER = "SELECT * FROM Booking WHERE user_id = ?";
    public static final String BOOKING_SELECT_BY_SLOT = "SELECT * FROM Booking WHERE slot_id = ?";
    public static final String BOOKING_SELECT_ALL = "SELECT * FROM Booking";
    public static final String BOOKING_UPDATE = "UPDATE Booking SET status = ? WHERE booking_id = ?";
    public static final String BOOKING_DELETE = "DELETE FROM Booking WHERE booking_id = ?";

    // Notification-related SQL queries
    public static final String NOTIFICATION_INSERT = "INSERT INTO Notification (type, message, user_id) VALUES (?, ?, ?)";
    public static final String NOTIFICATION_SELECT_BY_USER = "SELECT * FROM Notification WHERE user_id = ? AND status = 'UNREAD'";
    public static final String NOTIFICATION_UPDATE_STATUS = "UPDATE Notification SET status = 'READ' WHERE notification_id = ?";
    public static final String NOTIFICATION_DELETE = "DELETE FROM Notification WHERE notification_id = ?";

    // Waitlist-related SQL queries
    public static final String WAITLIST_INSERT = "INSERT INTO Waitlist (user_id, slot_id) VALUES (?, ?)";
    public static final String WAITLIST_SELECT_BY_SLOT = "SELECT * FROM Waitlist WHERE slot_id = ?";
    public static final String WAITLIST_DELETE = "DELETE FROM Waitlist WHERE waitlist_id = ?";

    // Private constructor to prevent instantiation
    private SqlConstant() {
        throw new AssertionError("Cannot instantiate SqlConstant class");
    }
}
