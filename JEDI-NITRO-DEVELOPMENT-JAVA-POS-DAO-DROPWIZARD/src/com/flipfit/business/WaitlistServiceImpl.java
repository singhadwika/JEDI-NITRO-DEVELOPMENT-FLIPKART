package com.flipfit.business;

import com.flipfit.bean.Waitlist;
import com.flipfit.dao.*;
import java.util.List;

public class WaitlistServiceImpl implements WaitlistService {

    private WaitlistDAO waitlistDAO = new WaitlistDAOImpl();
    private SlotService slotService = new SlotServiceImpl();
    private BookingService bookingService = new BookingServiceImpl();
    private NotificationService notificationService = new NotificationServiceImpl();

    // ---------------- ADD ----------------

    @Override
    public boolean addToWaitlist(int userId, int slotId) {

        Waitlist waitlist = new Waitlist(userId, slotId);

        return waitlistDAO.addToWaitlist(waitlist);
    }

    // ---------------- REMOVE ----------------

    @Override
    public boolean removeFromWaitlist(int userId, int slotId) {
        return waitlistDAO.removeFromWaitlist(userId, slotId);
    }

    // ---------------- PROMOTE ----------------

    @Override
    public int promoteUser(int slotId) {

        // Check slot availability
        if (!slotService.isSlotAvailable(slotId)) {
            return -1;
        }

        Waitlist promoted = waitlistDAO.promoteUser(slotId);

        if (promoted == null) {
            return -1;
        }

        // Lock slot
        slotService.lockSlot(slotId);

        // Notify user
        notificationService.sendNotification(
                promoted.getUserId(),
                "WAITLIST_PROMOTION",
                "Slot is now available! You have been promoted from waitlist."
        );

        return promoted.getUserId();
    }

    // ---------------- FETCH ----------------

    @Override
    public Waitlist getWaitlistBySlot(int slotId) {
        return waitlistDAO.getWaitlistBySlot(slotId);
    }

    @Override
    public int getUserPosition(int userId, int slotId) {
        return waitlistDAO.getUserPosition(userId, slotId);
    }

    @Override
    public List<Waitlist> getWaitlistsByUser(int userId) {
        return waitlistDAO.getWaitlistsByUser(userId);
    }

    @Override
    public int getWaitlistSize(int slotId) {
        return waitlistDAO.getWaitlistSize(slotId);
    }
}
