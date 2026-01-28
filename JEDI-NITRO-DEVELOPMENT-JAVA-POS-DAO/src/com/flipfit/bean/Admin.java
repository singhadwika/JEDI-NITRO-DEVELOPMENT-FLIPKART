package com.flipfit.bean;

/**
 * The Class Admin.
 * Represents an administrator user in the FlipFit system.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class Admin extends User {

	/**
     * Instantiates a new admin with default values.
     */
	public Admin() {}

    /**
     * Instantiates a new admin with specified details.
     *
     * @param id the admin id
     * @param name the admin name
     * @param email the admin email
     * @param password the admin password
     */
    public Admin(int id, String name, String email, String password) {
    	this.setId(id);
    	this.setName(name);
    	this.setEmail(email);
    	this.setPassword(password);
    }
}
