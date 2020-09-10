package io.App.UserManagementService.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO extends UserLoginInfoWrapper implements Serializable {

	private static final long serialVersionUID = 1L;

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
	public UserDTO(int id, String userName, String firstName,
			String lastName, int roleId, String email, Date dateLogin) {
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roleId = roleId;
		this.dateLogin = dateLogin;
	}

	public UserDTO(int id, String userName, String firstName,
			String lastName, int roleId, String email) {
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roleId = roleId;
	}

	public int getId() {
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

	public int getRole() {
		return roleId;
	}

	public Date getDateLogin() {
		return dateLogin;
	}

	public void setDate(Date dateLogin) {
		this.dateLogin = dateLogin;
	}
}
