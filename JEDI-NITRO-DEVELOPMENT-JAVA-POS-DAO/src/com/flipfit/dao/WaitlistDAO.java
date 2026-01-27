package com.flipfit.dao;

import com.flipfit.bean.Waitlist;
import java.util.List;

public interface WaitlistDAO {

    boolean addToWaitlist(Waitlist waitlist);

    boolean removeFromWaitlist(int userId, int slotId);

    Waitlist getWaitlistBySlot(int slotId);

    List<Waitlist> getWaitlistsByUser(int userId);

    int getWaitlistSize(int slotId);

    int getUserPosition(int userId, int slotId);

    Waitlist promoteUser(int slotId);
}
