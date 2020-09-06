package io.App.UserManagementService.userComponent;

public class Operation {

	private int id;
	private String name;

	public Operation() {
		// For REST only
	}

	public Operation(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
