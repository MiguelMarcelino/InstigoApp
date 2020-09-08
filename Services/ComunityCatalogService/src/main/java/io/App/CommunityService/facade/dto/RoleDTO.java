package io.App.CommunityService.facade.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleDTO {

	@JsonProperty("id")
	private int id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("operations")
	private List<OperationDTO> operations;

	public RoleDTO() {
		// For REST only
	}

	public RoleDTO(int id, String name, List<OperationDTO> operations) {
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

	public List<OperationDTO> getOperations() {
		return operations;
	}

}
