package com.flipfit.business;

import com.flipfit.bean.*;
import java.util.List;

public interface GymCustomerService {
    
    public boolean registerCustomer(GymCustomer customer);
    
    public List<Slot> viewSlots();
    
    public List<Slot> viewSlotsByCenter(int centerId);
    
    public List<GymCenter> viewCenters();
    
    public List<GymCenter> viewApprovedCenters();
    
    public Booking bookSlot(int customerId, int slotId, int centerId);
    
    public boolean cancelSlot(int bookingId);
    
    public List<Booking> viewWorkoutPlan(int customerId);
    
    public List<Booking> getCustomerBookings(int customerId);
    
    public GymCustomer getCustomerById(int customerId);
}
