package com.flipfit.dao;

import com.flipfit.bean.GymCenter;
import com.flipfit.bean.Slot;
import java.util.List;

public interface GymCenterDAO {

    boolean addGymCenter(GymCenter center);

    GymCenter getGymCenterById(int centerId);

    List<GymCenter> getAllGymCenters();

    List<GymCenter> getApprovedGymCenters();

    List<GymCenter> getPendingGymCenters();

    List<GymCenter> getGymCentersByOwner(int ownerId);

    boolean updateGymCenter(int centerId, String name, String location);

    boolean approveGymCenter(int centerId);

    boolean rejectGymCenter(int centerId);

    List<Slot> getAvailableSlots(int centerId);

    boolean requestForApproval(int centerId);
}
