package com.flipfit.business;

import com.flipfit.bean.GymCenter;
import com.flipfit.bean.Slot;
import com.flipfit.dao.GymCenterDAO;
import com.flipfit.dao.GymCenterDAOImpl;

import java.util.List;

public class GymCenterServiceImpl implements GymCenterService {

    private GymCenterDAO gymCenterDAO = new GymCenterDAOImpl();

    @Override
    public boolean addGymCenter(GymCenter center) {
        return gymCenterDAO.addGymCenter(center);
    }

    @Override
    public GymCenter getGymCenterById(int centerId) {
        return gymCenterDAO.getGymCenterById(centerId);
    }

    @Override
    public List<GymCenter> getAllGymCenters() {
        return gymCenterDAO.getAllGymCenters();
    }

    @Override
    public List<GymCenter> getApprovedGymCenters() {
        return gymCenterDAO.getApprovedGymCenters();
    }

    @Override
    public List<GymCenter> getPendingGymCenters() {
        return gymCenterDAO.getPendingGymCenters();
    }

    @Override
    public List<GymCenter> getGymCentersByOwner(int ownerId) {
        return gymCenterDAO.getGymCentersByOwner(ownerId);
    }

    @Override
    public boolean updateGymCenter(int centerId, String name, String location) {
        return gymCenterDAO.updateGymCenter(centerId, name, location);
    }

    @Override
    public boolean approveGymCenter(int centerId) {
        return gymCenterDAO.approveGymCenter(centerId);
    }

    @Override
    public boolean rejectGymCenter(int centerId) {
        return gymCenterDAO.rejectGymCenter(centerId);
    }

    @Override
    public List<Slot> viewAvailableSlots(int centerId) {
        return gymCenterDAO.getAvailableSlots(centerId);
    }

    @Override
    public GymCenter getCenterDetails(int centerId) {
        return gymCenterDAO.getGymCenterById(centerId);
    }

    @Override
    public boolean requestForApproval(int centerId) {
        return gymCenterDAO.requestForApproval(centerId);
    }
}
