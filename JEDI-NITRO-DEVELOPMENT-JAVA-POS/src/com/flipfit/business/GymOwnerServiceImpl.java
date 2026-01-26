package com.flipfit.business;

import com.flipfit.bean.*;
import com.flipfit.dao.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class GymOwnerServiceImpl implements GymOwnerService {

    private GymOwnerDAO ownerDAO = new GymOwnerDAOImpl();
    private GymCenterDAO centerDAO = new GymCenterDAOImpl();
    private BookingService bookingService = new BookingServiceImpl();

    private static int slotCounter = 1;

    // ---------------- OWNER ----------------

    @Override
    public boolean registerGymOwner(GymOwner gymOwner) {
        return ownerDAO.addGymOwner(gymOwner);
    }

    @Override
    public GymOwner getGymOwnerById(int ownerId) {
        return ownerDAO.getGymOwnerById(ownerId);
    }

    // ---------------- CENTER ----------------

    @Override
    public boolean addGymCenter(int ownerId, GymCenter center) {

        center.setOwnerId(ownerId);
        center.setApproved(false);

        return centerDAO.addGymCenter(center);
    }

    @Override
    public boolean updateCenterDetails(int centerId, String name, String location) {
        return centerDAO.updateGymCenter(centerId, name, location);
    }

    @Override
    public List<GymCenter> getGymCentersByOwner(int ownerId) {
        return centerDAO.getGymCentersByOwner(ownerId);
    }

    @Override
    public boolean requestApproval(int centerId) {
        return centerDAO.requestForApproval(centerId);
    }

    // ---------------- SLOT ----------------

    @Override
    public boolean addSlot(int centerId, LocalTime startTime, LocalTime endTime, int totalSeats) {

        GymCenter center = centerDAO.getGymCenterById(centerId);

        if (center == null) return false;

        Slot slot = new Slot();

        slot.setSlotId(slotCounter++);
        slot.setStartTime(startTime);
        slot.setEndTime(endTime);
        slot.setTotalSeats(totalSeats);
        slot.setAvailableSeats(totalSeats);

        center.getSlots().add(slot);

        return true;
    }

    @Override
    public boolean removeSlot(int slotId) {

        List<GymCenter> centers = centerDAO.getAllGymCenters();

        for (GymCenter center : centers) {

            List<Slot> slots = center.getSlots();

            for (Slot slot : slots) {
                if (slot.getSlotId() == slotId) {
                    slots.remove(slot);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public List<Slot> manageSlots(int centerId) {

        GymCenter center = centerDAO.getGymCenterById(centerId);

        if (center != null) {
            return center.getSlots();
        }

        return new ArrayList<>();
    }

    // ---------------- BOOKINGS ----------------

    @Override
    public List<Booking> viewBookings(int ownerId) {

        List<Booking> ownerBookings = new ArrayList<>();

        List<GymCenter> centers = centerDAO.getGymCentersByOwner(ownerId);

        for (GymCenter center : centers) {
            ownerBookings.addAll(
                    bookingService.getBookingsByCenter(center.getCenterId())
            );
        }

        return ownerBookings;
    }

    @Override
    public List<Booking> viewBookingsByCenter(int centerId) {
        return bookingService.getBookingsByCenter(centerId);
    }
}
