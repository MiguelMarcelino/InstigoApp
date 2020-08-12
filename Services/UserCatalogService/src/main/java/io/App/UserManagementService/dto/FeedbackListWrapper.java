package io.App.UserManagementService.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.App.UserManagementService.userComponent.Feedback;

public class FeedbackListWrapper {

	@JsonProperty("listFeedbacks")
	private List<Feedback> feedbackList;

	public FeedbackListWrapper() {
		// For REST only
	}
	
	public FeedbackListWrapper(List<Feedback> feedbackList) {
		this.feedbackList = feedbackList;
	}

	public List<Feedback> getFeedbackList() {
		return feedbackList;
	}

	public void setFeedbackList(List<Feedback> feedbackList) {
		this.feedbackList = feedbackList;
	}

}
