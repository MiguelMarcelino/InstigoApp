package io.App.UserManagementService.userComponent;

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

}
