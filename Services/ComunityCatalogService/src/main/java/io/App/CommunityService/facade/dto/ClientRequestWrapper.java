package io.App.CommunityService.facade.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientRequestWrapper {

	@JsonProperty("currentUser")
	private UserDTO currentUser;

	public ClientRequestWrapper() {
		// For REST only
	}
	
	public UserDTO getCurrentUser() {
		return currentUser;
	}
	
}
