package com.webservice.MatchCraft.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class LoginUser {
	@NotEmpty(message = "Email is required")
	@Email(message="Please enter a valid email")
	private String emailString;
	
	@NotEmpty(message="Password is required")
	private String passwordString;
	
	public LoginUser() {}

	public String getEmailString() {
	    return emailString;
	}

	public void setEmailString(String emailString) {
	    this.emailString = emailString;
	}

	public String getPasswordString() {
	    return passwordString;
	}

	public void setPasswordString(String passwordString) {
	    this.passwordString = passwordString;
	}

		
}
