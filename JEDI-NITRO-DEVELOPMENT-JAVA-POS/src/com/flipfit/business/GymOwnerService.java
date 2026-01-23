package com.flipfit.business;

import com.flipfit.bean.*;
import java.time.LocalTime;
import java.util.List;

/**
 * Implementation of GymOwnerServiceInterface.
 */
public class GymOwnerService implements GymOwnerServiceInterface {
    
    @Override
    public boolean registerGymOwner(GymOwner gymOwner) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public boolean addGymCenter(int ownerId, GymCenter center) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public boolean updateCenterDetails(int centerId, String name, String location) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public boolean addSlot(int centerId, LocalTime startTime, LocalTime endTime, int totalSeats) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public boolean removeSlot(int slotId) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public List<Slot> manageSlots(int centerId) {
        // TODO: Implement
        return null;
    }
    
    @Override
    public List<Booking> viewBookings(int ownerId) {
        // TODO: Implement
        return null;
    }
    
    @Override
    public List<Booking> viewBookingsByCenter(int centerId) {
        // TODO: Implement
        return null;
    }
    
    @Override
    public List<GymCenter> getGymCentersByOwner(int ownerId) {
        // TODO: Implement
        return null;
    }
    
    @Override
    public boolean requestApproval(int centerId) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public GymOwner getGymOwnerById(int ownerId) {
        // TODO: Implement
        return null;
    }
}
