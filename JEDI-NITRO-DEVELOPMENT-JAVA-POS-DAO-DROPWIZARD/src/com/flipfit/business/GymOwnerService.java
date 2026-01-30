package com.flipfit.business;

import com.flipfit.bean.*;
import java.time.LocalTime;
import java.util.List;

/**
 * The Interface GymOwnerService.
 * Defines business logic operations for gym owner management in the FlipFit system.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public interface GymOwnerService {
    
    /**
     * Registers a new gym owner in the system.
     *
     * @param gymOwner the gym owner to register
     * @return true if registration is successful, false otherwise
     */
    public boolean registerGymOwner(GymOwner gymOwner);
    
    /**
     * Adds a gym center for a gym owner.
     *
     * @param ownerId the owner id
     * @param center the gym center to add
     * @return true if addition is successful, false otherwise
     */
    public boolean addGymCenter(int ownerId, GymCenter center);
    
    /**
     * Updates the details of a gym center.
     *
     * @param centerId the center id
     * @param name the new center name
     * @param location the new center location
     * @return true if update is successful, false otherwise
     */
    public boolean updateCenterDetails(int centerId, String name, String location);
    
    /**
     * Adds a new slot at a gym center.
     *
     * @param centerId the center id
     * @param startTime the slot start time
     * @param endTime the slot end time
     * @param totalSeats the total seats in the slot
     * @return the created slot
     */
    public Slot addSlot(int centerId, LocalTime startTime, LocalTime endTime, int totalSeats);
    
    /**
     * Removes a slot from a gym center.
     *
     * @param slotId the slot id
     * @return true if removal is successful, false otherwise
     */
    public boolean removeSlot(int slotId);
    
    /**
     * Manages and retrieves all slots at a gym center.
     *
     * @param centerId the center id
     * @return list of slots at the center
     */
    public List<Slot> manageSlots(int centerId);
    
    /**
     * Views all bookings for a gym owner's centers.
     *
     * @param ownerId the owner id
     * @return list of bookings
     */
    public List<Booking> viewBookings(int ownerId);
    
    /**
     * Views all bookings for a specific gym center.
     *
     * @param centerId the center id
     * @return list of bookings for the center
     */
    public List<Booking> viewBookingsByCenter(int centerId);
    
    /**
     * Retrieves all gym centers owned by a specific owner.
     *
     * @param ownerId the owner id
     * @return list of gym centers owned by the owner
     */
    public List<GymCenter> getGymCentersByOwner(int ownerId);
    
    /**
     * Requests approval for a gym center.
     *
     * @param centerId the center id
     * @return true if request is successful, false otherwise
     */
    public boolean requestApproval(int centerId);
    
    /**
     * Retrieves gym owner details by ID.
     *
     * @param ownerId the owner id
     * @return the gym owner details
     */
    public GymOwner getGymOwnerById(int ownerId);
}
