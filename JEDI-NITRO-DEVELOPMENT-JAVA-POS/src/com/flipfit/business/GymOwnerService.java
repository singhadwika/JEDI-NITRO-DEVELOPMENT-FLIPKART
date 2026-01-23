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
        System.out.println("GymOwnerService.registerGymOwner called");
        return false;
    }
    
    @Override
    public boolean addGymCenter(int ownerId, GymCenter center) {
        System.out.println("GymOwnerService.addGymCenter called");
        return false;
    }
    
    @Override
    public boolean updateCenterDetails(int centerId, String name, String location) {
        System.out.println("GymOwnerService.updateCenterDetails called");
        return false;
    }
    
    @Override
    public boolean addSlot(int centerId, LocalTime startTime, LocalTime endTime, int totalSeats) {
        System.out.println("GymOwnerService.addSlot called");
        return false;
    }
    
    @Override
    public boolean removeSlot(int slotId) {
        System.out.println("GymOwnerService.removeSlot called");
        return false;
    }
    
    @Override
    public List<Slot> manageSlots(int centerId) {
        System.out.println("GymOwnerService.manageSlots called");
        return null;
    }
    
    @Override
    public List<Booking> viewBookings(int ownerId) {
        System.out.println("GymOwnerService.viewBookings called");
        return null;
    }
    
    @Override
    public List<Booking> viewBookingsByCenter(int centerId) {
        System.out.println("GymOwnerService.viewBookingsByCenter called");
        return null;
    }
    
    @Override
    public List<GymCenter> getGymCentersByOwner(int ownerId) {
        System.out.println("GymOwnerService.getGymCentersByOwner called");
        return null;
    }
    
    @Override
    public boolean requestApproval(int centerId) {
        System.out.println("GymOwnerService.requestApproval called");
        return false;
    }
    
    @Override
    public GymOwner getGymOwnerById(int ownerId) {
        System.out.println("GymOwnerService.getGymOwnerById called");
        return null;
    }
}
