package io.App.UserManagementService.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private String id;

	@JsonProperty("userName")
	private String userName;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("email")
	private String email;
	
	@JsonProperty("role")
	private String role;

	@JsonProperty("dateLogin")
	private Date dateLogin;

	public UserDTO() {
		// For REST only
	}

	/**
	 * 
	 * @param id - id of the user
	 * @param name - name of the user
	 */
	public UserDTO(String id, String userName, String firstName,
			String lastName, String role, String email, Date dateLogin) {
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.dateLogin = dateLogin;
	}
	
	public UserDTO(String id, String userName, String firstName,
			String lastName, String role, String email) {
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return userName;
	}

	public String getEmail() {
		return this.email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String getRole() {
		return role;
	}

	public Date getDateLogin() {
		return dateLogin;
	}
}
