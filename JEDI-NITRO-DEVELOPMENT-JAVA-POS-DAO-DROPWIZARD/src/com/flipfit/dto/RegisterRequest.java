package com.flipfit.dto;

/**
 * The Class RegisterRequest.
 * DTO for user registration requests.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class RegisterRequest {

    /** The user name. */
    private String name;
    
    /** The user email. */
    private String email;
    
    /** The user password. */
    private String password;
    
    /** The user role. */
    private String role;

    /**
     * Instantiates a new register request.
     */
    public RegisterRequest() {}

    /**
     * Instantiates a new register request with specified values.
     *
     * @param name the user name
     * @param email the user email
     * @param password the user password
     * @param role the user role
     */
    public RegisterRequest(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
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

    /**
     * Gets the role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role.
     *
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }
}
