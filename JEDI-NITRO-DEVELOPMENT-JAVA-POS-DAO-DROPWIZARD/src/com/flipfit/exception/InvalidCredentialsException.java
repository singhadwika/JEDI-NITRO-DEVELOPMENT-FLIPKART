package com.flipfit.exception;

/**
 * The Class InvalidCredentialsException.
 * Exception thrown when user credentials are invalid.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class InvalidCredentialsException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new invalid credentials exception.
     */
    public InvalidCredentialsException() {
        super("Invalid credentials provided");
    }

    /**
     * Instantiates a new invalid credentials exception with a custom message.
     *
     * @param message the error message
     */
    public InvalidCredentialsException(String message) {
        super(message);
    }

    /**
     * Instantiates a new invalid credentials exception with a custom message and cause.
     *
     * @param message the error message
     * @param cause the cause of the exception
     */
    public InvalidCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }
}
