package io.App.CommunityService.business;

public class User {

	private int id;
	private String userName;
	private String firstName;
	private String lastName;
	private Role role;
	private String email;

	public User() {
		// For REST only
	}

	public User(int id, String userName, String firstName, String lastName,
			String email, Role role) {
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
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

	public Role getRole() {
		return role;
	}

	public String getEmail() {
		return email;
	}

}
