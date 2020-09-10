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

import io.App.EventService.business.Event;
import io.App.EventService.business.exceptions.EventAlreadyExistsException;
import io.App.EventService.business.exceptions.InternalAppException;

public class EventDatabaseConnection {

	// import class for establishing SQL connection
	private DatabaseConnection databaseConnection;

	// SQL Queries
	private static final String GET_ALL_EVENTS_SQL = "SELECT * FROM events;";
	private static final String GET_EVENTS_FROM_COMMUNITY_SQL = "SELECT * FROm events"
			+ "WHERE (community_id = ?);";
	private static final String CREATE_NEW_EVENT_SQL = "INSERT INTO events (name, start, end, community_id, event_owner_name) "
			+ "VALUES (?, STR_TO_DATE(?, '%Y-%c-%d'), STR_TO_DATE(?, '%Y-%c-%d'), ?, ?);";
	private static final String DELETE_EVENT_SQL = "DELETE FROM events WHERE (id = ?);";
	private static final String GET_EVENT_BY_NAME_SQL = "SELECT * FROM events"
			+ "WHERE (e.name = ?);";
	private static final String USER_CREATED_EVENTS_SQL = "SELECT * FROM events"
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
				Event event = new Event(rs.getInt(1), rs.getString(2),
						rs.getDate(3).toString(), rs.getDate(4).toString(),
						rs.getInt(5), rs.getInt(6));
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
				Event event = new Event(rs.getInt(1), rs.getString(2),
						rs.getDate(3).toString(), rs.getDate(4).toString(),
						rs.getInt(5), rs.getInt(6));
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

			stmt.setInt(4, event.getCommunityId());
			stmt.setInt(5, event.getEventOwnerId());

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
			event = new Event(rs.getInt(1), rs.getString(2),
					rs.getDate(3).toString(), rs.getDate(4).toString(),
					rs.getInt(5), rs.getInt(6));
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

	public List<Event> userCreatedEvents(String userName)
			throws InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Event> listEvents = new ArrayList<>();

		try {
			stmt = con.prepareStatement(USER_CREATED_EVENTS_SQL);
			stmt.setString(1, userName);
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
