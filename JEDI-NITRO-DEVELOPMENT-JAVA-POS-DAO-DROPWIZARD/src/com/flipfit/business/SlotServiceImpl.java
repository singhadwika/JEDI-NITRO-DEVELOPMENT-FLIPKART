package com.flipfit.business;

import com.flipfit.bean.Slot;
import com.flipfit.dao.SlotDAO;
import com.flipfit.dao.SlotDAOImpl;

import java.time.LocalTime;
import java.util.List;

public class SlotServiceImpl implements SlotService {

    private SlotDAO slotDAO = new SlotDAOImpl();

    @Override
    public Slot createSlot(int centerId, LocalTime startTime, LocalTime endTime, int totalSeats) {

        Slot slot = new Slot();

        slot.setStartTime(startTime);
        slot.setEndTime(endTime);
        slot.setTotalSeats(totalSeats);
        slot.setAvailableSeats(totalSeats);

        return slotDAO.createSlot(centerId, slot);
    }

    @Override
    public Slot getSlotById(int slotId) {
        return slotDAO.getSlotById(slotId);
    }

    @Override
    public List<Slot> getSlotsByCenter(int centerId) {
        return slotDAO.getSlotsByCenter(centerId);
    }

    @Override
    public List<Slot> getAvailableSlotsByCenter(int centerId) {
        return slotDAO.getAvailableSlotsByCenter(centerId);
    }

    @Override
    public boolean isSlotAvailable(int slotId) {
        return slotDAO.isSlotAvailable(slotId);
    }

    @Override
    public boolean lockSlot(int slotId) {
        return slotDAO.lockSlot(slotId);
    }

    @Override
    public boolean unlockSlot(int slotId) {
        return slotDAO.unlockSlot(slotId);
    }

    @Override
    public boolean updateSlot(int slotId, LocalTime startTime, LocalTime endTime, int totalSeats) {
        return slotDAO.updateSlot(slotId, startTime, endTime, totalSeats);
    }

    @Override
    public boolean deleteSlot(int slotId) {
        return slotDAO.deleteSlot(slotId);
    }
}
