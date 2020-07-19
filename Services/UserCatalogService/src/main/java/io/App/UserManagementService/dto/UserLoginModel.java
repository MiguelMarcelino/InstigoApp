package io.App.UserManagementService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserLoginModel {
	@JsonProperty("username")
	private String uName;

	@JsonProperty("password")
	private String password;

	public UserLoginModel(String uName, String password) {
		this.uName = uName;
		this.password = password;
	}

	public String getuName() {
		return uName;
	}

	public String getPassword() {
		return password;
	}
}
