package io.App.UserCatalogService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class User {

	private int id;
	private String name;
	// password

	/**
	 * Existe um construtor so com o nome, visto que o id é atribuido ao user quando
	 * ele eh introduzido na tabela
	 * 
	 * @param name - o nome do utilizador
	 */
	public User(String name) {
		this.name = name;
	}

	/**
	 * Existe tambem um construtor com id e name para quando queremos ir buscar os
	 * Utilizadores a tabela de utilizadores, visto que ai eles ja incluem o id
	 * 
	 * @param id   - o id do utilizador
	 * @param name - o nome do utilizador
	 */
	public User(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	// tem que mudar --> notificacao em android
	public List<Event> notifyUser() {
		List<Event> lEvent = new ArrayList<Event>();
		try {
			String timezone = "?serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone,
					"miguel", "M12345");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT cID FROM RolesUtilCommunities WHERE (uID = " + getId() + ");");
			List<Integer> li = new ArrayList<Integer>();
			while (rs.next()) {
				li.add(rs.getInt(1));
			}

			// aceder ao microservico EventService e pedir todos os eventos que estao
			// relacionados com as comunidades

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return lEvent;
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
	public void addCommunity(Community c, Role r, String dStart, String dEnd) {
		try {
			String timezone = "?serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone,
					"miguel", "M12345");

			// ir buscar o proximo id da tabela RolesUsersCommunities
			GetId getid = new GetId();
			int id = getid.getTableId(con, "RolesUtilCommunities", "RolesUsersCommunities");

			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO RolesUsersCommunities (id, uID, cID, rID, dStart, dEnd) VALUES (" + id + ","
					+ getId() + "," + c.getId() + "," + r.getId() + ",TO_DATE(" + dStart + ", YYYY-MM-DD) "
					+ ", TO_DATE(" + dEnd + ", YYYY-MM-DD))");

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void removeCommunity(Community c) {
		try {
			String timezone = "?serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone,
					"miguel", "M12345");

			PreparedStatement st = con
					.prepareStatement("DELETE FROM RolesUsersCommunities WHERE cID = " + c.getId() + ";");
			st.executeUpdate();

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
