package io.App.EventService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class EventCatalog {

	public EventCatalog() {
		// TODO Auto-generated constructor stub
	}

	public EventListWrapper getAllEvents() {
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
						rs.getDate(4).toString(), rs.getInt(5), rs.getString(6));
				eventList.add(event);
			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		EventListWrapper eLW = new EventListWrapper();
		eLW.setList(eventList);
		return eLW;
	}

	@RequestMapping("/comEventList/{communityID}")
	public EventListWrapper getEventsFromCommunity(@PathVariable("communityID") int id) {
		EventListWrapper lW = new EventListWrapper();
		List<Event> eventList = new ArrayList<Event>();
		try {
			String timezone = "?serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone,
					"miguel", "M12345");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM CommunityEvents WHERE (cID = " + id + ")");
			while (rs.next()) {
				Event event = new Event(rs.getInt(1), rs.getString(2), rs.getDate(3).toString(),
						rs.getDate(4).toString(), rs.getInt(5), rs.getString(6));
				eventList.add(event);
			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		lW.setList(eventList);
		return lW;
	}

	public void registerNewEvent(Event event) {
		try {
			String timezone = "?serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone,
					"miguel", "M12345");

			// ir buscar o proximo id do proximo Evento
			GetId getid = new GetId();
			int id = getid.getTableId(con, "RolesUtilCommunities", "Events");

			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO Events (id, eName, start, end, cID, cName) VALUES ( " + id + ", '"
					+ event.getName() + "',STR_TO_DATE('" + event.getStart() + "', '%d-%c-%Y,%H:%i'), STR_TO_DATE('"
					+ event.getEnd() + "', '%d-%c-%Y,%H:%i'), " + event.getId() + ", '" + event.getcName() + "')");

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
