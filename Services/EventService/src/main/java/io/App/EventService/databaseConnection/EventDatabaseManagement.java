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

import io.App.EventService.EventComponent.Event;
import io.App.EventService.dto.EventDTO;
import io.App.EventService.dto.EventListWrapper;
import io.App.EventService.exceptions.EventAlreadyExistsException;
import io.App.EventService.exceptions.InternalAppException;

public class EventDatabaseManagement {

	// import class for establishing SQL connection
	private DatabaseConnection databaseConnection;

	// SQL Queries
	private static final String GET_ALL_EVENTS_SQL = "SELECT * FROM Events";
	private static final String GET_EVENTS_FROM_COMMUNITY_SQL = "SELECT * FROM Events WHERE (cID = ?)";
	private static final String REGISTER_NEW_EVENT_SQL = "INSERT INTO Events (eName, cID, cName, start, end, ownerUserName) "
			+ "VALUES (?, ?, ?, STR_TO_DATE(?, '%Y-%c-%d'), STR_TO_DATE(?, '%Y-%c-%d'), ?)";
	private static final String DELETE_EVENT_SQL = "DELETE FROM Events WHERE (id = ?);";
	private static final String GET_EVENT_BY_NAME_SQL = "SELECT eName FROM Events WHERE eName = ?;";

	public EventDatabaseManagement() {
		this.databaseConnection = new DatabaseConnection();
	}

	/**
	 * This method gets a list of all events in the database
	 * 
	 * @return - a list of all events in the system
	 */
	public EventListWrapper getAllEvents() {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		EventListWrapper eLW = new EventListWrapper();
		ArrayList<EventDTO> eventList = new ArrayList<>();

		try {
			stmt = con.prepareStatement(GET_ALL_EVENTS_SQL);
			rs = stmt.executeQuery();
			while (rs.next()) {
				EventDTO event = new EventDTO(String.valueOf(rs.getInt(1)), rs.getString(2),
						rs.getDate(3).toString(), rs.getDate(4).toString(),
						String.valueOf(rs.getInt(5)), rs.getString(6), rs.getString(7));
				eventList.add(event);
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

		eLW.setList(eventList);
		return eLW;
	}

	/**
	 * This method return all events from a community given its id
	 * 
	 * @param id - the id of the community
	 * @return events from a community where cID = id
	 * @throws InternalAppException - in case there is an Application Error
	 */
	public EventListWrapper getEventsFromCommunity(int id) throws InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		EventListWrapper lW = new EventListWrapper();
		ArrayList<EventDTO> eventList = new ArrayList<>();

		try {
			stmt = con.prepareStatement(GET_EVENTS_FROM_COMMUNITY_SQL);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				EventDTO event = new EventDTO(String.valueOf(rs.getInt(1)), rs.getString(2),
						rs.getDate(3).toString(), rs.getDate(4).toString(),
						String.valueOf(rs.getInt(5)), rs.getString(6), rs.getString(7));
				eventList.add(event);
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

		lW.setList(eventList);
		return lW;
	}

	/**
	 * This method register a new Event on the System
	 * 
	 * @param event - the event to register
	 * @throws EventAlreadyExistsException - in case the event already exists
	 * @throws InternalAppException - in case there is an Application Error
	 */
	public void registerNewEvent(Event event)
			throws EventAlreadyExistsException, InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(REGISTER_NEW_EVENT_SQL);
			stmt.setString(1, event.getName());
			stmt.setInt(2, event.getcID());
			stmt.setString(3, event.getcName());

			DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			LocalDate start = LocalDate.parse(event.getStart(), formatter);
			LocalDate end = LocalDate.parse(event.getEnd(), formatter);

			stmt.setDate(4, Date.valueOf(start));
			stmt.setDate(5, Date.valueOf(end));
			stmt.setString(6, event.getOwnerUserName());
			stmt.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {
			throw new EventAlreadyExistsException();
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
					rs.getInt(5), rs.getString(6), rs.getString(7));
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
}
