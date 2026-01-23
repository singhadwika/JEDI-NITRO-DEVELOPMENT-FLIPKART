package com.flipfit.business;

import com.flipfit.bean.*;
import java.util.List;

/**
 * Implementation of GymCustomerServiceInterface.
 */
public class GymCustomerService implements GymCustomerServiceInterface {
    
    @Override
    public boolean registerCustomer(GymCustomer customer) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public List<Slot> viewSlots() {
        // TODO: Implement
        return null;
    }
    
    @Override
    public List<Slot> viewSlotsByCenter(int centerId) {
        // TODO: Implement
        return null;
    }
    
    @Override
    public List<GymCenter> viewCenters() {
        // TODO: Implement
        return null;
    }
    
    @Override
    public List<GymCenter> viewApprovedCenters() {
        // TODO: Implement
        return null;
    }
    
    @Override
    public Booking bookSlot(int customerId, int slotId, int centerId) {
        // TODO: Implement
        return null;
    }
    
    @Override
    public boolean cancelSlot(int bookingId) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public List<Booking> viewWorkoutPlan(int customerId) {
        // TODO: Implement
        return null;
    }
    
    @Override
    public List<Booking> getCustomerBookings(int customerId) {
        // TODO: Implement
        return null;
    }
    
    @Override
    public GymCustomer getCustomerById(int customerId) {
        // TODO: Implement
        return null;
    }
}
