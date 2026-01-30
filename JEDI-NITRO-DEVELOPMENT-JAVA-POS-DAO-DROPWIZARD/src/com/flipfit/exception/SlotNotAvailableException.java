package com.flipfit.exception;

/**
 * The Class SlotNotAvailableException.
 * Exception thrown when a slot is not available for booking.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class SlotNotAvailableException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new slot not available exception.
     */
    public SlotNotAvailableException() {
        super("Slot is not available");
    }

    /**
     * Instantiates a new slot not available exception with a custom message.
     *
     * @param message the error message
     */
    public SlotNotAvailableException(String message) {
        super(message);
    }

    /**
     * Instantiates a new slot not available exception with a custom message and cause.
     *
     * @param message the error message
     * @param cause the cause of the exception
     */
    public SlotNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
