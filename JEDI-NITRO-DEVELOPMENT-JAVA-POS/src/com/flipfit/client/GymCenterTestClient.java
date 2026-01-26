package com.flipfit.client;

import com.flipfit.business.GymCenterService;
import com.flipfit.business.GymCenterServiceImpl;
import com.flipfit.bean.GymCenter;
import java.util.List;

public class GymCenterTestClient {
    public static void main(String[] args) {
        GymCenterService service = new GymCenterServiceImpl();
        System.out.println("=== Gym Center Service Test ===\n");

        // 1. Show all centers (Hardcoded)
        System.out.println("Total Centers: " + service.getAllGymCenters().size());

        // 2. Filter Pending
        List<GymCenter> pending = service.getPendingGymCenters();
        System.out.println("Pending Centers count: " + pending.size());

        // 3. Approve a center (Update)
        if(!pending.isEmpty()) {
            int idToApprove = pending.get(0).getCenterId();
            System.out.println("Approving Center ID: " + idToApprove);
            service.approveGymCenter(idToApprove);
            System.out.println("New Pending count: " + service.getPendingGymCenters().size());
        }

        // 4. Reject/Delete a center
        service.rejectGymCenter(1);
        System.out.println("Centers remaining after rejecting ID 1: " + service.getAllGymCenters().size());
    }
}