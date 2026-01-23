package com.flipfit.business;

import com.flipfit.bean.Slot;
import java.time.LocalTime;
import java.util.List;

public interface SlotServiceInterface {
    Slot createSlot(int centerId, LocalTime startTime, LocalTime endTime, int totalSeats);

    Slot getSlotById(int slotId);

    List<Slot> getSlotsByCenter(int centerId);

    List<Slot> getAvailableSlotsByCenter(int centerId);

    boolean isSlotAvailable(int slotId);

    boolean lockSlot(int slotId);

    boolean unlockSlot(int slotId);

    boolean updateSlot(int slotId, LocalTime startTime, LocalTime endTime, int totalSeats);

    boolean deleteSlot(int slotId);
}

