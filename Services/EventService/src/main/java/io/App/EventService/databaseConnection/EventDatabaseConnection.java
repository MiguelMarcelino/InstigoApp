package io.App.EventService.databaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import io.App.EventService.EventComponent.Community;
import io.App.EventService.EventComponent.Event;
import io.App.EventService.EventComponent.User;
import io.App.EventService.exceptions.EventAlreadyExistsException;
import io.App.EventService.exceptions.InternalAppException;

public class EventDatabaseConnection {

	// import class for establishing SQL connection
	private DatabaseConnection databaseConnection;

	// SQL Queries
	private static final String GET_ALL_EVENTS_SQL = "SELECT e.id, e.name, e.start, e.end "
			+ "u.id, u.user_name, u.first_name, u.last_name, u.role_id, u.email, "
			+ "c.id, c.name, c.description, c.community_owner_id  FROM events e "
			+ "INNER JOIN users u ON e.event_owner_id = u.id"
			+ "INNER JOIN communities c ON e.community_id = c.id;";
	private static final String GET_EVENTS_FROM_COMMUNITY_SQL = "SELECT e.id, e.name, e.start, e.end "
			+ "u.id, u.user_name, u.first_name, u.last_name, u.role_id, u.email  FROM events e "
			+ "c.id, c.name, c.description, c.community_owner_id  FROM events e "
			+ "INNER JOIN users u ON e.event_owner_id = u.id "
			+ "INNER JOIN communities c ON e.community_id = c.id "
			+ "WHERE (community_id = ?);";
	private static final String CREATE_NEW_EVENT_SQL = "INSERT INTO events (name, start, end, community_id, event_owner_name) "
			+ "VALUES (?, STR_TO_DATE(?, '%Y-%c-%d'), STR_TO_DATE(?, '%Y-%c-%d'), ?, ?);";
	private static final String DELETE_EVENT_SQL = "DELETE FROM events WHERE (id = ?);";
	private static final String GET_EVENT_BY_NAME_SQL = "SELECT e.id, e.name, e.start, e.end "
			+ "u.id, u.user_name, u.first_name, u.last_name, u.role_id, u.email  FROM events e "
			+ "c.id, c.name, c.description, c.community_owner_id  FROM events e "
			+ "INNER JOIN users u ON e.event_owner_id = u.id "
			+ "INNER JOIN communities c ON e.community_id = c.id "
			+ "WHERE (e.name = ?);";
	private static final String USER_CREATED_EVENTS_SQL = "SELECT e.id, e.name, e.start, e.end "
			+ "u.id, u.user_name, u.first_name, u.last_name, u.role_id, u.email, "
			+ "c.id, c.name, c.description, c.community_owner_id  FROM events e "
			+ "INNER JOIN users u ON e.event_owner_id = u.id "
			+ "INNER JOIN communities c ON e.community_id = c.id "
			+ "WHERE (event_owner_id = ?);";

	public EventDatabaseConnection() {
		this.databaseConnection = new DatabaseConnection();
	}

	/**
	 * This method gets a list of all events in the database
	 * 
	 * @return - a list of all events in the system
	 * @throws InternalAppException 
	 */
	public List<Event> getAllEvents() throws InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Event> eventList = new ArrayList<>();

		try {
			stmt = con.prepareStatement(GET_ALL_EVENTS_SQL);
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
				eventList.add(event);
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

		return eventList;
	}

	/**
	 * This method return all events from a community given its id
	 * 
	 * @param id - the id of the community
	 * @return events from a community where cID = id
	 * @throws InternalAppException - in case there is an Application Error
	 */
	public List<Event> getEventsFromCommunity(int id)
			throws InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Event> eventList = new ArrayList<>();

		try {
			stmt = con.prepareStatement(GET_EVENTS_FROM_COMMUNITY_SQL);
			stmt.setInt(1, id);
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
				eventList.add(event);
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

		return eventList;
	}

	/**
	 * This method register a new Event on the System
	 * 
	 * @param event - the event to register
	 * @throws EventAlreadyExistsException - in case the event already exists
	 * @throws InternalAppException - in case there is an Application Error
	 */
	public void createNewEvent(Event event)
			throws EventAlreadyExistsException, InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(CREATE_NEW_EVENT_SQL);
			stmt.setString(1, event.getName());

			// insert start and end
			DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
			DateTimeFormatter formatter = DateTimeFormatter
					.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			LocalDate start = LocalDate.parse(event.getStart(), formatter);
			LocalDate end = LocalDate.parse(event.getEnd(), formatter);
			stmt.setDate(2, Date.valueOf(start));
			stmt.setDate(3, Date.valueOf(end));

			stmt.setInt(4, event.getCommunity().getId());
			stmt.setInt(5, event.getEventOwner().getId());

			stmt.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {
			throw new EventAlreadyExistsException();
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
		}

	}

	/**
	 * This method deletes and event from the database
	 * 
	 * @param event - the event to delete
	 * @throws InternalAppException - in case there is an App exception
	 */
	public void deleteEvent(Event event) throws InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(DELETE_EVENT_SQL);
			stmt.setInt(1, event.getId());
			stmt.executeUpdate();
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
		}
	}

	/**
	 * Gets an event by name
	 * 
	 * @param eName - the name of the event to get
	 * @return - the event with the given name
	 */
	public Event getEventByName(String eName) {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Event event = null;

		try {
			stmt = con.prepareStatement(GET_EVENT_BY_NAME_SQL);
			stmt.setString(1, eName);
			rs = stmt.executeQuery();

			// get the event
			rs.next();
			/*
			 * 1-eventId, 2-eventName, 3-eventStart, 4-eventEnd, 5-userId,
			 * 6-userName, 7-userFirstName, 8-userLastName, 9-userRoleId,
			 * 10-userEmail, 11-communityId, 12-communityName,
			 * 13-communityDescription, 14-communityOwnerId
			 */
			User eventOwner = new User(rs.getInt(5), rs.getString(6),
					rs.getString(7), rs.getString(8), rs.getInt(9),
					rs.getString(10));
			Community community = new Community(rs.getInt(11), rs.getString(12),
					rs.getString(13), rs.getInt(14));

			event = new Event(rs.getInt(1), rs.getString(2),
					rs.getDate(3).toString(), rs.getDate(4).toString(),
					community, eventOwner);
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
		}
		return event;
	}
	
	public List<Event> userCreatedEvents(String userName) throws InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Event> listEvents = new ArrayList<>();

		try {
			stmt = con.prepareStatement(USER_CREATED_EVENTS_SQL);
			stmt.setString(1, userName);
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
