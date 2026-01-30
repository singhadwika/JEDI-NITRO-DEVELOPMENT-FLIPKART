package com.flipfit.client;

import com.flipfit.bean.GymCenter;
import com.flipfit.bean.Slot;
import com.flipfit.business.GymCenterService;
import com.flipfit.business.GymCenterServiceImpl;
import com.flipfit.dto.ApiResponse;
import com.flipfit.dto.UpdateCenterRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * The Class GymCenterController.
 * REST Controller for gym center operations in the FlipFit system.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
@Path("/api/centers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GymCenterController {

    /** The gym center service. */
    private final GymCenterService centerService;

    /**
     * Instantiates a new gym center controller.
     */
    public GymCenterController() {
        this.centerService = new GymCenterServiceImpl();
    }

    /**
     * Adds a new gym center to the system.
     *
     * @param center the gym center to add
     * @return the response indicating addition status
     */
    @POST
    public Response addGymCenter(GymCenter center) {
        try {
            boolean success = centerService.addGymCenter(center);
            if (success) {
                return Response.status(Response.Status.CREATED)
                        .entity(new ApiResponse<>(true, "Gym center added successfully", center))
                        .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiResponse<>(false, "Failed to add gym center", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error adding gym center: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Retrieves a gym center by its ID.
     *
     * @param centerId the center id
     * @return the response with gym center details
     */
    @GET
    @Path("/{centerId}")
    public Response getGymCenterById(@PathParam("centerId") int centerId) {
        try {
            GymCenter center = centerService.getGymCenterById(centerId);
            if (center != null) {
                return Response.ok(new ApiResponse<>(true, "Gym center found", center)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new ApiResponse<>(false, "Gym center not found", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving gym center: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Retrieves all gym centers in the system.
     *
     * @return the response with list of all gym centers
     */
    @GET
    public Response getAllGymCenters() {
        try {
            List<GymCenter> centers = centerService.getAllGymCenters();
            return Response.ok(new ApiResponse<>(true, "Gym centers retrieved successfully", centers)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving gym centers: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Retrieves all approved gym centers.
     *
     * @return the response with list of approved gym centers
     */
    @GET
    @Path("/approved")
    public Response getApprovedGymCenters() {
        try {
            List<GymCenter> centers = centerService.getApprovedGymCenters();
            return Response.ok(new ApiResponse<>(true, "Approved gym centers retrieved successfully", centers)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving approved gym centers: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Retrieves all pending gym centers waiting for approval.
     *
     * @return the response with list of pending gym centers
     */
    @GET
    @Path("/pending")
    public Response getPendingGymCenters() {
        try {
            List<GymCenter> centers = centerService.getPendingGymCenters();
            return Response.ok(new ApiResponse<>(true, "Pending gym centers retrieved successfully", centers)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving pending gym centers: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Retrieves all gym centers owned by a specific owner.
     *
     * @param ownerId the owner id
     * @return the response with list of gym centers
     */
    @GET
    @Path("/owner/{ownerId}")
    public Response getGymCentersByOwner(@PathParam("ownerId") int ownerId) {
        try {
            List<GymCenter> centers = centerService.getGymCentersByOwner(ownerId);
            return Response.ok(new ApiResponse<>(true, "Gym centers retrieved successfully", centers)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving gym centers: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Updates the details of an existing gym center.
     *
     * @param centerId the center id
     * @param updateRequest the update request
     * @return the response indicating update status
     */
    @PUT
    @Path("/{centerId}")
    public Response updateGymCenter(@PathParam("centerId") int centerId, UpdateCenterRequest updateRequest) {
        try {
            boolean success = centerService.updateGymCenter(centerId, updateRequest.getName(), updateRequest.getLocation());
            if (success) {
                return Response.ok(new ApiResponse<>(true, "Gym center updated successfully", null)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiResponse<>(false, "Failed to update gym center", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error updating gym center: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Approves a gym center for operation.
     *
     * @param centerId the center id
     * @return the response indicating approval status
     */
    @PUT
    @Path("/{centerId}/approve")
    public Response approveGymCenter(@PathParam("centerId") int centerId) {
        try {
            boolean success = centerService.approveGymCenter(centerId);
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
     * Rejects a gym center application.
     *
     * @param centerId the center id
     * @return the response indicating rejection status
     */
    @PUT
    @Path("/{centerId}/reject")
    public Response rejectGymCenter(@PathParam("centerId") int centerId) {
        try {
            boolean success = centerService.rejectGymCenter(centerId);
            if (success) {
                return Response.ok(new ApiResponse<>(true, "Gym center rejected successfully", null)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiResponse<>(false, "Failed to reject gym center", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error rejecting gym center: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Retrieves all available slots at a gym center.
     *
     * @param centerId the center id
     * @return the response with list of available slots
     */
    @GET
    @Path("/{centerId}/slots")
    public Response viewAvailableSlots(@PathParam("centerId") int centerId) {
        try {
            List<Slot> slots = centerService.viewAvailableSlots(centerId);
            return Response.ok(new ApiResponse<>(true, "Available slots retrieved successfully", slots)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving available slots: " + e.getMessage(), null))
                    .build();
        }
    }
}
