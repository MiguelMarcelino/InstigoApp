package io.App.UserManagementService.databaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import io.App.UserManagementService.exceptions.InternalAppException;
import io.App.UserManagementService.userComponent.Feedback;

public class FeedbackDatabaseConnection {

	// import class for establishing SQL connection
	private DatabaseConnection databaseConnection;

	// SQL Queries
	private static final String STORE_FEEDBACK_SQL = "INSERT INTO feedback (user_name, date_published, feedback_content) VALUES (?, ?, ?)";
	private static final String GET_FEEDBACK_SQL = "SELECT * FROM feedback";

	public FeedbackDatabaseConnection() {
		databaseConnection = new DatabaseConnection();
	}

	public void storeFeedbackData(String username, String datePublished,
			String feedback) throws InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement st = null;

		try {
			st = con.prepareStatement(STORE_FEEDBACK_SQL);
			st.setString(1, username);

			DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
			DateTimeFormatter formatter = DateTimeFormatter
					.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			LocalDate dateOfPublication = LocalDate.parse(datePublished,
					formatter);

			st.setDate(2, Date.valueOf(dateOfPublication));
			st.setString(3, feedback);
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

	public List<Feedback> getAllfeedback() throws InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Feedback> fLW = new ArrayList<>();

		try {
			st = con.prepareStatement(GET_FEEDBACK_SQL);
			rs = st.executeQuery();

			while (rs.next()) {
				fLW.add(new Feedback(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4)));
			}

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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return fLW;
	}
}
