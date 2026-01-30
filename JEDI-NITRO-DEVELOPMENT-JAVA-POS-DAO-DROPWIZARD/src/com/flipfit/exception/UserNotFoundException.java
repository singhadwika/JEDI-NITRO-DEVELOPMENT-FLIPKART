package com.flipfit.exception;

/**
 * The Class UserNotFoundException.
 * Exception thrown when a user is not found in the system.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class UserNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new user not found exception.
     */
    public UserNotFoundException() {
        super("User not found in the system");
    }

    /**
     * Instantiates a new user not found exception with a custom message.
     *
     * @param message the error message
     */
    public UserNotFoundException(String message) {
        super(message);
    }

    /**
     * Instantiates a new user not found exception with a custom message and cause.
     *
     * @param message the error message
     * @param cause the cause of the exception
     */
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
