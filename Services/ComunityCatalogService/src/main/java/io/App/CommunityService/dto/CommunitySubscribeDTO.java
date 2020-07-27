package io.App.CommunityService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommunitySubscribeDTO {

	@JsonProperty("uID")
	private String uID;
	@JsonProperty("cID")
	private String cID;

	public CommunitySubscribeDTO() {
		// For REST only
	}

	public CommunitySubscribeDTO(String uID, String cID) {
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
