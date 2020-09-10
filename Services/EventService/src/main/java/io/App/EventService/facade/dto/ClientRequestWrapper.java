package io.App.EventService.facade.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientRequestWrapper {

	@JsonProperty("currentUser")
	private int currentUserId;

	public ClientRequestWrapper() {
		// For REST only
	}

	public int getCurrentUserId() {
		return currentUserId;
	}

}
