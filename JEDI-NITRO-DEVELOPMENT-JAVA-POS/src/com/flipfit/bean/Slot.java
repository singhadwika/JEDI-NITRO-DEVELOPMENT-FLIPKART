package com.flipfit.bean;

import java.time.LocalTime;

public class Slot {
    private int slotId;
    private LocalTime startTime;
    private LocalTime endTime;
    private int totalSeats;
    private int availableSeats;
    private int centerId;

    public Slot() {}

    public Slot(int slotId, LocalTime startTime, LocalTime endTime, int totalSeats, int availableSeats, int centerId) {}

    public int getSlotId() { return 0; }
    public void setSlotId(int slotId) {}
    public LocalTime getStartTime() { return null; }
    public void setStartTime(LocalTime startTime) {}
    public LocalTime getEndTime() { return null; }
    public void setEndTime(LocalTime endTime) {}
    public int getTotalSeats() { return 0; }
    public void setTotalSeats(int totalSeats) {}
    public int getAvailableSeats() { return 0; }
    public void setAvailableSeats(int availableSeats) {}
    public int getCenterId() { return 0; }
    public void setCenterId(int centerId) {}

    public boolean isAvailable() { return false; }
    public boolean lockSlot() { return false; }
    public boolean unlockSlot() { return false; }
}
