package com.flipfit.business;

import com.flipfit.bean.*;
import java.util.List;

/**
 * The Interface AdminService.
 * Defines business logic operations for admin management in the FlipFit system.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public interface AdminService {
    
    /**
     * Retrieves monthly details and reports.
     *
     * @return the monthly report details
     */
    public String getMonthlyDetails();
    
    /**
     * Views all pending gym center approval requests.
     *
     * @return list of pending gym centers
     */
    public List<GymCenter> viewPendingGymRequest();
    
    /**
     * Approves a gym center request.
     *
     * @param centerId the center id
     * @return true if approval is successful, false otherwise
     */
    public boolean approveRequest(int centerId);
    
    /**
     * Declines a gym center request with a reason.
     *
     * @param centerId the center id
     * @param reason the reason for declining
     * @return true if decline is successful, false otherwise
     */
    public boolean declineRequest(int centerId, String reason);
    
    /**
     * Views all users in the system.
     *
     * @return list of all users
     */
    public List<User> viewAllUsers();
    
    /**
     * Views all gym centers in the system.
     *
     * @return list of all gym centers
     */
    public List<GymCenter> viewAllGymCenters();
    
    /**
     * Views all gym owners in the system.
     *
     * @return list of all gym owners
     */
    public List<GymOwner> viewAllGymOwners();
    
    /**
     * Verifies a gym owner.
     *
     * @param ownerId the owner id
     * @return true if verification is successful, false otherwise
     */
    public boolean verifyGymOwner(int ownerId);
    
    /**
     * Retrieves admin details by admin ID.
     *
     * @param adminId the admin id
     * @return the admin details
     */
    public Admin getAdminById(int adminId);
    
    /**
     * Registers a new admin in the system.
     *
     * @param admin the admin to register
     * @return true if registration is successful, false otherwise
     */
    public boolean registerAdmin(Admin admin);
}
