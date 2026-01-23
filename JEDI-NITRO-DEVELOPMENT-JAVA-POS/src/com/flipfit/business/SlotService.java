package com.flipfit.business;

import com.flipfit.bean.Slot;
import java.time.LocalTime;
import java.util.List;

/**
 * Implementation of SlotServiceInterface.
 */
public class SlotService implements SlotServiceInterface {
    
    @Override
    public Slot createSlot(int centerId, LocalTime startTime, LocalTime endTime, int totalSeats) {
        // TODO: Implement
        return null;
    }
    
    @Override
    public Slot getSlotById(int slotId) {
        // TODO: Implement
        return null;
    }
    
    @Override
    public List<Slot> getSlotsByCenter(int centerId) {
        // TODO: Implement
        return null;
    }
    
    @Override
    public List<Slot> getAvailableSlotsByCenter(int centerId) {
        // TODO: Implement
        return null;
    }
    
    @Override
    public boolean isSlotAvailable(int slotId) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public boolean lockSlot(int slotId) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public boolean unlockSlot(int slotId) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public boolean updateSlot(int slotId, LocalTime startTime, LocalTime endTime, int totalSeats) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public boolean deleteSlot(int slotId) {
        // TODO: Implement
        return false;
    }
}
