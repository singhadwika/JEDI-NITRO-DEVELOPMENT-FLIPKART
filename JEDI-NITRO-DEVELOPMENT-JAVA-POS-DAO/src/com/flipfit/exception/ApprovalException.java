package com.flipfit.exception;

/**
 * The Class ApprovalException.
 * Exception thrown when gym center approval operation fails.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class ApprovalException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new approval exception.
     */
    public ApprovalException() {
        super("Gym center approval failed");
    }

    /**
     * Instantiates a new approval exception with a custom message.
     *
     * @param message the error message
     */
    public ApprovalException(String message) {
        super(message);
    }

    /**
     * Instantiates a new approval exception with a custom message and cause.
     *
     * @param message the error message
     * @param cause the cause of the exception
     */
    public ApprovalException(String message, Throwable cause) {
        super(message, cause);
    }
}
