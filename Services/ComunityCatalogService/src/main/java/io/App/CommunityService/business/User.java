package io.App.CommunityService.business;

public class User {

	private int id;
	private String userName;
	private String firstName;
	private String lastName;
	private int roleId;
	private String email;

	public User() {
		// For REST only
	}

	public User(int id, String userName, String firstName, String lastName,
			String email, int roleId) {
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

	public String getUserName() {
		return userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getRoleId() {
		return roleId;
	}

	public String getEmail() {
		return email;
	}

}
