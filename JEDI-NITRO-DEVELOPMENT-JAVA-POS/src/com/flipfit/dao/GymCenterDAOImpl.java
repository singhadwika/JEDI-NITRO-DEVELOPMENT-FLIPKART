package com.flipfit.dao;

import com.flipfit.bean.GymCenter;
import com.flipfit.bean.Slot;

import java.util.ArrayList;
import java.util.List;

public class GymCenterDAOImpl implements GymCenterDAO {

    private static List<GymCenter> gymCenters = new ArrayList<>();
    private static int centerCounter = 1;

    @Override
    public boolean addGymCenter(GymCenter center) {

        center.setCenterId(centerCounter++);
        center.setApproved(false); // default pending
        
        // Initialize slots list if null
        if (center.getSlots() == null) {
            center.setSlots(new ArrayList<>());
        }
        
        gymCenters.add(center);

        return true;
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

        for (GymCenter center : gymCenters) {
            if (center.getCenterId() == centerId) {

                gymCenters.remove(center);
                return true;
            }
        }

        return false;
    }

    @Override
    public List<Slot> getAvailableSlots(int centerId) {

        GymCenter center = getGymCenterById(centerId);

        if (center != null) {
            return center.getSlots();
        }

        return new ArrayList<>();
    }

    @Override
    public boolean requestForApproval(int centerId) {

        GymCenter center = getGymCenterById(centerId);

        if (center != null) {
            center.setApproved(false);
            return true;
        }

        return false;
    }
}
