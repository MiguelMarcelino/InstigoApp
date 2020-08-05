package io.App.RoleCatalogService.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("communityId")
	private String communityID;
	@JsonProperty("authLevel")
	private String authLevel;

	public RoleDTO() {
		// For REST only
	}

	public RoleDTO(String id, String name, String communityId,
			String authLevel) {
		this.id = id;
		this.name = name;
		this.communityID = communityId;
		this.authLevel = authLevel;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCommunityID() {
		return communityID;
	}

	public String getAuthLevel() {
		return authLevel;
	}

}
