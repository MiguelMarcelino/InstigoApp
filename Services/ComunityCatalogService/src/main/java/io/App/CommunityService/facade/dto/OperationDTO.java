package io.App.CommunityService.facade.dto;

public class OperationDTO {

	private int id;
	private String name;
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
