package io.App.UserRoleService.roleComponent;

public class Operation {

	private int opLevel;
	private String description;

	public Operation() {
		// For REST only
	}

	public Operation(int opLevel, String description) {
		this.opLevel = opLevel;
		this.description = description;
	}

	public int getOpLevel() {
		return opLevel;
	}

	public String getDescription() {
		return description;
	}
}
