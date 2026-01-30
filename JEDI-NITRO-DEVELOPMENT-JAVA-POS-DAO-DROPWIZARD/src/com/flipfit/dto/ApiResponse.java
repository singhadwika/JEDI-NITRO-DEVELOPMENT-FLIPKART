package com.flipfit.dto;

/**
 * The Class ApiResponse.
 * Generic API response wrapper for REST endpoints.
 *
 * @param <T> the type of data in the response
 * @author FlipFit Development Team
 * @version 1.0
 */
public class ApiResponse<T> {

    /** Indicates if the request was successful. */
    private boolean success;
    
    /** The response message. */
    private String message;
    
    /** The response data. */
    private T data;

    /**
     * Instantiates a new api response.
     */
    public ApiResponse() {}

    /**
     * Instantiates a new api response with specified values.
     *
     * @param success the success status
     * @param message the response message
     * @param data the response data
     */
    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    /**
     * Checks if the request was successful.
     *
     * @return true if successful, false otherwise
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets the success status.
     *
     * @param success the success status to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Gets the response message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the response message.
     *
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the response data.
     *
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * Sets the response data.
     *
     * @param data the data to set
     */
    public void setData(T data) {
        this.data = data;
    }
}
