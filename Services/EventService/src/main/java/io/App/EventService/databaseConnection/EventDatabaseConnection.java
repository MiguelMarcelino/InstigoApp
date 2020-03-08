package io.App.EventService.databaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.EventService.EventComponent.Event;
import io.App.EventService.databaseConnection.DatabaseConnection;
import io.App.EventService.dto.EventListWrapper;

@SpringBootApplication
public class EventDatabaseConnection {

	private DatabaseConnection databaseConnection;

	public EventDatabaseConnection() {
		this.databaseConnection = new DatabaseConnection();
	}

	/**
	 * 
	 * @return a list of all events in the system
	 */
	public EventListWrapper getAllEvents() {
		Connection con = databaseConnection.connectToDatabase();
		Statement stmt = null;
		ResultSet rs = null;
		EventListWrapper eLW = new EventListWrapper();
		ArrayList<Event> eventList = new ArrayList<Event>();

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Events");
			while (rs.next()) {
				Event event = new Event(rs.getInt(1), rs.getString(2), rs.getDate(3).toString(),
						rs.getDate(4).toString(), rs.getInt(5), rs.getString(6));
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
	 */
	public EventListWrapper getEventsFromCommunity(int id) {
		Connection con = databaseConnection.connectToDatabase();
		Statement stmt = null;
		ResultSet rs = null;
		EventListWrapper lW = new EventListWrapper();
		ArrayList<Event> eventList = new ArrayList<Event>();

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Events WHERE (cID = " + id + ")");
			while (rs.next()) {
				Event event = new Event(rs.getInt(1), rs.getString(2), rs.getDate(3).toString(),
						rs.getDate(4).toString(), rs.getInt(5), rs.getString(6));
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

		lW.setList(eventList);
		return lW;
	}

	/**
	 * This method register a new Event on the System
	 * 
	 * @param event - the event to register
	 */
	public void registerNewEvent(Event event) {
		Connection con = databaseConnection.connectToDatabase();
		Statement stmt = null;

		try {
			stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO Events (eName, start, end, cID, cName) VALUES ('" + event.getName()
					+ "',STR_TO_DATE('" + event.getStart() + "', '%d-%c-%Y,%H:%i'), STR_TO_DATE('" + event.getEnd()
					+ "', '%d-%c-%Y,%H:%i'), " + event.getId() + ", '" + event.getcName() + "')");
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

	}

	/**
	 * This method deletes and event from the database
	 * 
	 * @param event - the event to delete
	 */
	public void deleteEvent(Event event) {
		Connection con = databaseConnection.connectToDatabase();
		Statement stmt = null;

		try {
			stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM Events WHERE eID = " + event.getId() + ";");
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
	}
}
