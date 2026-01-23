package com.flipfit.business;

import com.flipfit.bean.Admin;
import com.flipfit.bean.GymCenter;
import com.flipfit.bean.GymOwner;
import com.flipfit.bean.User;
import java.util.List;

public interface AdminServiceInterface {
    String getMonthlyDetails();

    List<GymCenter> viewPendingGymRequest();

    boolean approveRequest(int centerId);

    boolean declineRequest(int centerId, String reason);

    List<User> viewAllUsers();

    List<GymCenter> viewAllGymCenters();

    List<GymOwner> viewAllGymOwners();

    boolean verifyGymOwner(int ownerId);

    Admin getAdminById(int adminId);
}

