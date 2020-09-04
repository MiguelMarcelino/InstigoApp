package io.App.EventService.databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import io.App.EventService.EventComponent.Community;
import io.App.EventService.EventComponent.Event;
import io.App.EventService.EventComponent.User;
import io.App.EventService.exceptions.InternalAppException;

public class EventSubscriptionDatabaseConnection {

	// import class for establishing SQL connection
	private DatabaseConnection databaseConnection;

	// LEFT JOIN TODO
	private static final String EVENTS_FROM_SUBSCRIBED_COMMUNITIES_SQL = "SELECT e.id, e.name, e.start, e.end,"
			+ "u.id, u.user_name, u.first_name, u.last_name, u.role_id, u.email," 
			+ "c.id, c.name, c.description, c.community_owner_id  FROM events e "
			+ "INNER JOIN users u ON e.event_owner_id = u.id "
			+ "INNER JOIN communities c ON e.community_id = c.id "
			+ "INNER JOIN user_subscribed_communities usc ON ((usc.user_id =  ?) "
			+ "AND (usc.community_id = c.id));";
			
			
//			"SELECT * FROM Events AS E WHERE (E.cID = "
//			+ "(SELECT cID FROM RolesUsersCommunities AS RUC WHERE (RUC.cID = E.cID) AND (RUC.uID = ?)));";

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
				/*
				 * 1-eventId, 2-eventName, 3-eventStart, 4-eventEnd, 5-userId,
				 * 6-userName, 7-userFirstName, 8-userLastName, 9-userRoleId,
				 * 10-userEmail, 11-communityId, 12-communityName,
				 * 13-communityDescription, 14-communityOwnerId
				 */
				User eventOwner = new User(rs.getInt(5), rs.getString(6),
						rs.getString(7), rs.getString(8), rs.getInt(9),
						rs.getString(10));
				Community community = new Community(rs.getInt(11),
						rs.getString(12), rs.getString(13), rs.getInt(14));

				Event event = new Event(rs.getInt(1), rs.getString(2),
						rs.getDate(3).toString(), rs.getDate(4).toString(),
						community, eventOwner);

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
