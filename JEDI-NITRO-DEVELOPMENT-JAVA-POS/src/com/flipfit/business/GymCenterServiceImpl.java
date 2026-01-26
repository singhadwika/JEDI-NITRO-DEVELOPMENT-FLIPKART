package com.flipfit.business;

import com.flipfit.bean.GymCenter;
import com.flipfit.bean.Slot;
import java.util.ArrayList;
import java.util.List;

public class GymCenterServiceImpl implements GymCenterService {

    // Internal data store replacing the DAO
    private static List<GymCenter> gymCenters = new ArrayList<>();
    private static int centerCounter = 1;

    // Static block to provide hardcoded centers for your demo
    static {
        // Constructor: GymCenter(int centerId, String name, String location, boolean approved, int ownerId)
        gymCenters.add(new GymCenter(centerCounter++, "Elite Fitness", "HSR Layout", true, 501));
        gymCenters.add(new GymCenter(centerCounter++, "Power House", "Koramangala", false, 502));
        gymCenters.add(new GymCenter(centerCounter++, "Fit & Fine", "Bellandur", false, 501));
    }

    @Override
    public boolean addGymCenter(GymCenter center) {
        center.setCenterId(centerCounter++);
        center.setApproved(false); // Default to pending as per your requirement
        return gymCenters.add(center);
    }

    @Override
    public GymCenter getGymCenterById(int centerId) {
        for (GymCenter center : gymCenters) {
            if (center.getCenterId() == centerId) {
                return center;
            }
        }
        return null;
    }

    @Override
    public List<GymCenter> getAllGymCenters() {
        return gymCenters;
    }

    @Override
    public List<GymCenter> getApprovedGymCenters() {
        List<GymCenter> approvedCenters = new ArrayList<>();
        for (GymCenter center : gymCenters) {
            if (center.isApproved()) {
                approvedCenters.add(center);
            }
        }
        return approvedCenters;
    }

    @Override
    public List<GymCenter> getPendingGymCenters() {
        List<GymCenter> pendingCenters = new ArrayList<>();
        for (GymCenter center : gymCenters) {
            if (!center.isApproved()) {
                pendingCenters.add(center);
            }
        }
        return pendingCenters;
    }

    @Override
    public List<GymCenter> getGymCentersByOwner(int ownerId) {
        List<GymCenter> ownerCenters = new ArrayList<>();
        for (GymCenter center : gymCenters) {
            if (center.getOwnerId() == ownerId) {
                ownerCenters.add(center);
            }
        }
        return ownerCenters;
    }

    @Override
    public boolean updateGymCenter(int centerId, String name, String location) {
        for (GymCenter center : gymCenters) {
            if (center.getCenterId() == centerId) {
                center.setName(name);
                center.setLocation(location);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean approveGymCenter(int centerId) {
        for (GymCenter center : gymCenters) {
            if (center.getCenterId() == centerId) {
                center.setApproved(true);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean rejectGymCenter(int centerId) {
        return gymCenters.removeIf(center -> center.getCenterId() == centerId);
    }

    @Override
    public List<Slot> viewAvailableSlots(int centerId) {
        GymCenter center = getGymCenterById(centerId);
        if (center != null) {
            return center.getSlots();
        }
        return new ArrayList<>();
    }

    @Override
    public GymCenter getCenterDetails(int centerId) {
        return getGymCenterById(centerId);
    }

    @Override
    public boolean requestForApproval(int centerId) {
        GymCenter center = getGymCenterById(centerId);
        if (center != null) {
            center.setApproved(false); // Sets back to pending
            return true;
        }
        return false;
    }
}