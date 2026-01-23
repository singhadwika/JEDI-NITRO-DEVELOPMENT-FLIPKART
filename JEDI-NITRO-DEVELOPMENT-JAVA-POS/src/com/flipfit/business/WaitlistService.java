package com.flipfit.business;

import com.flipfit.bean.Waitlist;
import java.util.List;

/**
 * Implementation of WaitlistServiceInterface.
 */
public class WaitlistService implements WaitlistServiceInterface {
    
    @Override
    public boolean addToWaitlist(int userId, int slotId) {
        System.out.println("WaitlistService.addToWaitlist called");
        return false;
    }
    
    @Override
    public boolean removeFromWaitlist(int userId, int slotId) {
        System.out.println("WaitlistService.removeFromWaitlist called");
        return false;
    }
    
    @Override
    public int promoteUser(int slotId) {
        System.out.println("WaitlistService.promoteUser called");
        return -1;
    }
    
    @Override
    public Waitlist getWaitlistBySlot(int slotId) {
        System.out.println("WaitlistService.getWaitlistBySlot called");
        return null;
    }
    
    @Override
    public int getUserPosition(int userId, int slotId) {
        System.out.println("WaitlistService.getUserPosition called");
        return -1;
    }
    
    @Override
    public List<Waitlist> getWaitlistsByUser(int userId) {
        System.out.println("WaitlistService.getWaitlistsByUser called");
        return null;
    }
    
    @Override
    public int getWaitlistSize(int slotId) {
        System.out.println("WaitlistService.getWaitlistSize called");
        return 0;
    }
}
