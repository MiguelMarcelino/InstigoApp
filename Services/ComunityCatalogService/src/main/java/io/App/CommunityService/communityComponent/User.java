package io.App.CommunityService.communityComponent;

public class User {

	private int id;
	private String userName;
	private String firstName;
	private String lastName;
	private int roleID;
	private String email;

	public User(int id, String userName, String firstName,
			String lastName, int roleID, String email) {
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roleID = roleID;
		this.email = email;
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
		return roleID;
	}

	public String getEmail() {
		return email;
	}

}
