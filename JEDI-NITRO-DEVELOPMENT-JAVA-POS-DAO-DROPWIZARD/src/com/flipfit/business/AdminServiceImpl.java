package com.flipfit.business;

import com.flipfit.bean.*;
import com.flipfit.dao.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Class AdminServiceImpl.
 * Implements business logic operations for admin management in the FlipFit system.
 * Uses Stream API for functional programming style data filtering.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class AdminServiceImpl implements AdminService {

    private AdminDAO adminDAO = new AdminDAOImpl();

    /**
     * Retrieves monthly details and reports.
     *
     * @return the monthly report details
     */
    @Override
    public String getMonthlyDetails() {
        return adminDAO.getMonthlyReport();
    }

    /**
     * Views all pending gym center approval requests.
     *
     * @return list of pending gym centers
     */
    @Override
    public List<GymCenter> viewPendingGymRequest() {
        return adminDAO.getPendingGymCenters();
    }

    /**
     * Approves a gym center request.
     *
     * @param centerId the center id
     * @return true if approval is successful, false otherwise
     */
    @Override
    public boolean approveRequest(int centerId) {
        return adminDAO.approveGymCenter(centerId);
    }

    /**
     * Declines a gym center request with a reason.
     *
     * @param centerId the center id
     * @param reason the reason for declining
     * @return true if decline is successful, false otherwise
     */
    @Override
    public boolean declineRequest(int centerId, String reason) {
        return adminDAO.declineGymCenter(centerId, reason);
    }

    /**
     * Views all users in the system.
     *
     * @return list of all users
     */
    @Override
    public List<User> viewAllUsers() {
        return adminDAO.getAllUsers();
    }

    /**
     * Views all gym centers in the system.
     *
     * @return list of all gym centers
     */
    @Override
    public List<GymCenter> viewAllGymCenters() {
        return adminDAO.getAllGymCenters();
    }

    /**
     * Views all gym owners in the system.
     *
     * @return list of all gym owners
     */
    @Override
    public List<GymOwner> viewAllGymOwners() {
        return adminDAO.getAllGymOwners();
    }

    /**
     * Verifies a gym owner.
     *
     * @param ownerId the owner id
     * @return true if verification is successful, false otherwise
     */
    @Override
    public boolean verifyGymOwner(int ownerId) {
        return adminDAO.verifyGymOwner(ownerId);
    }

    /**
     * Retrieves admin details by admin ID.
     *
     * @param adminId the admin id
     * @return the admin details
     */
    @Override
    public Admin getAdminById(int adminId) {
        return adminDAO.getAdminById(adminId);
    }

    /**
     * Registers a new admin in the system.
     *
     * @param admin the admin to register
     * @return true if registration is successful, false otherwise
     */
    @Override
    public boolean registerAdmin(Admin admin) {
        return adminDAO.addAdmin(admin);
    }

    /**
     * Gets all approved gym centers using Stream API.
     * Demonstrates functional programming with Java 17+ Stream API.
     *
     * @return list of approved gym centers
     */
    public List<GymCenter> getApprovedGymCenters() {
        return adminDAO.getAllGymCenters().stream()
                .filter(GymCenter::isApproved)
                .collect(Collectors.toList());
    }

    /**
     * Gets all not-approved (pending) gym centers using Stream API.
     * Demonstrates functional programming with Java 17+ Stream API.
     *
     * @return list of pending gym centers
     */
    public List<GymCenter> getNotApprovedGymCenters() {
        return adminDAO.getAllGymCenters().stream()
                .filter(center -> !center.isApproved())
                .collect(Collectors.toList());
    }

    /**
     * Gets approved gym owners using Stream API.
     * Filters gym owners based on verification status.
     *
     * @return list of verified gym owners
     */
    public List<GymOwner> getApprovedGymOwners() {
        return adminDAO.getAllGymOwners().stream()
                .filter(GymOwner::isVerified)
                .collect(Collectors.toList());
    }

    /**
     * Gets not-approved (unverified) gym owners using Stream API.
     * Filters gym owners who are not yet verified.
     *
     * @return list of unverified gym owners
     */
    public List<GymOwner> getNotApprovedGymOwners() {
        return adminDAO.getAllGymOwners().stream()
                .filter(owner -> !owner.isVerified())
                .collect(Collectors.toList());
    }
}
