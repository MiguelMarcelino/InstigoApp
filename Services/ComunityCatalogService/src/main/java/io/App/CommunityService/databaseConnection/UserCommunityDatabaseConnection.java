package io.App.CommunityService.databaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;

import io.App.CommunityService.communityComponent.Community;
import io.App.CommunityService.communityComponent.Role;
import io.App.CommunityService.dto.CommunityListWrapper;
import io.App.CommunityService.exceptions.AlreadySubscribedException;
import io.App.CommunityService.exceptions.InternalAppException;

public class UserCommunityDatabaseConnection {

	// import class for establishing SQL connection
	private DatabaseConnection databaseConnection;

	// SQL Queries
	private static final String USER_SUBSCRIBED_COMMUNITIES_SQL = "SELECT * FROM Communities AS C WHERE (C.cID = "
			+ "(SELECT cID FROM RolesUsersCommunities AS RUC WHERE C.cID = RUC.cID "
			+ "AND RUC.uID = ?));";
	private static final String CHECK_USER_COMMUNITY_REGISTRATION_SQL = "SELECT cID FROM RolesUsersCommunities "
			+ "WHERE cID = ? AND uID = ?;";
	private static final String SUBSCRIBE_USER_TO_COMMUNITY_SQL = "INSERT INTO RolesUsersCommunities "
			+ "(uID, cID, roleName, dStart, dEnd)" + "VALUES(?, ?, ?, ?, ?);";
	private static final String UNSUBSCRIBE_USER_FROM_COMMUNITY_SQL = "DELETE FROM RolesUsersCommunities WHERE "
			+ "(uID = ?) AND (cID = ?);";

	public UserCommunityDatabaseConnection() {
		databaseConnection = new DatabaseConnection();
	}

	/**
	 * This method returns all of the subscribed communities by a user
	 * 
	 * @param uID - the user to get the communities
	 * @return the communities subscribed by a user with id = uID
	 * @throws InternalAppException 
	 */
	public CommunityListWrapper userSubCommunities(int uID) throws InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CommunityListWrapper cLW = new CommunityListWrapper();
		ArrayList<Community> lC = new ArrayList<Community>();

		try {
			stmt = con.prepareStatement(USER_SUBSCRIBED_COMMUNITIES_SQL);
			stmt.setInt(1, uID);
			rs = stmt.executeQuery();
			while (rs.next()) {
				int cID = rs.getInt(1);
				String cName = rs.getString(2);
				String cDescription = rs.getString(3);
				Community c = new Community(cID, cName, cDescription);
				lC.add(c);
			}
		} catch (SQLException e) {
			System.err.print(e.getMessage());
			throw new InternalAppException();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
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

		cLW.setList(lC);

		return cLW;
	}

	/**
	 * This method verifies if a user has subscribed to a given community
	 * 
	 * @param uID - the user to verify
	 * @param cID - the community to verify if User u is registered to it
	 * @return
	 */
	public boolean isRegisteredToCommunity(int uID, int cID) {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		// ver se o Utilizador esta associado a uma dada communidade
		try {
			stmt = con.prepareStatement(CHECK_USER_COMMUNITY_REGISTRATION_SQL);
			stmt.setInt(1, cID);
			stmt.setInt(2, uID);

			rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
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

		return false;
	}

	/**
	 * Subscribes a user to a given community
	 * 
	 * @param uID - the id of the user to subscribe
	 * @param cID - the community where the user wants to subscribe to
	 * @throws InternalAppException - in case there is an Application Error
	 * @throws AlreadySubscribedException
	 */
	public void subscribeUserToCommunity(int uID, int cID)
			throws InternalAppException, AlreadySubscribedException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(SUBSCRIBE_USER_TO_COMMUNITY_SQL);
			stmt.setInt(1, uID);
			stmt.setInt(2, cID);

			// Every user gets the default role when they subscribe to a
			// community
			stmt.setString(3, Role.USER.name());

			// create new Dates. Every user has a default end date of 1 year
			LocalDate dateStart = LocalDate.now();
			LocalDate dateEnd = dateStart.plusYears(1);
			stmt.setDate(4, Date.valueOf(dateStart));
			stmt.setDate(5, Date.valueOf(dateEnd));
			stmt.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			throw new AlreadySubscribedException(uID, cID);
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
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void unsubscribeFromCommunity(int uID, int cID) throws InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(UNSUBSCRIBE_USER_FROM_COMMUNITY_SQL);
			stmt.setInt(1, uID);
			stmt.setInt(2, cID);

			stmt.executeUpdate();
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
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
