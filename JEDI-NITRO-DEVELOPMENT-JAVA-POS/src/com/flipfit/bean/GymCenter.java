package com.flipfit.bean;

import java.util.List;

public class GymCenter {
    private int centerId;
    private String name;
    private String location;
    private List<Slot> slots;
    private boolean approved;
    private int ownerId;

    public GymCenter() {}

    public GymCenter(int centerId, String name, String location, boolean approved, int ownerId) {}

    public int getCenterId() { return 0; }
    public void setCenterId(int centerId) {}
    public String getName() { return null; }
    public void setName(String name) {}
    public String getLocation() { return null; }
    public void setLocation(String location) {}
    public List<Slot> getSlots() { return null; }
    public void setSlots(List<Slot> slots) {}
    public boolean isApproved() { return false; }
    public void setApproved(boolean approved) {}
    public int getOwnerId() { return 0; }
    public void setOwnerId(int ownerId) {}

    public List<Slot> viewAvailableSlots() { return null; }
    public GymCenter getCenterDetails() { return null; }
    public boolean requestForApproval() { return false; }
    public void addSlot(Slot slot) {}
    public void removeSlot(int slotId) {}
}
