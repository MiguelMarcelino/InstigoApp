package io.App.EventService.facade.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {

	@JsonProperty("id")
	private int id;

	@JsonProperty("userName")
	private String userName;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("email")
	private String email;

	@JsonProperty("roleId")
	private int roleId;

	public UserDTO(int id, String userName, String firstName, String lastName,
			String email, int roleId) {
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roleId = roleId;
		this.email = email;
	}

	public UserDTO() {
		// For REST only
	}

	public int getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getRoleID() {
		return roleId;
	}

	public String getEmail() {
		return email;
	}
}
