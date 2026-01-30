package com.flipfit.client;

import com.flipfit.bean.Slot;
import com.flipfit.business.SlotService;
import com.flipfit.business.SlotServiceImpl;
import com.flipfit.dto.ApiResponse;
import com.flipfit.dto.CreateSlotRequest;
import com.flipfit.dto.UpdateSlotRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * The Class SlotController.
 * REST Controller for slot operations in the FlipFit system.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
@Path("/api/slots")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SlotController {

    /** The slot service. */
    private final SlotService slotService;

    /**
     * Instantiates a new slot controller.
     */
    public SlotController() {
        this.slotService = new SlotServiceImpl();
    }

    /**
     * Creates a new slot at a gym center.
     *
     * @param slotRequest the slot creation request
     * @return the response with created slot
     */
    @POST
    public Response createSlot(CreateSlotRequest slotRequest) {
        try {
            Slot slot = slotService.createSlot(
                    slotRequest.getCenterId(),
                    slotRequest.getStartTime(),
                    slotRequest.getEndTime(),
                    slotRequest.getTotalSeats()
            );
            if (slot != null) {
                return Response.status(Response.Status.CREATED)
                        .entity(new ApiResponse<>(true, "Slot created successfully", slot))
                        .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiResponse<>(false, "Failed to create slot", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error creating slot: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Retrieves a slot by its ID.
     *
     * @param slotId the slot id
     * @return the response with slot details
     */
    @GET
    @Path("/{slotId}")
    public Response getSlotById(@PathParam("slotId") int slotId) {
        try {
            Slot slot = slotService.getSlotById(slotId);
            if (slot != null) {
                return Response.ok(new ApiResponse<>(true, "Slot found", slot)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new ApiResponse<>(false, "Slot not found", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving slot: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Retrieves all slots at a gym center.
     *
     * @param centerId the center id
     * @return the response with list of slots
     */
    @GET
    @Path("/center/{centerId}")
    public Response getSlotsByCenter(@PathParam("centerId") int centerId) {
        try {
            List<Slot> slots = slotService.getSlotsByCenter(centerId);
            return Response.ok(new ApiResponse<>(true, "Slots retrieved successfully", slots)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving slots: " + e.getMessage(), null))
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
    @Path("/center/{centerId}/available")
    public Response getAvailableSlotsByCenter(@PathParam("centerId") int centerId) {
        try {
            List<Slot> slots = slotService.getAvailableSlotsByCenter(centerId);
            return Response.ok(new ApiResponse<>(true, "Available slots retrieved successfully", slots)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving available slots: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Checks if a slot is available.
     *
     * @param slotId the slot id
     * @return the response with availability status
     */
    @GET
    @Path("/{slotId}/availability")
    public Response checkSlotAvailability(@PathParam("slotId") int slotId) {
        try {
            boolean available = slotService.isSlotAvailable(slotId);
            return Response.ok(new ApiResponse<>(true, available ? "Slot is available" : "Slot is not available", available)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error checking slot availability: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Updates a slot.
     *
     * @param slotId the slot id
     * @param updateRequest the update request
     * @return the response indicating update status
     */
    @PUT
    @Path("/{slotId}")
    public Response updateSlot(@PathParam("slotId") int slotId, UpdateSlotRequest updateRequest) {
        try {
            boolean success = slotService.updateSlot(slotId, updateRequest.getStartTime(), 
                    updateRequest.getEndTime(), updateRequest.getTotalSeats());
            if (success) {
                return Response.ok(new ApiResponse<>(true, "Slot updated successfully", null)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiResponse<>(false, "Failed to update slot", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error updating slot: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Deletes a slot.
     *
     * @param slotId the slot id
     * @return the response indicating deletion status
     */
    @DELETE
    @Path("/{slotId}")
    public Response deleteSlot(@PathParam("slotId") int slotId) {
        try {
            boolean success = slotService.deleteSlot(slotId);
            if (success) {
                return Response.ok(new ApiResponse<>(true, "Slot deleted successfully", null)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiResponse<>(false, "Failed to delete slot", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error deleting slot: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Locks a slot (makes it unavailable).
     *
     * @param slotId the slot id
     * @return the response indicating lock status
     */
    @PUT
    @Path("/{slotId}/lock")
    public Response lockSlot(@PathParam("slotId") int slotId) {
        try {
            boolean success = slotService.lockSlot(slotId);
            if (success) {
                return Response.ok(new ApiResponse<>(true, "Slot locked successfully", null)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiResponse<>(false, "Failed to lock slot", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error locking slot: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Unlocks a slot (makes it available).
     *
     * @param slotId the slot id
     * @return the response indicating unlock status
     */
    @PUT
    @Path("/{slotId}/unlock")
    public Response unlockSlot(@PathParam("slotId") int slotId) {
        try {
            boolean success = slotService.unlockSlot(slotId);
            if (success) {
                return Response.ok(new ApiResponse<>(true, "Slot unlocked successfully", null)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiResponse<>(false, "Failed to unlock slot", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error unlocking slot: " + e.getMessage(), null))
                    .build();
        }
    }
}
