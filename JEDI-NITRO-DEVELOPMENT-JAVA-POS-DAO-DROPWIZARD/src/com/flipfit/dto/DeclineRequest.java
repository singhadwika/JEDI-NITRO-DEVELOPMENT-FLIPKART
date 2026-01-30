package com.flipfit.dto;

/**
 * The Class DeclineRequest.
 * DTO for gym center decline requests.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class DeclineRequest {

    /** The reason for declining. */
    private String reason;

    /**
     * Instantiates a new decline request.
     */
    public DeclineRequest() {}

    /**
     * Instantiates a new decline request with specified reason.
     *
     * @param reason the reason for declining
     */
    public DeclineRequest(String reason) {
        this.reason = reason;
    }

    /**
     * Gets the reason.
     *
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the reason.
     *
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
}
