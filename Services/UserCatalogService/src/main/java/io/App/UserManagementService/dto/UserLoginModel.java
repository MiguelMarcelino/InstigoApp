package io.App.UserManagementService.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserLoginModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("username")
	private String username;

	@JsonProperty("password")
	private String password;

	public UserLoginModel(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public UserLoginModel() {
		// For REST only
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
