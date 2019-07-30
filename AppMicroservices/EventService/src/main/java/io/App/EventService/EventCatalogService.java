package io.App.EventService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventCatalogService {

	public EventCatalogService() {
		// TODO Auto-generated constructor stub
	}

	public List<Event> getAllEvents() {
		List<Event> eventList = new ArrayList<Event>();
		try {
			String timezone = "?serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone,
					"miguel", "M12345");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Events");
			while (rs.next()) {
				Event event = new Event(rs.getInt(1), rs.getString(2), rs.getDate(3).toString(),
						rs.getDate(4).toString(), rs.getInt(5));
				eventList.add(event);
			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return eventList;
	}

	public List<Event> getEventsFromCommunity(Community c) {
		List<Event> eventList = new ArrayList<Event>();
		try {
			String timezone = "?serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone,
					"miguel", "M12345");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM CommunityEvents WHERE (cID = " + c.getId() + ")");
			while (rs.next()) {
				Event event = new Event(rs.getInt(1), rs.getString(2), rs.getDate(3).toString(),
						rs.getDate(4).toString(), rs.getInt(5));
				eventList.add(event);
			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return eventList;
	}

	public void registerNewEvent(Event event, Community c) {
		try {
			String timezone = "?serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone,
					"miguel", "M12345");

			// ir buscar o proximo id do proximo Evento
			GetId getid = new GetId();
			int id = getid.getTableId(con, "RolesUtilCommunities", "Events");

			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO Events (id, eName, start, end, cID) VALUES ( " + id + ", " + event.getName()
					+ "," + event.getStart() + ", " + event.getEnd() + ", " +  c.getId() + ")");

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
