package com.flipfit.exception;

/**
 * The Class GymCenterNotFoundException.
 * Exception thrown when a gym center is not found.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class GymCenterNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new gym center not found exception.
     */
    public GymCenterNotFoundException() {
        super("Gym center not found");
    }

    /**
     * Instantiates a new gym center not found exception with a custom message.
     *
     * @param message the error message
     */
    public GymCenterNotFoundException(String message) {
        super(message);
    }

    /**
     * Instantiates a new gym center not found exception with a custom message and cause.
     *
     * @param message the error message
     * @param cause the cause of the exception
     */
    public GymCenterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
