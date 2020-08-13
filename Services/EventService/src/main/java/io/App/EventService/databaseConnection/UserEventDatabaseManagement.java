package io.App.EventService.databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import io.App.EventService.dto.EventDTO;
import io.App.EventService.exceptions.InternalAppException;

public class UserEventDatabaseManagement {

	// import class for establishing SQL connection
	private DatabaseConnection databaseConnection;

	private static final String EVENTS_FROM_SUBSCRIBED_COMMUNITIES_SQL = "SELECT * FROM Events AS E WHERE (E.cID = "
			+ "(SELECT cID FROM RolesUsersCommunities AS RUC WHERE (RUC.cID = E.cID) AND (RUC.uID = ?)));";
	private static final String USER_CREATED_EVENTS_SQL = "SELECT * FROM Events WHERE (ownerUserName = ?)";

	public UserEventDatabaseManagement() {
		databaseConnection = new DatabaseConnection();
	}

	/**
	 * This method returns all events from communities subscribed by uID
	 * 
	 * @param uID - the user to get all events
	 * @return all events from the user (uID) subscribed communities
	 * @throws InternalAppException
	 */
	public List<EventDTO> eventsFromSubCommunities(int uID)
			throws InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<EventDTO> listEvents = new ArrayList<>();

		try {
			stmt = con.prepareStatement(EVENTS_FROM_SUBSCRIBED_COMMUNITIES_SQL);
			stmt.setInt(1, uID);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String eID = String.valueOf(rs.getInt(1));
				String eName = rs.getString(2);

				// format dates
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				String dateStart = df.format(rs.getDate(3));
				String dateEnd = df.format(rs.getDate(4));

				String cID = String.valueOf(rs.getInt(5));
				String cName = rs.getString(6);
				String ownerUserName = rs.getString(7);

				// create Event
				EventDTO e = new EventDTO(eID, eName, dateStart, dateEnd, cID,
						cName, ownerUserName);

				// insert Event into List
				listEvents.add(e);
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

		return listEvents;
	}

	public List<EventDTO> userCreatedEvents(String userName) throws InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<EventDTO> listEvents = new ArrayList<>();

		try {
			stmt = con.prepareStatement(USER_CREATED_EVENTS_SQL);
			stmt.setString(1, userName);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String eID = String.valueOf(rs.getInt(1));
				String eName = rs.getString(2);

				// format dates
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				String dateStart = df.format(rs.getDate(3));
				String dateEnd = df.format(rs.getDate(4));

				String cID = String.valueOf(rs.getInt(5));
				String cName = rs.getString(6);
				String ownerUserName = rs.getString(7);

				// create Event
				EventDTO e = new EventDTO(eID, eName, dateStart, dateEnd, cID,
						cName, ownerUserName);

				// insert Event into List
				listEvents.add(e);
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

		return listEvents;
	}
}
