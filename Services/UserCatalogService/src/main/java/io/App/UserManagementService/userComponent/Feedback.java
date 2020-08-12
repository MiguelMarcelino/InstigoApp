package io.App.UserManagementService.userComponent;

public class Feedback {

	private int id;
	private String username;
	private String feedback;
	
	public Feedback() {
		// For REST only
	}
	
	public Feedback(int id, String username, String feedback) {
		this.id = id;
		this.feedback = feedback;
		this.username = username;
	}
	
	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getFeedback() {
		return feedback;
	}
	
}
