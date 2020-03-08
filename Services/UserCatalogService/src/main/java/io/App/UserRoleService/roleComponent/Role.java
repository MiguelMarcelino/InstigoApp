package io.App.UserRoleService.roleComponent;

import java.util.ArrayList;

public class Role {

	private int id;
	private String name;
	private ArrayList<Operation> allowedOperations;

	public Role(int id, String name, ArrayList<Operation> operations) {
		this.id = id;
		this.name = name;
		this.allowedOperations = operations;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Operation> getAuthLevel() {
		return allowedOperations;
	}
}
