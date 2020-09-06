package io.App.UserManagementService.userComponent;

public class Feedback {

	private int id;
	private String username;
	private String datePublished;
	private String feedback;
	
	public Feedback() {
		// For REST only
	}
	
	public Feedback(int id, String username, String datePublished, String feedback) {
		this.id = id;
		this.username = username;
		this.datePublished = datePublished;
		this.feedback = feedback;
	}
	
	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}
	
	public String getDatePublished() {
		return datePublished;
	}

	public String getFeedbackContent() {
		return feedback;
	}
	
}
