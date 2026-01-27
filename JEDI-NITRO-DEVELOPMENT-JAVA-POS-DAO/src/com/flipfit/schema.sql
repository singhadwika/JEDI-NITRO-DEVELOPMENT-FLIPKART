-- FlipFit Database Schema
-- Run this script in MySQL to create all required tables

-- Create database
CREATE DATABASE IF NOT EXISTS flipfit;
USE flipfit;

-- ============================================
-- TABLE: User (Base table for all users)
-- ============================================
CREATE TABLE IF NOT EXISTS User (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'GYM_OWNER', 'GYM_CUSTOMER') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- ============================================
-- TABLE: GymOwner (Extends User)
-- ============================================
CREATE TABLE IF NOT EXISTS GymOwner (
    id INT PRIMARY KEY,
    is_verified BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id) REFERENCES User(id) ON DELETE CASCADE
);

-- ============================================
-- TABLE: GymCustomer (Extends User)
-- ============================================
CREATE TABLE IF NOT EXISTS GymCustomer (
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES User(id) ON DELETE CASCADE
);

-- ============================================
-- TABLE: Admin (Extends User)
-- ============================================
CREATE TABLE IF NOT EXISTS Admin (
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES User(id) ON DELETE CASCADE
);

-- ============================================
-- TABLE: GymCenter
-- ============================================
CREATE TABLE IF NOT EXISTS GymCenter (
    center_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(255) NOT NULL,
    is_approved BOOLEAN DEFAULT FALSE,
    owner_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (owner_id) REFERENCES GymOwner(id) ON DELETE CASCADE
);

-- ============================================
-- TABLE: Slot
-- ============================================
CREATE TABLE IF NOT EXISTS Slot (
    slot_id INT PRIMARY KEY AUTO_INCREMENT,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    total_seats INT NOT NULL,
    available_seats INT NOT NULL,
    center_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (center_id) REFERENCES GymCenter(center_id) ON DELETE CASCADE
);

-- ============================================
-- TABLE: Booking
-- ============================================
CREATE TABLE IF NOT EXISTS Booking (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    booking_date DATE NOT NULL,
    status BOOLEAN DEFAULT TRUE,
    user_id INT NOT NULL,
    slot_id INT NOT NULL,
    center_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES GymCustomer(id) ON DELETE CASCADE,
    FOREIGN KEY (slot_id) REFERENCES Slot(slot_id) ON DELETE CASCADE,
    FOREIGN KEY (center_id) REFERENCES GymCenter(center_id) ON DELETE CASCADE
);

-- ============================================
-- TABLE: Notification
-- ============================================
CREATE TABLE IF NOT EXISTS Notification (
    notification_id INT PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(50) NOT NULL,
    message TEXT NOT NULL,
    status ENUM('UNREAD', 'READ') DEFAULT 'UNREAD',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
);

-- ============================================
-- TABLE: Waitlist
-- ============================================
CREATE TABLE IF NOT EXISTS Waitlist (
    waitlist_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    slot_id INT NOT NULL,
    request_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES GymCustomer(id) ON DELETE CASCADE,
    FOREIGN KEY (slot_id) REFERENCES Slot(slot_id) ON DELETE CASCADE,
    UNIQUE KEY unique_waitlist (user_id, slot_id)
);

-- ============================================
-- INDEXES for better query performance
-- ============================================
CREATE INDEX idx_user_email ON User(email);
CREATE INDEX idx_user_role ON User(role);
CREATE INDEX idx_gymcenter_owner ON GymCenter(owner_id);
CREATE INDEX idx_gymcenter_approved ON GymCenter(is_approved);
CREATE INDEX idx_slot_center ON Slot(center_id);
CREATE INDEX idx_booking_user ON Booking(user_id);
CREATE INDEX idx_booking_slot ON Booking(slot_id);
CREATE INDEX idx_booking_center ON Booking(center_id);
CREATE INDEX idx_booking_date ON Booking(booking_date);
CREATE INDEX idx_notification_user ON Notification(user_id);
CREATE INDEX idx_notification_status ON Notification(status);
CREATE INDEX idx_waitlist_slot ON Waitlist(slot_id);
CREATE INDEX idx_waitlist_user ON Waitlist(user_id);

-- ============================================
-- Insert default admin user
-- ============================================
INSERT INTO User (name, email, password, role) VALUES ('Admin', 'admin@flipfit.com', 'admin123', 'ADMIN');
INSERT INTO Admin (id) VALUES (LAST_INSERT_ID());

-- ============================================
-- Sample Data (Optional - uncomment to insert)
-- ============================================
-- -- Sample Gym Owner
-- INSERT INTO User (name, email, password, role) VALUES ('John Owner', 'john@gym.com', 'password123', 'GYM_OWNER');
-- INSERT INTO GymOwner (id, is_verified) VALUES (LAST_INSERT_ID(), TRUE);

-- -- Sample Gym Customer
-- INSERT INTO User (name, email, password, role) VALUES ('Jane Customer', 'jane@email.com', 'password123', 'GYM_CUSTOMER');
-- INSERT INTO GymCustomer (id) VALUES (LAST_INSERT_ID());

SELECT 'FlipFit database schema created successfully!' AS message;
