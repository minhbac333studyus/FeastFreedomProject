package com.minhle.controller;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.minhle.constrain.FieldMatch;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
    @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
})
public class KitchenUserRegistrationDto { 
	@NotEmpty
	private String name;
 
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String confirmPassword;
	
	@Email
	@NotEmpty
	private String email;
	
	@Email
	@NotEmpty
	private String confirmEmail; 
	public String getPassword() {
	    return password;
	}
	
	public void setPassword(String password) {
	    this.password = password;
	}
	
	public String getConfirmPassword() {
	    return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
	    this.confirmPassword = confirmPassword;
	}
	
	public String getEmail() {
	    return email;
	}
	
	public void setEmail(String email) {
	    this.email = email;
	}
	
	public String getConfirmEmail() {
	    return confirmEmail;
	}
	
	public void setConfirmEmail(String confirmEmail) {
	    this.confirmEmail = confirmEmail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
 
}