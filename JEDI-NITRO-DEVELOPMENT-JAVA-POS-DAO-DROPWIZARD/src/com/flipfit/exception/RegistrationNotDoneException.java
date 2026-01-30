package com.flipfit.exception;

/**
 * The Class RegistrationNotDoneException.
 * Exception thrown when user registration fails.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class RegistrationNotDoneException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new registration not done exception.
     */
    public RegistrationNotDoneException() {
        super("User registration failed");
    }

    /**
     * Instantiates a new registration not done exception with a custom message.
     *
     * @param message the error message
     */
    public RegistrationNotDoneException(String message) {
        super(message);
    }

    /**
     * Instantiates a new registration not done exception with a custom message and cause.
     *
     * @param message the error message
     * @param cause the cause of the exception
     */
    public RegistrationNotDoneException(String message, Throwable cause) {
        super(message, cause);
    }
}
