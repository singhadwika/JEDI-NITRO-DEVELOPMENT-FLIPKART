package com.flipfit.client;

import com.flipfit.bean.Booking;
import com.flipfit.bean.GymCenter;
import com.flipfit.bean.GymCustomer;
import com.flipfit.bean.Slot;
import com.flipfit.business.GymCustomerService;
import com.flipfit.business.GymCustomerServiceImpl;
import com.flipfit.dto.ApiResponse;
import com.flipfit.dto.BookSlotRequest;
import com.flipfit.dto.RegisterRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * The Class GymCustomerController.
 * REST Controller for gym customer operations in the FlipFit system.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
@Path("/api/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GymCustomerController {

    /** The gym customer service. */
    private final GymCustomerService customerService;

    /**
     * Instantiates a new gym customer controller.
     */
    public GymCustomerController() {
        this.customerService = new GymCustomerServiceImpl();
    }

    /**
     * Registers a new gym customer.
     *
     * @param registerRequest the registration request
     * @return the response indicating registration status
     */
    @POST
    @Path("/register")
    public Response registerCustomer(RegisterRequest registerRequest) {
        try {
            GymCustomer customer = new GymCustomer();
            customer.setName(registerRequest.getName());
            customer.setEmail(registerRequest.getEmail());
            customer.setPassword(registerRequest.getPassword());
            customer.setRole("GYM_CUSTOMER");
            
            boolean success = customerService.registerCustomer(customer);
            if (success) {
                return Response.status(Response.Status.CREATED)
                        .entity(new ApiResponse<>(true, "Customer registered successfully", customer))
                        .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiResponse<>(false, "Customer registration failed", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error registering customer: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Retrieves customer details by customer ID.
     *
     * @param customerId the customer id
     * @return the response with customer details
     */
    @GET
    @Path("/{customerId}")
    public Response getCustomerById(@PathParam("customerId") int customerId) {
        try {
            GymCustomer customer = customerService.getCustomerById(customerId);
            if (customer != null) {
                return Response.ok(new ApiResponse<>(true, "Customer found", customer)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new ApiResponse<>(false, "Customer not found", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving customer: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Views all available slots.
     *
     * @return the response with list of all slots
     */
    @GET
    @Path("/slots")
    public Response viewAllSlots() {
        try {
            List<Slot> slots = customerService.viewSlots();
            return Response.ok(new ApiResponse<>(true, "Slots retrieved successfully", slots)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving slots: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Views all slots available at a specific gym center.
     *
     * @param centerId the center id
     * @return the response with list of slots at the center
     */
    @GET
    @Path("/slots/center/{centerId}")
    public Response viewSlotsByCenter(@PathParam("centerId") int centerId) {
        try {
            List<Slot> slots = customerService.viewSlotsByCenter(centerId);
            return Response.ok(new ApiResponse<>(true, "Slots retrieved successfully", slots)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving slots: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Views all gym centers.
     *
     * @return the response with list of all gym centers
     */
    @GET
    @Path("/centers")
    public Response viewCenters() {
        try {
            List<GymCenter> centers = customerService.viewCenters();
            return Response.ok(new ApiResponse<>(true, "Gym centers retrieved successfully", centers)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving gym centers: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Views all approved gym centers.
     *
     * @return the response with list of approved gym centers
     */
    @GET
    @Path("/centers/approved")
    public Response viewApprovedCenters() {
        try {
            List<GymCenter> centers = customerService.viewApprovedCenters();
            return Response.ok(new ApiResponse<>(true, "Approved gym centers retrieved successfully", centers)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving approved gym centers: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Books a slot for a customer.
     *
     * @param customerId the customer id
     * @param bookRequest the booking request
     * @return the response with booking details
     */
    @POST
    @Path("/{customerId}/bookings")
    public Response bookSlot(@PathParam("customerId") int customerId, BookSlotRequest bookRequest) {
        try {
            Booking booking = customerService.bookSlot(customerId, bookRequest.getSlotId(), bookRequest.getCenterId());
            if (booking != null) {
                return Response.status(Response.Status.CREATED)
                        .entity(new ApiResponse<>(true, "Slot booked successfully", booking))
                        .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiResponse<>(false, "Failed to book slot", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error booking slot: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Cancels a booked slot.
     *
     * @param customerId the customer id
     * @param bookingId the booking id
     * @return the response indicating cancellation status
     */
    @DELETE
    @Path("/{customerId}/bookings/{bookingId}")
    public Response cancelSlot(@PathParam("customerId") int customerId, @PathParam("bookingId") int bookingId) {
        try {
            boolean success = customerService.cancelSlot(bookingId);
            if (success) {
                return Response.ok(new ApiResponse<>(true, "Booking cancelled successfully", null)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiResponse<>(false, "Failed to cancel booking", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error cancelling booking: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Views the workout plan of a customer.
     *
     * @param customerId the customer id
     * @return the response with customer's workout plan
     */
    @GET
    @Path("/{customerId}/workout-plan")
    public Response viewWorkoutPlan(@PathParam("customerId") int customerId) {
        try {
            List<Booking> workoutPlan = customerService.viewWorkoutPlan(customerId);
            return Response.ok(new ApiResponse<>(true, "Workout plan retrieved successfully", workoutPlan)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving workout plan: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Retrieves all bookings of a customer.
     *
     * @param customerId the customer id
     * @return the response with list of customer bookings
     */
    @GET
    @Path("/{customerId}/bookings")
    public Response getCustomerBookings(@PathParam("customerId") int customerId) {
        try {
            List<Booking> bookings = customerService.getCustomerBookings(customerId);
            return Response.ok(new ApiResponse<>(true, "Bookings retrieved successfully", bookings)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving bookings: " + e.getMessage(), null))
                    .build();
        }
    }
}
