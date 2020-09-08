package io.App.CommunityService.databaseAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.App.CommunityService.business.Role;
import io.App.CommunityService.business.User;
import io.App.CommunityService.facade.exceptions.InternalAppException;
import io.App.CommunityService.facade.exceptions.UserDoesNotExistException;

public class UserDatabaseConnection {

	// import class for establishing SQL connection
	private DatabaseConnection databaseConnection;

	private static final String GET_USER_BY_ID_SQL = "SELECT u.id, u.user_name, "
			+ "u.first_name, u.last_name, u.email, r.id, r.name FROM users u "
			+ "INNER JOIN roles r ON (r.id = u.role_id)" + "WHERE u.id = ?;";

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
				Role role = new Role(rs.getInt(6), rs.getString(7));
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), role);
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
