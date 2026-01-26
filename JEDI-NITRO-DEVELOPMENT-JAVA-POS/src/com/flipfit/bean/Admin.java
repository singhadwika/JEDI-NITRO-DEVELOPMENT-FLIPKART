package com.flipfit.bean;

import java.util.List;

public class Admin extends User {
	
    public Admin(int id, String name, String email, String password) {
    	this.setId(id);
    	this.setName(name);
    	this.setEmail(email);
    	this.setPassword(password);
    }
}
