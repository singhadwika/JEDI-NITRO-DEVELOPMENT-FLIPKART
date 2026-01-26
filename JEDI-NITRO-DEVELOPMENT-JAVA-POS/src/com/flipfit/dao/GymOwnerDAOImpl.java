package com.flipfit.dao;

import com.flipfit.bean.GymOwner;
import java.util.ArrayList;
import java.util.List;

public class GymOwnerDAOImpl implements GymOwnerDAO {

    private static List<GymOwner> ownerList = new ArrayList<>();
    private static int ownerCounter = 1;

    @Override
    public boolean addGymOwner(GymOwner owner) {

        owner.setId(ownerCounter++);
        owner.setVerified(false); // admin verification required
        ownerList.add(owner);

        return true;
    }

    @Override
    public GymOwner getGymOwnerById(int ownerId) {

        for (GymOwner owner : ownerList) {
            if (owner.getId() == ownerId) {
                return owner;
            }
        }

        return null;
    }

    @Override
    public List<GymOwner> getAllGymOwners() {
        return ownerList;
    }
}
