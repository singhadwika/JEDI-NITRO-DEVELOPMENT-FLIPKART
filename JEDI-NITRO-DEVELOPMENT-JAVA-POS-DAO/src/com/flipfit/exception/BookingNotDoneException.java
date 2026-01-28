package com.flipfit.exception;

/**
 * The Class BookingNotDoneException.
 * Exception thrown when booking operation fails.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class BookingNotDoneException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new booking not done exception.
     */
    public BookingNotDoneException() {
        super("Booking operation failed");
    }

    /**
     * Instantiates a new booking not done exception with a custom message.
     *
     * @param message the error message
     */
    public BookingNotDoneException(String message) {
        super(message);
    }

    /**
     * Instantiates a new booking not done exception with a custom message and cause.
     *
     * @param message the error message
     * @param cause the cause of the exception
     */
    public BookingNotDoneException(String message, Throwable cause) {
        super(message, cause);
    }
}
