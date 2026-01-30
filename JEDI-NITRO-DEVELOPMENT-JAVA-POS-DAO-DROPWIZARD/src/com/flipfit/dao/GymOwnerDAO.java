package com.flipfit.dao;

import com.flipfit.bean.GymOwner;
import java.util.List;

public interface GymOwnerDAO {

    boolean addGymOwner(GymOwner owner);

    GymOwner getGymOwnerById(int ownerId);

    List<GymOwner> getAllGymOwners();
}
