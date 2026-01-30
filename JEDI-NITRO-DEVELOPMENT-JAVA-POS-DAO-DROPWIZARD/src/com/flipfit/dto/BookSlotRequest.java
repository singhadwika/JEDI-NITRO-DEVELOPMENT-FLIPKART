package com.flipfit.dto;

/**
 * The Class BookSlotRequest.
 * DTO for slot booking requests.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class BookSlotRequest {

    /** The slot ID to book. */
    private int slotId;
    
    /** The center ID. */
    private int centerId;

    /**
     * Instantiates a new book slot request.
     */
    public BookSlotRequest() {}

    /**
     * Instantiates a new book slot request with specified values.
     *
     * @param slotId the slot id
     * @param centerId the center id
     */
    public BookSlotRequest(int slotId, int centerId) {
        this.slotId = slotId;
        this.centerId = centerId;
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
}
