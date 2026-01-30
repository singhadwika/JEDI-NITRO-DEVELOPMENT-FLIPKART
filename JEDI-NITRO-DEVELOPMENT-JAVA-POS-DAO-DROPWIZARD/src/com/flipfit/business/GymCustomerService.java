package com.flipfit.business;

import com.flipfit.bean.*;
import java.util.List;

/**
 * The Interface GymCustomerService.
 * Defines business logic operations for gym customer management in the FlipFit system.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public interface GymCustomerService {
    
    /**
     * Registers a new gym customer in the system.
     *
     * @param customer the gym customer to register
     * @return true if registration is successful, false otherwise
     */
    public boolean registerCustomer(GymCustomer customer);
    
    /**
     * Views all available slots in the system.
     *
     * @return list of all slots
     */
    public List<Slot> viewSlots();
    
    /**
     * Views all slots available at a specific gym center.
     *
     * @param centerId the center id
     * @return list of slots at the center
     */
    public List<Slot> viewSlotsByCenter(int centerId);
    
    /**
     * Views all gym centers in the system.
     *
     * @return list of all gym centers
     */
    public List<GymCenter> viewCenters();
    
    /**
     * Views all approved gym centers.
     *
     * @return list of approved gym centers
     */
    public List<GymCenter> viewApprovedCenters();
    
    /**
     * Books a slot for a customer.
     *
     * @param customerId the customer id
     * @param slotId the slot id
     * @param centerId the center id
     * @return the created booking
     */
    public Booking bookSlot(int customerId, int slotId, int centerId);
    
    /**
     * Cancels a booked slot for a customer.
     *
     * @param bookingId the booking id
     * @return true if cancellation is successful, false otherwise
     */
    public boolean cancelSlot(int bookingId);
    
    /**
     * Views the workout plan of a customer.
     *
     * @param customerId the customer id
     * @return list of customer bookings representing workout plan
     */
    public List<Booking> viewWorkoutPlan(int customerId);
    
    /**
     * Retrieves all bookings of a customer.
     *
     * @param customerId the customer id
     * @return list of customer bookings
     */
    public List<Booking> getCustomerBookings(int customerId);
    
    /**
     * Retrieves customer details by ID.
     *
     * @param customerId the customer id
     * @return the customer details
     */
    public GymCustomer getCustomerById(int customerId);
}
