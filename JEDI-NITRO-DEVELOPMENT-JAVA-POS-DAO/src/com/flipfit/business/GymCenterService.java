package com.flipfit.business;

import com.flipfit.bean.GymCenter;
import com.flipfit.bean.Slot;
import java.util.List;

/**
 * The Interface GymCenterService.
 * Defines business logic operations for gym center management in the FlipFit system.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public interface GymCenterService {
    
    /**
     * Adds a new gym center to the system.
     *
     * @param center the gym center to add
     * @return true if addition is successful, false otherwise
     */
    public boolean addGymCenter(GymCenter center);
    
    /**
     * Retrieves a gym center by its ID.
     *
     * @param centerId the center id
     * @return the gym center details
     */
    public GymCenter getGymCenterById(int centerId);

    /**
     * Retrieves all gym centers in the system.
     *
     * @return list of all gym centers
     */
    public List<GymCenter> getAllGymCenters();
    
    /**
     * Retrieves all approved gym centers.
     *
     * @return list of approved gym centers
     */
    public List<GymCenter> getApprovedGymCenters();
    
    /**
     * Retrieves all pending gym centers waiting for approval.
     *
     * @return list of pending gym centers
     */
    public List<GymCenter> getPendingGymCenters();
    
    /**
     * Retrieves all gym centers owned by a specific owner.
     *
     * @param ownerId the owner id
     * @return list of gym centers owned by the owner
     */
    public List<GymCenter> getGymCentersByOwner(int ownerId);
    
    /**
     * Updates the details of an existing gym center.
     *
     * @param centerId the center id
     * @param name the new center name
     * @param location the new center location
     * @return true if update is successful, false otherwise
     */
    public boolean updateGymCenter(int centerId, String name, String location);
    
    /**
     * Approves a gym center for operation.
     *
     * @param centerId the center id
     * @return true if approval is successful, false otherwise
     */
    public boolean approveGymCenter(int centerId);
    
    /**
     * Rejects a gym center application.
     *
     * @param centerId the center id
     * @return true if rejection is successful, false otherwise
     */
    public boolean rejectGymCenter(int centerId);
    
    /**
     * Retrieves all available slots at a gym center.
     *
     * @param centerId the center id
     * @return list of available slots
     */
    public List<Slot> viewAvailableSlots(int centerId);
    
    /**
     * Retrieves detailed information about a gym center.
     *
     * @param centerId the center id
     * @return the detailed gym center information
     */
    public GymCenter getCenterDetails(int centerId);
    
    /**
     * Requests approval for a gym center.
     *
     * @param centerId the center id
     * @return true if request is successful, false otherwise
     */
    public boolean requestForApproval(int centerId);
}
