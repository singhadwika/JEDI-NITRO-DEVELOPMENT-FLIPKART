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
        System.out.println("GymCenterService.addGymCenter called");
        return false;
    }
    
    @Override
    public GymCenter getGymCenterById(int centerId) {
        System.out.println("GymCenterService.getGymCenterById called");
        return null;
    }
    
    @Override
    public List<GymCenter> getAllGymCenters() {
        System.out.println("GymCenterService.getAllGymCenters called");
        return null;
    }
    
    @Override
    public List<GymCenter> getApprovedGymCenters() {
        System.out.println("GymCenterService.getApprovedGymCenters called");
        return null;
    }
    
    @Override
    public List<GymCenter> getPendingGymCenters() {
        System.out.println("GymCenterService.getPendingGymCenters called");
        return null;
    }
    
    @Override
    public List<GymCenter> getGymCentersByOwner(int ownerId) {
        System.out.println("GymCenterService.getGymCentersByOwner called");
        return null;
    }
    
    @Override
    public boolean updateGymCenter(int centerId, String name, String location) {
        System.out.println("GymCenterService.updateGymCenter called");
        return false;
    }
    
    @Override
    public boolean approveGymCenter(int centerId) {
        System.out.println("GymCenterService.approveGymCenter called");
        return false;
    }
    
    @Override
    public boolean rejectGymCenter(int centerId) {
        System.out.println("GymCenterService.rejectGymCenter called");
        return false;
    }
    
    @Override
    public List<Slot> viewAvailableSlots(int centerId) {
        System.out.println("GymCenterService.viewAvailableSlots called");
        return null;
    }
    
    @Override
    public GymCenter getCenterDetails(int centerId) {
        System.out.println("GymCenterService.getCenterDetails called");
        return null;
    }
    
    @Override
    public boolean requestForApproval(int centerId) {
        System.out.println("GymCenterService.requestForApproval called");
        return false;
    }
}
