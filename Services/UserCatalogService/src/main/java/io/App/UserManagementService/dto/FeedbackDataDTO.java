package io.App.UserManagementService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedbackDataDTO {

	@JsonProperty("username")
	private String username;

	@JsonProperty("feedback")
	private String feedback;
	
	@JsonProperty("datePublished")
	private String datePublished;

	public FeedbackDataDTO() {
		// For REST only
	}

	public FeedbackDataDTO(String username, String datePublished, String feedback) {
		this.username = username;
		this.datePublished = datePublished;
		this.feedback = feedback;
	}

	public String getUserName() {
		return username;
	}
	
	public String getDatePublished() {
		return datePublished;
	}

	public String getFeedback() {
		return feedback;
	}

}
