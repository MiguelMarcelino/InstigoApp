package io.App.CommunityService.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommunityDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("description")
	private String description;
	@JsonProperty("ownerUserName")
	private String ownerUserName;
	@JsonProperty("isSubscribed")
	private boolean isSubscribed;

	public CommunityDTO() {
		// For REST only
	}

	public CommunityDTO(String id, String name, String description, String ownerUserName) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.ownerUserName = ownerUserName;
	}

	public String getcID() {
		return id;
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
