package com.flipfit.dto;

/**
 * The Class UpdateCenterRequest.
 * DTO for updating gym center details.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class UpdateCenterRequest {

    /** The center name. */
    private String name;
    
    /** The center location. */
    private String location;

    /**
     * Instantiates a new update center request.
     */
    public UpdateCenterRequest() {}

    /**
     * Instantiates a new update center request with specified values.
     *
     * @param name the center name
     * @param location the center location
     */
    public UpdateCenterRequest(String name, String location) {
        this.name = name;
        this.location = location;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location.
     *
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }
}
