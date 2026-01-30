package com.flipfit.dto;

import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * The Class CreateSlotRequest.
 * DTO for creating a new slot.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class CreateSlotRequest {

    /** The center ID. */
    private int centerId;
    
    /** The start time of the slot. */
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;
    
    /** The end time of the slot. */
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;
    
    /** The total seats available in the slot. */
    private int totalSeats;

    /**
     * Instantiates a new create slot request.
     */
    public CreateSlotRequest() {}

    /**
     * Instantiates a new create slot request with specified values.
     *
     * @param centerId the center id
     * @param startTime the start time
     * @param endTime the end time
     * @param totalSeats the total seats
     */
    public CreateSlotRequest(int centerId, LocalTime startTime, LocalTime endTime, int totalSeats) {
        this.centerId = centerId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalSeats = totalSeats;
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
     * Gets the start time.
     *
     * @return the start time
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time.
     *
     * @param startTime the start time to set
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets the end time.
     *
     * @return the end time
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time.
     *
     * @param endTime the end time to set
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets the total seats.
     *
     * @return the total seats
     */
    public int getTotalSeats() {
        return totalSeats;
    }

    /**
     * Sets the total seats.
     *
     * @param totalSeats the total seats to set
     */
    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }
}
