package com.flipfit.bean;

import java.util.List;

public class Waitlist {
    private int waitlistId;
    private int slotId;
    private int position;
    private List<Integer> userIds;

    public Waitlist() {}

    public Waitlist(int waitlistId, int slotId) {}

    public int getWaitlistId() { return 0; }
    public void setWaitlistId(int waitlistId) {}
    public int getSlotId() { return 0; }
    public void setSlotId(int slotId) {}
    public int getPosition() { return 0; }
    public void setPosition(int position) {}
    public List<Integer> getUserIds() { return null; }
    public void setUserIds(List<Integer> userIds) {}

    public boolean addUser(int userId) { return false; }
    public boolean removeUser(int userId) { return false; }
    public int promoteUser() { return 0; }
    public int getPositionForUser(int userId) { return 0; }
    public int getWaitlistSize() { return 0; }
}
