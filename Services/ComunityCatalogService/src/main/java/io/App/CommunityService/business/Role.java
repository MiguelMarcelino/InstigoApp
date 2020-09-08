package io.App.CommunityService.business;

import java.util.List;

public class Role {

	private int id;
	private String name;
	private List<Operation> operations;

	public Role() {
		// For REST only
	}

	public Role(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Role(int id, String name, List<Operation> operations) {
		this.id = id;
		this.name = name;
		this.operations = operations;
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

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
}
