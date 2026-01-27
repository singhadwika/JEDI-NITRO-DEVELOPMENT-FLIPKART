package com.flipfit.dao;

import com.flipfit.bean.*;
import java.util.List;

/**
 * DAO Interface for GymAdmin operations
 * Provides methods for admin-related database operations
 */
public interface GymAdminDAO {

    /**
     * Add a new admin to the system
     * @param admin Admin object to add
     * @return true if admin added successfully, false otherwise
     */
    boolean addAdmin(Admin admin);

    /**
     * Get admin by their unique ID
     * @param adminId ID of the admin
     * @return Admin object if found, null otherwise
     */
    Admin getAdminById(int adminId);

    /**
     * Get all gym centers pending approval
     * @return List of pending GymCenter objects
     */
    List<GymCenter> getPendingGymCenters();

    /**
     * Approve a gym center
     * @param centerId ID of the gym center to approve
     * @return true if approved successfully, false otherwise
     */
    boolean approveGymCenter(int centerId);

    /**
     * Decline a gym center with a reason
     * @param centerId ID of the gym center to decline
     * @param reason Reason for declining
     * @return true if declined successfully, false otherwise
     */
    boolean declineGymCenter(int centerId, String reason);

    /**
     * Get all users in the system
     * @return List of all User objects
     */
    List<User> getAllUsers();

    /**
     * Get all gym centers in the system
     * @return List of all GymCenter objects
     */
    List<GymCenter> getAllGymCenters();

    /**
     * Get all gym owners in the system
     * @return List of all GymOwner objects
     */
    List<GymOwner> getAllGymOwners();

    /**
     * Verify a gym owner
     * @param ownerId ID of the gym owner to verify
     * @return true if verified successfully, false otherwise
     */
    boolean verifyGymOwner(int ownerId);

    /**
     * Generate monthly report
     * @return Monthly report as a formatted string
     */
    String getMonthlyReport();
}
