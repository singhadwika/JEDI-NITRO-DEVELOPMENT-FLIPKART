package com.flipfit.client;

import com.flipfit.bean.Booking;
import com.flipfit.bean.GymCenter;
import com.flipfit.bean.GymOwner;
import com.flipfit.bean.Slot;
import com.flipfit.business.GymOwnerService;
import com.flipfit.business.GymOwnerServiceImpl;
import com.flipfit.dto.ApiResponse;
import com.flipfit.dto.AddSlotRequest;
import com.flipfit.dto.UpdateCenterRequest;
import com.flipfit.dto.RegisterRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * The Class GymOwnerController.
 * REST Controller for gym owner operations in the FlipFit system.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
@Path("/api/owners")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GymOwnerController {

    /** The gym owner service. */
    private final GymOwnerService ownerService;

    /**
     * Instantiates a new gym owner controller.
     */
    public GymOwnerController() {
        this.ownerService = new GymOwnerServiceImpl();
    }

    /**
     * Registers a new gym owner.
     *
     * @param registerRequest the registration request
     * @return the response indicating registration status
     */
    @POST
    @Path("/register")
    public Response registerGymOwner(RegisterRequest registerRequest) {
        try {
            GymOwner gymOwner = new GymOwner();
            gymOwner.setName(registerRequest.getName());
            gymOwner.setEmail(registerRequest.getEmail());
            gymOwner.setPassword(registerRequest.getPassword());
            gymOwner.setRole("GYM_OWNER");
            
            boolean success = ownerService.registerGymOwner(gymOwner);
            if (success) {
                return Response.status(Response.Status.CREATED)
                        .entity(new ApiResponse<>(true, "Gym owner registered successfully", gymOwner))
                        .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiResponse<>(false, "Gym owner registration failed", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error registering gym owner: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Adds a gym center for a gym owner.
     *
     * @param ownerId the owner id
     * @param center the gym center to add
     * @return the response indicating addition status
     */
    @POST
    @Path("/{ownerId}/centers")
    public Response addGymCenter(@PathParam("ownerId") int ownerId, GymCenter center) {
        try {
            boolean success = ownerService.addGymCenter(ownerId, center);
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
     * Retrieves all gym centers owned by a specific owner.
     *
     * @param ownerId the owner id
     * @return the response with list of gym centers
     */
    @GET
    @Path("/{ownerId}/centers")
    public Response getGymCentersByOwner(@PathParam("ownerId") int ownerId) {
        try {
            List<GymCenter> centers = ownerService.getGymCentersByOwner(ownerId);
            return Response.ok(new ApiResponse<>(true, "Gym centers retrieved successfully", centers)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving gym centers: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Updates the details of a gym center.
     *
     * @param ownerId the owner id
     * @param centerId the center id
     * @param updateRequest the update request
     * @return the response indicating update status
     */
    @PUT
    @Path("/{ownerId}/centers/{centerId}")
    public Response updateCenterDetails(@PathParam("ownerId") int ownerId, 
                                         @PathParam("centerId") int centerId,
                                         UpdateCenterRequest updateRequest) {
        try {
            boolean success = ownerService.updateCenterDetails(centerId, updateRequest.getName(), updateRequest.getLocation());
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
     * Adds a new slot at a gym center.
     *
     * @param ownerId the owner id
     * @param centerId the center id
     * @param slotRequest the slot request
     * @return the response with created slot
     */
    @POST
    @Path("/{ownerId}/centers/{centerId}/slots")
    public Response addSlot(@PathParam("ownerId") int ownerId, 
                            @PathParam("centerId") int centerId,
                            AddSlotRequest slotRequest) {
        try {
            Slot slot = ownerService.addSlot(centerId, slotRequest.getStartTime(), 
                    slotRequest.getEndTime(), slotRequest.getTotalSeats());
            if (slot != null) {
                return Response.status(Response.Status.CREATED)
                        .entity(new ApiResponse<>(true, "Slot added successfully", slot))
                        .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiResponse<>(false, "Failed to add slot", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error adding slot: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Removes a slot from a gym center.
     *
     * @param ownerId the owner id
     * @param centerId the center id
     * @param slotId the slot id
     * @return the response indicating removal status
     */
    @DELETE
    @Path("/{ownerId}/centers/{centerId}/slots/{slotId}")
    public Response removeSlot(@PathParam("ownerId") int ownerId, 
                               @PathParam("centerId") int centerId,
                               @PathParam("slotId") int slotId) {
        try {
            boolean success = ownerService.removeSlot(slotId);
            if (success) {
                return Response.ok(new ApiResponse<>(true, "Slot removed successfully", null)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiResponse<>(false, "Failed to remove slot", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error removing slot: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Manages and retrieves all slots at a gym center.
     *
     * @param ownerId the owner id
     * @param centerId the center id
     * @return the response with list of slots
     */
    @GET
    @Path("/{ownerId}/centers/{centerId}/slots")
    public Response manageSlots(@PathParam("ownerId") int ownerId, 
                                @PathParam("centerId") int centerId) {
        try {
            List<Slot> slots = ownerService.manageSlots(centerId);
            return Response.ok(new ApiResponse<>(true, "Slots retrieved successfully", slots)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving slots: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Views all bookings for a gym owner's centers.
     *
     * @param ownerId the owner id
     * @return the response with list of bookings
     */
    @GET
    @Path("/{ownerId}/bookings")
    public Response viewBookings(@PathParam("ownerId") int ownerId) {
        try {
            List<Booking> bookings = ownerService.viewBookings(ownerId);
            return Response.ok(new ApiResponse<>(true, "Bookings retrieved successfully", bookings)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving bookings: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Views all bookings for a specific gym center.
     *
     * @param ownerId the owner id
     * @param centerId the center id
     * @return the response with list of bookings
     */
    @GET
    @Path("/{ownerId}/centers/{centerId}/bookings")
    public Response viewBookingsByCenter(@PathParam("ownerId") int ownerId, 
                                         @PathParam("centerId") int centerId) {
        try {
            List<Booking> bookings = ownerService.viewBookingsByCenter(centerId);
            return Response.ok(new ApiResponse<>(true, "Bookings retrieved successfully", bookings)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving bookings: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Requests approval for a gym center.
     *
     * @param ownerId the owner id
     * @param centerId the center id
     * @return the response indicating request status
     */
    @POST
    @Path("/{ownerId}/centers/{centerId}/request-approval")
    public Response requestApproval(@PathParam("ownerId") int ownerId, 
                                    @PathParam("centerId") int centerId) {
        try {
            boolean success = ownerService.requestApproval(centerId);
            if (success) {
                return Response.ok(new ApiResponse<>(true, "Approval request submitted successfully", null)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiResponse<>(false, "Failed to submit approval request", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error submitting approval request: " + e.getMessage(), null))
                    .build();
        }
    }
}
