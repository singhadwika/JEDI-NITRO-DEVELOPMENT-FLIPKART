package com.flipfit.business;

import com.flipfit.bean.Waitlist;
import java.util.List;

public interface WaitlistService {
    
    public boolean addToWaitlist(int userId, int slotId);

    public boolean removeFromWaitlist(int userId, int slotId);

    public int promoteUser(int slotId);

    public Waitlist getWaitlistBySlot(int slotId);
    
    public int getUserPosition(int userId, int slotId);

    public List<Waitlist> getWaitlistsByUser(int userId);

    public int getWaitlistSize(int slotId);
}
