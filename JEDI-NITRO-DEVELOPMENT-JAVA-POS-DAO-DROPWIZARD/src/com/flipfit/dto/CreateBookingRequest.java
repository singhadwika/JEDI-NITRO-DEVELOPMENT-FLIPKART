package com.flipfit.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * The Class CreateBookingRequest.
 * DTO for creating a new booking.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class CreateBookingRequest {

    /** The user ID. */
    private int userId;
    
    /** The slot ID. */
    private int slotId;
    
    /** The center ID. */
    private int centerId;
    
    /** The booking date. */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookingDate;

    /**
     * Instantiates a new create booking request.
     */
    public CreateBookingRequest() {}

    /**
     * Instantiates a new create booking request with specified values.
     *
     * @param userId the user id
     * @param slotId the slot id
     * @param centerId the center id
     * @param bookingDate the booking date
     */
    public CreateBookingRequest(int userId, int slotId, int centerId, LocalDate bookingDate) {
        this.userId = userId;
        this.slotId = slotId;
        this.centerId = centerId;
        this.bookingDate = bookingDate;
    }

    /**
     * Gets the user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user id.
     *
     * @param userId the user id to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the slot id.
     *
     * @return the slot id
     */
    public int getSlotId() {
        return slotId;
    }

    /**
     * Sets the slot id.
     *
     * @param slotId the slot id to set
     */
    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    /**
     * Gets the center id.
     *
     * @return the center id
     */
    public int getCenterId() {
        return centerId;
    }

    /**
     * Sets the center id.
     *
     * @param centerId the center id to set
     */
    public void setCenterId(int centerId) {
        this.centerId = centerId;
    }

    /**
     * Gets the booking date.
     *
     * @return the booking date
     */
    public LocalDate getBookingDate() {
        return bookingDate;
    }

    /**
     * Sets the booking date.
     *
     * @param bookingDate the booking date to set
     */
    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
}
