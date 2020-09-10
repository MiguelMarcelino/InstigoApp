package io.App.CommunityService.databaseAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.App.CommunityService.business.User;
import io.App.CommunityService.business.exceptions.InternalAppException;
import io.App.CommunityService.business.exceptions.UserDoesNotExistException;

public class UserDatabaseConnection {

	// import class for establishing SQL connection
	private DatabaseConnection databaseConnection;

	private static final String GET_USER_BY_ID_SQL = "SELECT id, user_name, first_name, last_name, role_id, email  "
			+ "FROM users WHERE (id = ?);";

	public UserDatabaseConnection() {
		databaseConnection = new DatabaseConnection();
	}

	/**
	 * This method gets a user from the database by its id
	 * 
	 * @param uID - Get user info based on user id
	 * @return a user with all parameters from database
	 * @throws InternalAppException
	 * @throws UserDoesNotExistException
	 */
	public User getUserById(int uID)
			throws InternalAppException, UserDoesNotExistException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement st = null;
		ResultSet rs = null;
		User user = null;
		try {
			st = con.prepareStatement(GET_USER_BY_ID_SQL);
			st.setInt(1, uID);
			rs = st.executeQuery();

			if (rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(6), rs.getInt(5));
			} else {
				throw new UserDoesNotExistException();
			}

		} catch (SQLException e) {
			throw new InternalAppException(e.getMessage());
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

		return user;
	}

}
