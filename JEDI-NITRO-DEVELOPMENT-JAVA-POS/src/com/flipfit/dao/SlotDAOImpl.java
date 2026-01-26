package com.flipfit.dao;

import com.flipfit.bean.GymCenter;
import com.flipfit.bean.Slot;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SlotDAOImpl implements SlotDAO {

    private GymCenterDAO gymCenterDAO = new GymCenterDAOImpl();
    private static int slotCounter = 1;

    @Override
    public Slot createSlot(int centerId, Slot slot) {

        GymCenter center = gymCenterDAO.getGymCenterById(centerId);

        if (center == null) return null;

        slot.setSlotId(slotCounter++);
        center.getSlots().add(slot);

        return slot;
    }

    @Override
    public Slot getSlotById(int slotId) {

        List<GymCenter> centers = gymCenterDAO.getAllGymCenters();

        for (GymCenter center : centers) {
            for (Slot slot : center.getSlots()) {

                if (slot.getSlotId() == slotId) {
                    return slot;
                }
            }
        }

        return null;
    }

    @Override
    public List<Slot> getSlotsByCenter(int centerId) {

        GymCenter center = gymCenterDAO.getGymCenterById(centerId);

        if (center != null) {
            return center.getSlots();
        }

        return new ArrayList<>();
    }

    @Override
    public List<Slot> getAvailableSlotsByCenter(int centerId) {

        List<Slot> availableSlots = new ArrayList<>();

        GymCenter center = gymCenterDAO.getGymCenterById(centerId);

        if (center == null) return availableSlots;

        for (Slot slot : center.getSlots()) {

            if (slot.getAvailableSeats() > 0) {
                availableSlots.add(slot);
            }
        }

        return availableSlots;
    }

    @Override
    public boolean isSlotAvailable(int slotId) {

        Slot slot = getSlotById(slotId);

        return slot != null && slot.getAvailableSeats() > 0;
    }

    @Override
    public boolean lockSlot(int slotId) {

        Slot slot = getSlotById(slotId);

        if (slot != null && slot.getAvailableSeats() > 0) {

            slot.setAvailableSeats(slot.getAvailableSeats() - 1);
            return true;
        }

        return false;
    }

    @Override
    public boolean unlockSlot(int slotId) {

        Slot slot = getSlotById(slotId);

        if (slot != null) {

            if (slot.getAvailableSeats() < slot.getTotalSeats()) {

                slot.setAvailableSeats(slot.getAvailableSeats() + 1);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean updateSlot(int slotId, LocalTime startTime, LocalTime endTime, int totalSeats) {

        Slot slot = getSlotById(slotId);

        if (slot != null) {

            slot.setStartTime(startTime);
            slot.setEndTime(endTime);
            slot.setTotalSeats(totalSeats);

            if (slot.getAvailableSeats() > totalSeats) {
                slot.setAvailableSeats(totalSeats);
            }

            return true;
        }

        return false;
    }

    @Override
    public boolean deleteSlot(int slotId) {

        List<GymCenter> centers = gymCenterDAO.getAllGymCenters();

        for (GymCenter center : centers) {

            for (Slot slot : center.getSlots()) {

                if (slot.getSlotId() == slotId) {

                    center.getSlots().remove(slot);
                    return true;
                }
            }
        }

        return false;
    }
}
