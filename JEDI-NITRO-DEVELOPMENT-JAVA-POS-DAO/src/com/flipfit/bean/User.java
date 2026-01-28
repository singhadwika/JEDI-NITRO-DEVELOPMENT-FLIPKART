package com.flipfit.bean;

/**
 * The Class User.
 * Represents a user in the FlipFit system.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class User {

	/** The user ID. */
	private int id;
    /** The user name. */
    private String name;
    /** The user email. */
    private String email;
    /** The user password. */
    private String password;
    /** The user role. */
    private String role;
    
    /**
     * Gets the user ID.
     *
     * @return the id
     */
	public int getId() {
		return id;
	}
	
	/**
     * Sets the user ID.
     *
     * @param id the id to set
     */
	public void setId(int id) {
		this.id = id;
	}
	
    /**
     * Gets the user name.
     *
     * @return the name
     */
	public String getName() {
		return name;
	}
	
	/**
     * Sets the user name.
     *
     * @param name the name to set
     */
	public void setName(String name) {
		this.name = name;
	}
	
    /**
     * Gets the user email.
     *
     * @return the email
     */
	public String getEmail() {
		return email;
	}
	
	/**
     * Sets the user email.
     *
     * @param email the email to set
     */
	public void setEmail(String email) {
		this.email = email;
	}
	
    /**
     * Gets the user password.
     *
     * @return the password
     */
	public String getPassword() {
		return password;
	}
	
	/**
     * Sets the user password.
     *
     * @param password the password to set
     */
	public void setPassword(String password) {
		this.password = password;
	}
	
    /**
     * Gets the user role.
     *
     * @return the role
     */
	public String getRole() {
		return role;
	}
	
	/**
     * Sets the user role.
     *
     * @param role the role to set
     */
	public void setRole(String role) {
		this.role = role;
	}
}
