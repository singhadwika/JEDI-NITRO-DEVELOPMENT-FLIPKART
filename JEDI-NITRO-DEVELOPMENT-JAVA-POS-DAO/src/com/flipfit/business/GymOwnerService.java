package com.flipfit.business;

import com.flipfit.bean.*;
import java.time.LocalTime;
import java.util.List;

public interface GymOwnerService {
    
    public boolean registerGymOwner(GymOwner gymOwner);
    
    public boolean addGymCenter(int ownerId, GymCenter center);
    
    public boolean updateCenterDetails(int centerId, String name, String location);
    
    public Slot addSlot(int centerId, LocalTime startTime, LocalTime endTime, int totalSeats);
    
    public boolean removeSlot(int slotId);
    
    public List<Slot> manageSlots(int centerId);
    
    public List<Booking> viewBookings(int ownerId);
    
    public List<Booking> viewBookingsByCenter(int centerId);
    
    public List<GymCenter> getGymCentersByOwner(int ownerId);
    
    public boolean requestApproval(int centerId);
    
    public GymOwner getGymOwnerById(int ownerId);
}
