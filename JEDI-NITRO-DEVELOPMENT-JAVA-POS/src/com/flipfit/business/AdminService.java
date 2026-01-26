package com.flipfit.business;

import com.flipfit.bean.*;
import java.util.List;

public interface AdminService {
    
    public String getMonthlyDetails();
    
    public List<GymCenter> viewPendingGymRequest();
    
    public boolean approveRequest(int centerId);
    
    public boolean declineRequest(int centerId, String reason);
    
    public List<User> viewAllUsers();
    
    public List<GymCenter> viewAllGymCenters();
    
    public List<GymOwner> viewAllGymOwners();
    
    public boolean verifyGymOwner(int ownerId);
    
    public Admin getAdminById(int adminId);
    
    public boolean registerAdmin(Admin admin);
}
