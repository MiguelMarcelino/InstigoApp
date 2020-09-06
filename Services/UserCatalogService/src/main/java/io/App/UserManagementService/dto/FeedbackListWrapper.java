package io.App.UserManagementService.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedbackListWrapper {

	@JsonProperty("listFeedbacks")
	private List<FeedbackDTO> feedbackList;

	public FeedbackListWrapper() {
		// For REST only
	}
	
	public FeedbackListWrapper(List<FeedbackDTO> feedbackList) {
		this.feedbackList = feedbackList;
	}

	public List<FeedbackDTO> getFeedbackList() {
		return feedbackList;
	}

	public void setFeedbackList(List<FeedbackDTO> feedbackList) {
		this.feedbackList = feedbackList;
	}

}
