package com.flipfit.business;

import com.flipfit.bean.Slot;
import java.time.LocalTime;
import java.util.List;

public interface SlotService {
    
    public Slot createSlot(int centerId, LocalTime startTime, LocalTime endTime, int totalSeats);
    
    public Slot getSlotById(int slotId);

    public List<Slot> getSlotsByCenter(int centerId);

    public List<Slot> getAvailableSlotsByCenter(int centerId);

    public boolean isSlotAvailable(int slotId);

    public boolean lockSlot(int slotId);

    public boolean unlockSlot(int slotId);

    public boolean updateSlot(int slotId, LocalTime startTime, LocalTime endTime, int totalSeats);

    public boolean deleteSlot(int slotId)
}
