package io.App.CommunityService.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.App.CommunityService.communityComponent.User;

public class CommunityDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("description")
	private String description;
	@JsonProperty("communityOwner")
	private User communityOwner;
	@JsonProperty("currentUser")
	private User currentUser;
	@JsonProperty("isSubscribed")
	private boolean isSubscribed;

	public CommunityDTO() {
		// For REST only
	}

	/**
	 * This is a dto object for transporting the necessary fields from a
	 * Community object
	 * 
	 * @param id
	 * @param name
	 * @param description
	 * @param user
	 */
	public CommunityDTO(String id, String name, String description,
			User communityOwner) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.communityOwner = communityOwner;
	}

	/**
	 * This is a dto object for receiving the necessary fields from a
	 * Community object for creating and deleting operations
	 * 
	 * @param id
	 * @param name
	 * @param description
	 * @param user
	 */
	public CommunityDTO(String id, String name, String description,
			User communityOwner, User currentUser) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.communityOwner = communityOwner;
		this.currentUser = currentUser;
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

	public User getCommunityOwner() {
		return communityOwner;
	}

	public User getCurrentUser() {
		return currentUser;
	}

}
