package io.App.CommunityService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommunitySubscriptionDTO {

	@JsonProperty("uID")
	private String uID;
	@JsonProperty("cID")
	private String cID;

	public CommunitySubscriptionDTO() {
		// For REST only
	}

	public CommunitySubscriptionDTO(String uID, String cID) {
		this.uID = uID;
		this.cID = cID;
	}

	public String getuID() {
		return uID;
	}

	public String getcID() {
		return cID;
	}

}
