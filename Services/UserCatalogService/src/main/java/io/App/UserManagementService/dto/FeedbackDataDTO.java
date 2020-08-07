package io.App.UserManagementService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedbackDataDTO {

	@JsonProperty("username")
	private String username;

	@JsonProperty("feedback")
	private String feedback;

	public FeedbackDataDTO() {
		// For REST only
	}

	public FeedbackDataDTO(String username, String feedback) {
		this.username = username;
		this.feedback = feedback;
	}

	public String getUserName() {
		return username;
	}

	public String getFeedback() {
		return feedback;
	}

}
