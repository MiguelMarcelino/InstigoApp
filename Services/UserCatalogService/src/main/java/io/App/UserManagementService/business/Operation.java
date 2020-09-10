package io.App.UserManagementService.business;

public class Operation {

	private int id;
	private String name;
	private int roleID;

	public Operation() {
		// For REST only
	}

	public Operation(int id, String name, int roleID) {
		this.id = id;
		this.name = name;
		this.roleID = roleID;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getRoleID() {
		return roleID;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
