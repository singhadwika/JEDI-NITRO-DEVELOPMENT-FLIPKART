package com.flipfit.client;

import com.flipfit.bean.Booking;
import com.flipfit.business.BookingService;
import com.flipfit.business.BookingServiceImpl;
import com.flipfit.dto.ApiResponse;
import com.flipfit.dto.CreateBookingRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.List;

/**
 * The Class BookingController.
 * REST Controller for booking operations in the FlipFit system.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
@Path("/api/bookings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingController {

    /** The booking service. */
    private final BookingService bookingService;

    /**
     * Instantiates a new booking controller.
     */
    public BookingController() {
        this.bookingService = new BookingServiceImpl();
    }

    /**
     * Creates a new booking.
     *
     * @param bookingRequest the booking request
     * @return the response with created booking
     */
    @POST
    public Response createBooking(CreateBookingRequest bookingRequest) {
        try {
            Booking booking = bookingService.createBooking(
                    bookingRequest.getUserId(),
                    bookingRequest.getSlotId(),
                    bookingRequest.getCenterId(),
                    bookingRequest.getBookingDate()
            );
            if (booking != null) {
                return Response.status(Response.Status.CREATED)
                        .entity(new ApiResponse<>(true, "Booking created successfully", booking))
                        .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiResponse<>(false, "Failed to create booking", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error creating booking: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Retrieves a booking by its ID.
     *
     * @param bookingId the booking id
     * @return the response with booking details
     */
    @GET
    @Path("/{bookingId}")
    public Response getBookingById(@PathParam("bookingId") int bookingId) {
        try {
            Booking booking = bookingService.getBookingById(bookingId);
            if (booking != null) {
                return Response.ok(new ApiResponse<>(true, "Booking found", booking)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new ApiResponse<>(false, "Booking not found", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving booking: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Confirms an existing booking.
     *
     * @param bookingId the booking id
     * @return the response indicating confirmation status
     */
    @PUT
    @Path("/{bookingId}/confirm")
    public Response confirmBooking(@PathParam("bookingId") int bookingId) {
        try {
            boolean success = bookingService.confirmBooking(bookingId);
            if (success) {
                return Response.ok(new ApiResponse<>(true, "Booking confirmed successfully", null)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiResponse<>(false, "Failed to confirm booking", null))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error confirming booking: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Cancels an existing booking.
     *
     * @param bookingId the booking id
     * @return the response indicating cancellation status
     */
    @DELETE
    @Path("/{bookingId}")
    public Response cancelBooking(@PathParam("bookingId") int bookingId) {
        try {
            boolean success = bookingService.cancelBooking(bookingId);
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
     * Retrieves all bookings made by a specific user.
     *
     * @param userId the user id
     * @return the response with list of bookings
     */
    @GET
    @Path("/user/{userId}")
    public Response getBookingsByUser(@PathParam("userId") int userId) {
        try {
            List<Booking> bookings = bookingService.getBookingsByUser(userId);
            return Response.ok(new ApiResponse<>(true, "Bookings retrieved successfully", bookings)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving bookings: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Retrieves all bookings for a specific slot.
     *
     * @param slotId the slot id
     * @return the response with list of bookings
     */
    @GET
    @Path("/slot/{slotId}")
    public Response getBookingsBySlot(@PathParam("slotId") int slotId) {
        try {
            List<Booking> bookings = bookingService.getBookingsBySlot(slotId);
            return Response.ok(new ApiResponse<>(true, "Bookings retrieved successfully", bookings)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving bookings: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Retrieves all bookings for a specific gym center.
     *
     * @param centerId the center id
     * @return the response with list of bookings
     */
    @GET
    @Path("/center/{centerId}")
    public Response getBookingsByCenter(@PathParam("centerId") int centerId) {
        try {
            List<Booking> bookings = bookingService.getBookingsByCenter(centerId);
            return Response.ok(new ApiResponse<>(true, "Bookings retrieved successfully", bookings)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving bookings: " + e.getMessage(), null))
                    .build();
        }
    }

    /**
     * Retrieves all bookings for a specific date.
     *
     * @param date the booking date
     * @return the response with list of bookings
     */
    @GET
    @Path("/date/{date}")
    public Response getBookingsByDate(@PathParam("date") String date) {
        try {
            LocalDate bookingDate = LocalDate.parse(date);
            List<Booking> bookings = bookingService.getBookingsByDate(bookingDate);
            return Response.ok(new ApiResponse<>(true, "Bookings retrieved successfully", bookings)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Error retrieving bookings: " + e.getMessage(), null))
                    .build();
        }
    }
}
