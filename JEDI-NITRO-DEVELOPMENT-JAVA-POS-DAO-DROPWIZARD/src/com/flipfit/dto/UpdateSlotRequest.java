package com.flipfit.dto;

import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * The Class UpdateSlotRequest.
 * DTO for updating slot details.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class UpdateSlotRequest {

    /** The start time of the slot. */
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;
    
    /** The end time of the slot. */
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;
    
    /** The total seats available in the slot. */
    private int totalSeats;

    /**
     * Instantiates a new update slot request.
     */
    public UpdateSlotRequest() {}

    /**
     * Instantiates a new update slot request with specified values.
     *
     * @param startTime the start time
     * @param endTime the end time
     * @param totalSeats the total seats
     */
    public UpdateSlotRequest(LocalTime startTime, LocalTime endTime, int totalSeats) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalSeats = totalSeats;
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
