package io.App.UserManagementService.userComponent;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.UserManagementService.databaseConnection.FeedbackDatabaseConnection;
import io.App.UserManagementService.exceptions.InternalAppException;

@SpringBootApplication
public class FeedbackCatalog {

	private FeedbackDatabaseConnection fDC;

	public FeedbackCatalog() {
		this.fDC = new FeedbackDatabaseConnection();
	}

	public void storeFeedback(String username, String feedback)
			throws InternalAppException {
		this.fDC.storeFeedbackData(username, feedback);
	}

	public List<Feedback> getAllFeedback() throws InternalAppException {
		return this.fDC.getAllfeedback();
	}

}
