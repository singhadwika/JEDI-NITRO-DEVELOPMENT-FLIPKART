package com.flipfit.dao;

import com.flipfit.bean.*;
import java.util.List;

public interface AdminDAO {

    boolean addAdmin(Admin admin);

    Admin getAdminById(int adminId);

    List<GymCenter> getPendingGymCenters();

    boolean approveGymCenter(int centerId);

    boolean declineGymCenter(int centerId, String reason);

    List<User> getAllUsers();

    List<GymCenter> getAllGymCenters();

    List<GymOwner> getAllGymOwners();

    boolean verifyGymOwner(int ownerId);

    String getMonthlyReport();
}
