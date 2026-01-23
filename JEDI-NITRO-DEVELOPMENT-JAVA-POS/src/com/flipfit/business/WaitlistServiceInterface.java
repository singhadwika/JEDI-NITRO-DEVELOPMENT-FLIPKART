package com.flipfit.business;

import com.flipfit.bean.Waitlist;
import java.util.List;

public interface WaitlistServiceInterface {
    boolean addToWaitlist(int userId, int slotId);

    boolean removeFromWaitlist(int userId, int slotId);

    int promoteUser(int slotId);

    Waitlist getWaitlistBySlot(int slotId);

    int getUserPosition(int userId, int slotId);

    List<Waitlist> getWaitlistsByUser(int userId);

    int getWaitlistSize(int slotId);
}
