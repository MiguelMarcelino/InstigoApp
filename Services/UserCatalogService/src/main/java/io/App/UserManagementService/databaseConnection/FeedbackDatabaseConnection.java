package io.App.UserManagementService.databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import io.App.UserManagementService.exceptions.InternalAppException;

public class FeedbackDatabaseConnection {

	// import class for establishing SQL connection
	private DatabaseConnection databaseConnection;

	// SQL Queries
	private static final String STORE_FEEDBACK_SQL = "INSERT INTO Feedback (username, feedback) VALUES (?, ?)";

	public FeedbackDatabaseConnection() {
		databaseConnection = new DatabaseConnection();
	}

	public void storeFeedbackData(String username, String feedback)
			throws InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement st = null;

		try {
			st = con.prepareStatement(STORE_FEEDBACK_SQL);
			st.setString(1, username);
			st.setString(2, feedback);
			st.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new InternalAppException();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
