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
        System.out.println("SlotService.createSlot called");
        return null;
    }
    
    @Override
    public Slot getSlotById(int slotId) {
        System.out.println("SlotService.getSlotById called");
        return null;
    }
    
    @Override
    public List<Slot> getSlotsByCenter(int centerId) {
        System.out.println("SlotService.getSlotsByCenter called");
        return null;
    }
    
    @Override
    public List<Slot> getAvailableSlotsByCenter(int centerId) {
        System.out.println("SlotService.getAvailableSlotsByCenter called");
        return null;
    }
    
    @Override
    public boolean isSlotAvailable(int slotId) {
        System.out.println("SlotService.isSlotAvailable called");
        return false;
    }
    
    @Override
    public boolean lockSlot(int slotId) {
        System.out.println("SlotService.lockSlot called");
        return false;
    }
    
    @Override
    public boolean unlockSlot(int slotId) {
        System.out.println("SlotService.unlockSlot called");
        return false;
    }
    
    @Override
    public boolean updateSlot(int slotId, LocalTime startTime, LocalTime endTime, int totalSeats) {
        System.out.println("SlotService.updateSlot called");
        return false;
    }
    
    @Override
    public boolean deleteSlot(int slotId) {
        System.out.println("SlotService.deleteSlot called");
        return false;
    }
}
