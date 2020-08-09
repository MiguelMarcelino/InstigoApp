package io.App.CommunityService.databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.App.CommunityService.communityComponent.Role;
import io.App.CommunityService.exceptions.InternalAppException;
import io.App.CommunityService.exceptions.UserDoesNotExistException;

public class UserDatabaseConnection {
	
	// import class for establishing SQL connection
	private DatabaseConnection databaseConnection;
	
	private static final String GET_USER_ROLE_BY_NAME_SQL = "SELECT role FROM Users WHERE (uName = ?);";
	
	public UserDatabaseConnection() {
		databaseConnection = new DatabaseConnection();
	}

	/**
	 * 
	 * @param name
	 * @return
	 * @throws InternalAppException
	 * @throws UserDoesNotExistException
	 */
	public Role getUserRole(String username)
			throws InternalAppException, UserDoesNotExistException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement st = null;
		ResultSet rs = null;
		Role r = null;

		try {
			st = con.prepareStatement(GET_USER_ROLE_BY_NAME_SQL);
			st.setString(1, username);
			rs = st.executeQuery();
			// get user
			if (rs.next()) {
				r = Role.valueOf(rs.getString(1));
			} else {
				throw new UserDoesNotExistException();
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
		return r;
	}
}
