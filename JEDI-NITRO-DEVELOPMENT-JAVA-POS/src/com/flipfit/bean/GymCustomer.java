package com.flipfit.bean;

import java.util.List;

public class GymCustomer extends User {
    private List<Slot> bookedSlots;

    public GymCustomer() {}

    public GymCustomer(int id, String name, String email, String password) {}

    public List<Slot> getBookedSlots() { return null; }
    public void setBookedSlots(List<Slot> bookedSlots) {}

    public List<Slot> viewSlots() { return null; }
    public void viewCenters() {}
    public boolean bookSlot(Slot slot) { return false; }
    public boolean cancelSlot(int slotId) { return false; }
    public void viewWorkoutPlan() {}
}
