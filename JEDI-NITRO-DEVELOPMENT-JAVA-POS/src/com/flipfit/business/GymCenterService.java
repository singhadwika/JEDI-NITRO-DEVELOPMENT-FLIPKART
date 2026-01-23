package com.flipfit.business;

import com.flipfit.bean.GymCenter;
import com.flipfit.bean.Slot;
import java.util.List;

/**
 * Implementation of GymCenterServiceInterface.
 */
public class GymCenterService implements GymCenterServiceInterface {
    
    @Override
    public boolean addGymCenter(GymCenter center) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public GymCenter getGymCenterById(int centerId) {
        // TODO: Implement
        return null;
    }
    
    @Override
    public List<GymCenter> getAllGymCenters() {
        // TODO: Implement
        return null;
    }
    
    @Override
    public List<GymCenter> getApprovedGymCenters() {
        // TODO: Implement
        return null;
    }
    
    @Override
    public List<GymCenter> getPendingGymCenters() {
        // TODO: Implement
        return null;
    }
    
    @Override
    public List<GymCenter> getGymCentersByOwner(int ownerId) {
        // TODO: Implement
        return null;
    }
    
    @Override
    public boolean updateGymCenter(int centerId, String name, String location) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public boolean approveGymCenter(int centerId) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public boolean rejectGymCenter(int centerId) {
        // TODO: Implement
        return false;
    }
    
    @Override
    public List<Slot> viewAvailableSlots(int centerId) {
        // TODO: Implement
        return null;
    }
    
    @Override
    public GymCenter getCenterDetails(int centerId) {
        // TODO: Implement
        return null;
    }
    
    @Override
    public boolean requestForApproval(int centerId) {
        // TODO: Implement
        return false;
    }
}
