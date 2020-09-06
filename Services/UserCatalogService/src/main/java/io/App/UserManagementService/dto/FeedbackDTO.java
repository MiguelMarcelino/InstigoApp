package io.App.UserManagementService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedbackDTO {

	@JsonProperty("id")
	private int id;

	@JsonProperty("username")
	private String username;

	@JsonProperty("datePublished")
	private String datePublished;

	@JsonProperty("feedback")
	private String feedback;

	public FeedbackDTO() {
		// For REST only
	}

	public FeedbackDTO(String username, String datePublished, String feedback) {
		this.username = username;
		this.datePublished = datePublished;
		this.feedback = feedback;
	}

	public FeedbackDTO(int id, String username, String datePublished,
			String feedback) {
		this.id = id;
		this.username = username;
		this.datePublished = datePublished;
		this.feedback = feedback;
	}

	public int getId() {
		return id;
	}

	public String getUserName() {
		return username;
	}

	public String getDatePublished() {
		return datePublished;
	}

	public String getFeedbackContent() {
		return feedback;
	}

}
