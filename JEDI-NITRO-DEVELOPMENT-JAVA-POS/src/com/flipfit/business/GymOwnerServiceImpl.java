package com.flipfit.business;

import com.flipfit.bean.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class GymOwnerServiceImpl implements GymOwnerService {

    private static List<GymOwner> gymOwners = new ArrayList<>();
    private static List<GymCenter> gymCenters = new ArrayList<>();
    private static int slotCounter = 1;

    static {
        // Using 5-arg constructor: (id, name, email, password, isVerified)
        gymOwners.add(new GymOwner(501, "John Doe", "john@fit.com", "pass123", true));
        
        // Using 5-arg constructor: (centerId, name, location, approved, ownerId)
        GymCenter center1 = new GymCenter(101, "Elite Fitness", "HSR Layout", true, 501);
        center1.setSlots(new ArrayList<>());
        gymCenters.add(center1);
    }

    @Override
    public boolean registerGymOwner(GymOwner gymOwner) {
        return gymOwners.add(gymOwner);
    }

    @Override
    public GymOwner getGymOwnerById(int ownerId) {
        for (GymOwner owner : gymOwners) {
            if (owner.getId() == ownerId) return owner;
        }
        return null;
    }

    @Override
    public boolean addGymCenter(int ownerId, GymCenter center) {
        center.setOwnerId(ownerId);
        center.setApproved(false);
        if(center.getSlots() == null) center.setSlots(new ArrayList<>());
        return gymCenters.add(center);
    }

    @Override
    public boolean addSlot(int centerId, LocalTime startTime, LocalTime endTime, int totalSeats) {
        for (GymCenter center : gymCenters) {
            if (center.getCenterId() == centerId) {
                // Using 6-arg constructor: (slotId, startTime, endTime, totalSeats, availableSeats, centerId)
                Slot newSlot = new Slot(slotCounter++, startTime, endTime, totalSeats, totalSeats, centerId);
                return center.getSlots().add(newSlot);
            }
        }
        return false;
    }

    @Override
    public List<GymCenter> getGymCentersByOwner(int ownerId) {
        List<GymCenter> result = new ArrayList<>();
        for (GymCenter center : gymCenters) {
            if (center.getOwnerId() == ownerId) result.add(center);
        }
        return result;
    }

    @Override
    public List<Slot> manageSlots(int centerId) {
        for (GymCenter center : gymCenters) {
            if (center.getCenterId() == centerId) return center.getSlots();
        }
        return new ArrayList<>();
    }

    @Override
    public boolean updateCenterDetails(int id, String n, String l) { return true; }
    @Override
    public boolean requestApproval(int id) { return true; }
    @Override
    public boolean removeSlot(int id) { return true; }
    @Override
    public List<Booking> viewBookings(int id) { return new ArrayList<>(); }
    @Override
    public List<Booking> viewBookingsByCenter(int id) { return new ArrayList<>(); }
}