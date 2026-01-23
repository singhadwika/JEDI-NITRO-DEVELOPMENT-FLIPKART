package com.flipfit.business;

import com.flipfit.bean.Booking;
import com.flipfit.bean.GymCenter;
import com.flipfit.bean.GymCustomer;
import com.flipfit.bean.Slot;
import java.util.List;

public interface GymCustomerServiceInterface {
    boolean registerCustomer(GymCustomer customer);

    List<Slot> viewSlots();

    List<Slot> viewSlotsByCenter(int centerId);

    List<GymCenter> viewCenters();

    List<GymCenter> viewApprovedCenters();

    Booking bookSlot(int customerId, int slotId, int centerId);

    boolean cancelSlot(int bookingId);

    List<Booking> viewWorkoutPlan(int customerId);

    List<Booking> getCustomerBookings(int customerId);

    GymCustomer getCustomerById(int customerId);
}

