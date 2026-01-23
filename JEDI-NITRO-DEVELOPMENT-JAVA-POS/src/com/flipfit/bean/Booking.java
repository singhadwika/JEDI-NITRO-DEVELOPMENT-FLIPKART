package com.flipfit.bean;

import java.time.LocalDate;

public class Booking {
    private int bookingId;
    private LocalDate bookingDate;
    private boolean status;
    private int userId;
    private int slotId;
    private int centerId;

    public Booking() {}

    public Booking(int bookingId, LocalDate bookingDate, boolean status, int userId, int slotId, int centerId) {}

    public int getBookingId() { return 0; }
    public void setBookingId(int bookingId) {}
    public LocalDate getBookingDate() { return null; }
    public void setBookingDate(LocalDate bookingDate) {}
    public boolean isStatus() { return false; }
    public void setStatus(boolean status) {}
    public int getUserId() { return 0; }
    public void setUserId(int userId) {}
    public int getSlotId() { return 0; }
    public void setSlotId(int slotId) {}
    public int getCenterId() { return 0; }
    public void setCenterId(int centerId) {}

    public boolean confirmBooking() { return false; }
    public boolean cancelBooking() { return false; }
}
