package com.flipfit.business;

import com.flipfit.bean.Waitlist;
import java.util.List;

/**
 * Implementation of WaitlistServiceInterface.
 */
public class WaitlistService implements WaitlistServiceInterface {
    
    @Override
    public boolean addToWaitlist(int userId, int slotId) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public boolean removeFromWaitlist(int userId, int slotId) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public int promoteUser(int slotId) {
        // TODO: Implement
        return -1;
    }
    
    @Override
    public Waitlist getWaitlistBySlot(int slotId) {
        // TODO: Implement
        return null;
    }
    
    @Override
    public int getUserPosition(int userId, int slotId) {
        // TODO: Implement
        return -1;
    }
    
    @Override
    public List<Waitlist> getWaitlistsByUser(int userId) {
        // TODO: Implement
        return null;
    }
    
    @Override
    public int getWaitlistSize(int slotId) {
        // TODO: Implement
        return 0;
    }
}
