package io.App.EventService.databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import io.App.EventService.EventComponent.Event;
import io.App.EventService.dto.EventListWrapper;


public class UserEventDatabaseManagement {

	// import class for establishing SQL connection
	private DatabaseConnection databaseConnection;
	
	private static final String EVENTS_FROM_SUBSCRIBED_COMMUNITIES_SQL = "SELECT * FROM Events AS E WHERE (E.cID = "
			+ "(SELECT cID FROM RolesUsersCommunities AS RUC WHERE (RUC.cID = E.cID) AND (RUC.uID = ?)));";
	
	public UserEventDatabaseManagement() {
		databaseConnection = new DatabaseConnection();
	}
		
	/**
	 * This method returns all events from communities subscribed by uID
	 * 
	 * @param uID - the user to get all events
	 * @return all events from the user (uID) subscribed communities
	 */
	public EventListWrapper eventsFromSubCommunities(int uID) {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		EventListWrapper eLW = new EventListWrapper();
		ArrayList<Event> listEvents = new ArrayList<Event>();

		try {
			stmt = con.prepareStatement(EVENTS_FROM_SUBSCRIBED_COMMUNITIES_SQL);
			stmt.setInt(1, uID);
			rs = stmt.executeQuery();
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
}
