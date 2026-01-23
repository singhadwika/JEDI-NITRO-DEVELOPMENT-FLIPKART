package com.flipfit.business;

import com.flipfit.bean.*;
import java.util.List;

/**
 * Implementation of GymCustomerServiceInterface.
 */
public class GymCustomerService implements GymCustomerServiceInterface {
    
    @Override
    public boolean registerCustomer(GymCustomer customer) {
        System.out.println("GymCustomerService.registerCustomer called");
        return false;
    }
    
    @Override
    public List<Slot> viewSlots() {
        System.out.println("GymCustomerService.viewSlots called");
        return null;
    }
    
    @Override
    public List<Slot> viewSlotsByCenter(int centerId) {
        System.out.println("GymCustomerService.viewSlotsByCenter called");
        return null;
    }
    
    @Override
    public List<GymCenter> viewCenters() {
        System.out.println("GymCustomerService.viewCenters called");
        return null;
    }
    
    @Override
    public List<GymCenter> viewApprovedCenters() {
        System.out.println("GymCustomerService.viewApprovedCenters called");
        return null;
    }
    
    @Override
    public Booking bookSlot(int customerId, int slotId, int centerId) {
        System.out.println("GymCustomerService.bookSlot called");
        return null;
    }
    
    @Override
    public boolean cancelSlot(int bookingId) {
        System.out.println("GymCustomerService.cancelSlot called");
        return false;
    }
    
    @Override
    public List<Booking> viewWorkoutPlan(int customerId) {
        System.out.println("GymCustomerService.viewWorkoutPlan called");
        return null;
    }
    
    @Override
    public List<Booking> getCustomerBookings(int customerId) {
        System.out.println("GymCustomerService.getCustomerBookings called");
        return null;
    }
    
    @Override
    public GymCustomer getCustomerById(int customerId) {
        System.out.println("GymCustomerService.getCustomerById called");
        return null;
    }
}
