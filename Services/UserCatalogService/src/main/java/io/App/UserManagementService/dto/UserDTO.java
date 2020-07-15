package io.App.UserManagementService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {

	@JsonProperty("id")
	private int id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("email")
	private String email;
	@JsonProperty("password")
	private String password;

	public UserDTO() {
		// For REST only
	}

	/**
	 * 
	 * @param id
	 *            - id of the user
	 * @param name
	 *            - name of the user
	 */
	public UserDTO(int id, String name, String email, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return password;
	}
}
