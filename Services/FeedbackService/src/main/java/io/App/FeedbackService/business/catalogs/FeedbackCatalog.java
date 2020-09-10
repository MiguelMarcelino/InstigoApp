package io.App.FeedbackService.business.catalogs;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.FeedbackService.business.Feedback;
import io.App.FeedbackService.business.exceptions.InternalAppException;
import io.App.FeedbackService.databaseConnection.FeedbackDatabaseConnection;

@SpringBootApplication
public class FeedbackCatalog {

	private FeedbackDatabaseConnection fDC;

	public FeedbackCatalog() {
		this.fDC = new FeedbackDatabaseConnection();
	}

	public void storeFeedback(String username, String datePublished, String feedback)
			throws InternalAppException {
		this.fDC.storeFeedbackData(username, datePublished, feedback);
	}

	public List<Feedback> getAllFeedback() throws InternalAppException {
		return this.fDC.getAllfeedback();
	}

}
