package com.flipfit.business;

import com.flipfit.bean.Booking;
import com.flipfit.bean.GymCenter;
import com.flipfit.bean.GymOwner;
import com.flipfit.bean.Slot;
import java.time.LocalTime;
import java.util.List;

public interface GymOwnerServiceInterface {
    boolean registerGymOwner(GymOwner gymOwner);

    boolean addGymCenter(int ownerId, GymCenter center);

    boolean updateCenterDetails(int centerId, String name, String location);

    boolean addSlot(int centerId, LocalTime startTime, LocalTime endTime, int totalSeats);

    boolean removeSlot(int slotId);

    List<Slot> manageSlots(int centerId);

    List<Booking> viewBookings(int ownerId);

    List<Booking> viewBookingsByCenter(int centerId);

    List<GymCenter> getGymCentersByOwner(int ownerId);

    boolean requestApproval(int centerId);

    GymOwner getGymOwnerById(int ownerId);
}

