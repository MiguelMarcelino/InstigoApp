package io.App.UserManagementService.userComponent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.UserManagementService.databaseConnection.DatabaseConnection;
import io.App.UserManagementService.dto.CommunityListWrapper;
import io.App.UserManagementService.dto.EventListWrapper;

@SpringBootApplication
public class UserCommunityEvent {

	private DatabaseConnection databaseConnection;

	public UserCommunityEvent() {
		databaseConnection = new DatabaseConnection();
	}

	/**
	 * This method returns all of the subscribed communities by a user
	 * 
	 * @param uID - the user to get the communities
	 * @return the communities subscribed by a user with id = uID
	 */
	public CommunityListWrapper userSubCommunities(int uID) {
		Connection con = databaseConnection.connectToDatabase();
		Statement stmt = null;
		ResultSet rs = null;
		CommunityListWrapper cLW = new CommunityListWrapper();
		ArrayList<Community> lC = new ArrayList<Community>();

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Communities AS C WHERE (C.cID = "
					+ "(SELECT cID FROM RolesUsersCommunities AS RUC WHERE C.cID = RUC.cID " + "AND RUC.uID = " + uID
					+ "));");
			while (rs.next()) {
				int cID = rs.getInt(1);
				String cName = rs.getString(2);
				Community c = new Community(cID, cName);
				lC.add(c);
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

		cLW.setList(lC);

		return cLW;
	}

	/**
	 * This method returns all events from communities subscribed by uID
	 * 
	 * @param uID - the user to get all events
	 * @return all events from the user (uID) subscribed communities
	 */
	public EventListWrapper eventsFromSubCommunities(int uID) {
		Connection con = databaseConnection.connectToDatabase();
		Statement stmt = null;
		ResultSet rs = null;
		EventListWrapper eLW = new EventListWrapper();
		ArrayList<Event> listEvents = new ArrayList<Event>();

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Events AS E WHERE (E.cID = "
					+ "(SELECT cID FROM RolesUsersCommunities AS RUC WHERE (RUC.cID = E.cID) AND " + "(RUC.uID = " + uID
					+ ")));");
			while (rs.next()) {
				int eID = rs.getInt(1);
				String eName = rs.getString(2);

				// format dates
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				String dateStart = df.format(rs.getDate(3));
				String dateEnd = df.format(rs.getDate(4));

				int cID = rs.getInt(5);
				String cName = rs.getString(6);

				// create Event
				Event e = new Event(eID, eName, dateStart, dateEnd, cID, cName);

				// insert Event into List
				listEvents.add(e);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
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

		// set List
		eLW.setList(listEvents);

		return eLW;
	}

	/**
	 * This method verifies if a user has subscribed to a given community
	 * 
	 * @param uID - the user to verify
	 * @param cID - the community to verify if User u is registered to it
	 * @return
	 */
	public boolean isRegistered(int uID, int cID) {
		Connection con = databaseConnection.connectToDatabase();
		Statement stmt = null;
		ResultSet rs = null;

		// ver se o Utilizador esta associado a uma dada communidade
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(
					"SELECT cID FROM RolesUsersCommunities WHERE cID = " + cID + " AND uID = " + uID + ";");

			// rever isto
			while (rs.next()) {
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
}