package com.flipfit.business;

import com.flipfit.bean.GymCenter;
import com.flipfit.bean.Slot;
import java.util.List;

public interface GymCenterService {
    
    public boolean addGymCenter(GymCenter center);
    
    public GymCenter getGymCenterById(int centerId);

    public List<GymCenter> getAllGymCenters();
    
    public List<GymCenter> getApprovedGymCenters();
    
    public List<GymCenter> getPendingGymCenters();
    
    public List<GymCenter> getGymCentersByOwner(int ownerId);
    
    public boolean updateGymCenter(int centerId, String name, String location);
    
    public boolean approveGymCenter(int centerId);
    
    public boolean rejectGymCenter(int centerId);
    
    public List<Slot> viewAvailableSlots(int centerId);
    
    public GymCenter getCenterDetails(int centerId);
    
    public boolean requestForApproval(int centerId);
}
