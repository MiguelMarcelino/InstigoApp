package io.App.UserManagementService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OperationDTO {

	@JsonProperty("id")
	private int id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("roleId")
	private int roleID;

	public OperationDTO() {
		// For REST only
	}

	public OperationDTO(int id, String name, int roleID) {
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

}
