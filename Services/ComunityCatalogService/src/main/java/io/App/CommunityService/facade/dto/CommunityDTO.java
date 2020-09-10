package io.App.CommunityService.facade.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommunityDTO extends ClientRequestWrapper implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private int id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("description")
	private String description;
	@JsonProperty("communityOwner")
	private int communityOwnerId;
	@JsonProperty("isSubscribed")
	private boolean isSubscribed;

	public CommunityDTO() {
		// For REST only
	}

	/**
	 * This is a dto object for transporting the necessary fields from a
	 * Community object and for receiving the necessary fields from a Community
	 * object for creating and deleting operations
	 * 
	 * @param id
	 * @param name
	 * @param description
	 * @param user
	 */
	public CommunityDTO(int id, String name, String description,
			int communityOwner) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.communityOwnerId = communityOwner;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getCommunityOwnerId() {
		return communityOwnerId;
	}
}
