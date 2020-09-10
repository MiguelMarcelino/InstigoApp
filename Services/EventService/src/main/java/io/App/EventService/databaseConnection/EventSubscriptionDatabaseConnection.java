package io.App.EventService.databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import io.App.EventService.business.Event;
import io.App.EventService.business.exceptions.InternalAppException;

public class EventSubscriptionDatabaseConnection {

	// import class for establishing SQL connection
	private DatabaseConnection databaseConnection;

	// LEFT JOIN TODO
	private static final String EVENTS_FROM_SUBSCRIBED_COMMUNITIES_SQL = "SELECT e.id, e.name, e.start, e.end,"
			+ "e.community_id, e.event_owner_id FROM events e "
			+ "INNER JOIN user_subscribed_communities usc ON ((usc.user_id =  ?) "
			+ "AND (usc.community_id = c.id));";

	public EventSubscriptionDatabaseConnection() {
		databaseConnection = new DatabaseConnection();
	}

	/**
	 * This method returns all events from communities subscribed by uID
	 * 
	 * @param uID - the user to get all events
	 * @return all events from the user (uID) subscribed communities
	 * @throws InternalAppException
	 */
	public List<Event> eventsFromSubCommunities(int uID)
			throws InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Event> listEvents = new ArrayList<>();

		try {
			stmt = con.prepareStatement(EVENTS_FROM_SUBSCRIBED_COMMUNITIES_SQL);
			stmt.setInt(1, uID);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Event event = new Event(rs.getInt(1), rs.getString(2),
						rs.getDate(3).toString(), rs.getDate(4).toString(),
						rs.getInt(5), rs.getInt(6));

				// insert Event into List
				listEvents.add(event);
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
