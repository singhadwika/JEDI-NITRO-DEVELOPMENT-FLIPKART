package com.flipfit.bean;

public class Admin extends User {

	public Admin() {}

    public Admin(int id, String name, String email, String password) {
    	this.setId(id);
    	this.setName(name);
    	this.setEmail(email);
    	this.setPassword(password);
    }
}
