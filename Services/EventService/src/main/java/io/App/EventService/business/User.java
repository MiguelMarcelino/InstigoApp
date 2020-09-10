package io.App.EventService.business;

public class User {

	private int id;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private int roleId;

	public User(int id, String userName, String firstName, String lastName,
			String email, int roleId) {
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roleId = roleId;
	}

	public User() {
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
