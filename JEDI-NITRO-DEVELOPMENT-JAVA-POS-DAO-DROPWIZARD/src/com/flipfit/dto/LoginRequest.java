package com.flipfit.dto;

/**
 * The Class LoginRequest.
 * DTO for user login requests.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class LoginRequest {

    /** The user email. */
    private String email;
    
    /** The user password. */
    private String password;

    /**
     * Instantiates a new login request.
     */
    public LoginRequest() {}

    /**
     * Instantiates a new login request with specified values.
     *
     * @param email the user email
     * @param password the user password
     */
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
