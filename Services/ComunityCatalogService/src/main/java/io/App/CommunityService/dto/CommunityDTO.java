package io.App.CommunityService.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommunityDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("cID")
	private int cID;
	@JsonProperty("name")
	private String name;
	@JsonProperty("description")
	private String description;
	@JsonProperty("ownerUserName")
	private String ownerUserName;

	public CommunityDTO() {
		// For REST only
	}

	public CommunityDTO(int id, String name, String description, String ownerUserName) {
		this.cID = id;
		this.name = name;
		this.description = description;
		this.ownerUserName = ownerUserName;
	}

	public int getcID() {
		return cID;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	public String getOwnerUserName() {
		return ownerUserName;
	}

}
