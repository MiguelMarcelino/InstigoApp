package io.App.UserCatalogService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UserCommunityEvent {

	public UserCommunityEvent() {

	}

	public CommunityListWrapper userSubCommunities(int uID) {
		CommunityListWrapper cLW = new CommunityListWrapper();
		List<Community> lC = new ArrayList<Community>();

		try {
			String timezone = "?serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone,
					"miguel", "M12345");

			Statement stmt = con.createStatement();
			ResultSet st1 = stmt.executeQuery("SELECT * FROM Communities AS C WHERE (C.id = "
					+ "(SELECT cID FROM RolesUsersCommunities AS RUC WHERE C.id = RUC.cID " + "AND RUC.uID = "
					+ uID + "));");
			while (st1.next()) {
				int cID = st1.getInt(1);
				String cName = st1.getString(2);
				Community c = new Community(cID, cName);
				lC.add(c);
			}

			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		// set list to the created list
		cLW.setList(lC);

		return cLW;
	}

	public EventListWrapper eventsFromSubCommunities(int uID) {
		EventListWrapper eLW = new EventListWrapper();
		List<Event> listEvents = new ArrayList<Event>();

		try {
			String timezone = "?serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone,
					"miguel", "M12345");

			Statement stmt = con.createStatement();
			ResultSet st1 = stmt.executeQuery("SELECT * FROM Events AS E WHERE (E.cID = "
					+ "(SELECT cID FROM RolesUsersCommunities AS RUC WHERE (RUC.cID = E.cID) AND " + "(RUC.uID = "
					+ uID + ")));");
			while (st1.next()) {
				int eID = st1.getInt(1);
				String eName = st1.getString(2);

				// format dates
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				String dateStart = df.format(st1.getDate(3));
				String dateEnd = df.format(st1.getDate(4));

				int cID = st1.getInt(5);
				String cName = st1.getString(6);

				// create Event
				Event e = new Event(eID, eName, dateStart, dateEnd, cID, cName);

				// insert Event into List
				listEvents.add(e);
			}

			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		// set List
		eLW.setList(listEvents);

		return eLW;
	}

	/**
	 * Vai inserir na tabela RolesUsersCommunities --> Eh adicionado o id do
	 * utilizador na tabela --> Na aplicacao vai ser disposta uma lista de
	 * Communities das quais o utilizador vai poder escolher de quais quer receber
	 * notificacoes. Quando escolher as communities o seu id sera adicionado a
	 * tabela --> O utilizador tambem vai indicar o seu Role --> O utilizador indica
	 * a data de inicio e a data de fom do seu role
	 * 
	 * @param c
	 * @param r
	 * @param dStart
	 * @param dEnd
	 */
	public void addCommunity(User u, Community c, Role r, String dStart, String dEnd) {
		Connection con = null;
		Statement stmt = null;
		try {
			String timezone = "?serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone, "miguel",
					"M12345");

			// ir buscar o proximo id da tabela RolesUsersCommunities
			GetId getid = new GetId();
			int id = getid.getTableId(con, "RolesUtilCommunities", "RolesUsersCommunities");
			stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO RolesUsersCommunities (id, uID, cID, rID, dStart, dEnd) VALUES (" + id
					+ ", " + u.getId() + ", " + c.getId() + ", " + r.getId() + ", STR_TO_DATE('" + dStart
					+ "', '%d-%c-%Y')" + ", STR_TO_DATE('" + dEnd + "', '%d-%c-%Y'));");

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (con != null) {
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public void removeCommunity(User u, Community c) {
		try {
			String timezone = "?serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone,
					"miguel", "M12345");

			PreparedStatement st = con.prepareStatement(
					"DELETE FROM RolesUsersCommunities WHERE cID = " + c.getId() + " AND uID = " + u.getId() + ";");
			st.executeUpdate();

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
