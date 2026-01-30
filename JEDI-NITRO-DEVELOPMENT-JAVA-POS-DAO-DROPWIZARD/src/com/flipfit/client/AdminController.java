package com.flipfit.client;

import com.flipfit.bean.Admin;
import com.flipfit.bean.GymCenter;
import com.flipfit.bean.GymOwner;
import com.flipfit.bean.User;
import com.flipfit.business.AdminService;
import com.flipfit.business.AdminServiceImpl;
import com.flipfit.dto.ApiResponse;
import com.flipfit.dto.DeclineRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * The Class AdminController.
 * REST Controller for admin operations in the FlipFit system.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
@Path("/api/admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminController {

    /** The admin service. */
    private final AdminService adminService;

    /**
     * Instantiates a new admin controller.
     */
    public AdminController() {
        this.adminService = new AdminServiceImpl();
    }

    /**
     * Retrieves admin details by admin ID.
     *
     * @param adminId the admin id
     * @return the response with admin details
     */
    @GET
    @Path("/{adminId}")
    public Response getAdminById(@PathParam("adminId") int adminId) {
        try {
            Admin admin = adminService.getAdminById(adminId);
            if (admin != null) {
                return Response.ok(new ApiResponse<>(true, "Admin found", admin)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new ApiResponse<>(false, "Admin not found", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving admin: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Registers a new admin in the system.
     *
     * @param admin the admin to register
     * @return the response indicating registration status
     */
    @POST
    @Path("/register")
    public Response registerAdmin(Admin admin) {
        try {
            admin.setRole("ADMIN");
            boolean success = adminService.registerAdmin(admin);
            if (success) {
                return Response.status(Response.Status.CREATED)
                        .entity(new ApiResponse<>(true, "Admin registered successfully", admin))
                        .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiResponse<>(false, "Admin registration failed", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error registering admin: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Views all pending gym center approval requests.
     *
     * @return the response with list of pending gym centers
     */
    @GET
    @Path("/gym-requests/pending")
    public Response viewPendingGymRequests() {
        try {
            List<GymCenter> pendingRequests = adminService.viewPendingGymRequest();
            return Response.ok(new ApiResponse<>(true, "Pending gym requests retrieved", pendingRequests)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving pending requests: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Approves a gym center request.
     *
     * @param centerId the center id
     * @return the response indicating approval status
     */
    @PUT
    @Path("/gym-requests/{centerId}/approve")
    public Response approveGymRequest(@PathParam("centerId") int centerId) {
        try {
            boolean success = adminService.approveRequest(centerId);
            if (success) {
                return Response.ok(new ApiResponse<>(true, "Gym center approved successfully", null)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiResponse<>(false, "Failed to approve gym center", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error approving gym center: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Declines a gym center request with a reason.
     *
     * @param centerId the center id
     * @param declineRequest the decline request containing reason
     * @return the response indicating decline status
     */
    @PUT
    @Path("/gym-requests/{centerId}/decline")
    public Response declineGymRequest(@PathParam("centerId") int centerId, DeclineRequest declineRequest) {
        try {
            boolean success = adminService.declineRequest(centerId, declineRequest.getReason());
            if (success) {
                return Response.ok(new ApiResponse<>(true, "Gym center declined successfully", null)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiResponse<>(false, "Failed to decline gym center", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error declining gym center: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Views all users in the system.
     *
     * @return the response with list of all users
     */
    @GET
    @Path("/users")
    public Response viewAllUsers() {
        try {
            List<User> users = adminService.viewAllUsers();
            return Response.ok(new ApiResponse<>(true, "Users retrieved successfully", users)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving users: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Views all gym centers in the system.
     *
     * @return the response with list of all gym centers
     */
    @GET
    @Path("/gym-centers")
    public Response viewAllGymCenters() {
        try {
            List<GymCenter> gymCenters = adminService.viewAllGymCenters();
            return Response.ok(new ApiResponse<>(true, "Gym centers retrieved successfully", gymCenters)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving gym centers: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Views all gym owners in the system.
     *
     * @return the response with list of all gym owners
     */
    @GET
    @Path("/gym-owners")
    public Response viewAllGymOwners() {
        try {
            List<GymOwner> gymOwners = adminService.viewAllGymOwners();
            return Response.ok(new ApiResponse<>(true, "Gym owners retrieved successfully", gymOwners)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving gym owners: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Verifies a gym owner.
     *
     * @param ownerId the owner id
     * @return the response indicating verification status
     */
    @PUT
    @Path("/gym-owners/{ownerId}/verify")
    public Response verifyGymOwner(@PathParam("ownerId") int ownerId) {
        try {
            boolean success = adminService.verifyGymOwner(ownerId);
            if (success) {
                return Response.ok(new ApiResponse<>(true, "Gym owner verified successfully", null)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiResponse<>(false, "Failed to verify gym owner", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error verifying gym owner: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Retrieves monthly details and reports.
     *
     * @return the response with monthly report
     */
    @GET
    @Path("/reports/monthly")
    public Response getMonthlyReport() {
        try {
            String report = adminService.getMonthlyDetails();
            return Response.ok(new ApiResponse<>(true, "Monthly report retrieved", report)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving monthly report: " + e.getMessage(), null))
                    .build();
        }
    }
}
