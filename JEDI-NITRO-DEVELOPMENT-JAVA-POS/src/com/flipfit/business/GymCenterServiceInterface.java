package com.flipfit.business;

import com.flipfit.bean.GymCenter;
import com.flipfit.bean.Slot;
import java.util.List;

public interface GymCenterServiceInterface {
    boolean addGymCenter(GymCenter center);

    GymCenter getGymCenterById(int centerId);

    List<GymCenter> getAllGymCenters();

    List<GymCenter> getApprovedGymCenters();

    List<GymCenter> getPendingGymCenters();

    List<GymCenter> getGymCentersByOwner(int ownerId);

    boolean updateGymCenter(int centerId, String name, String location);

    boolean approveGymCenter(int centerId);

    boolean rejectGymCenter(int centerId);

    List<Slot> viewAvailableSlots(int centerId);

    GymCenter getCenterDetails(int centerId);

    boolean requestForApproval(int centerId);
}
