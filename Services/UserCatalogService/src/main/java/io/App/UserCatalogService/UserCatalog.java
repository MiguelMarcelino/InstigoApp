package io.App.UserCatalogService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.client.RestTemplate;

public class UserCatalog {

	public UserCatalog() {

	}

	/**
	 * Este metodo devolve a lista dos utilizadores que estao registados no catalogo
	 * 
	 * @return
	 */
	public List<User> getUserList() {
		List<User> userList = new ArrayList<User>();
		try {
			String timezone = "?serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone,
					"miguel", "M12345");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Users");
			while (rs.next()) {
				User user = new User(rs.getInt(1), rs.getString(2));
				userList.add(user);
			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return userList;
	}

	public String addUser(User user) {
		try {
			String timezone = "?serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone,
					"miguel", "M12345");

			// ir buscar o proximo id do proximo User
			GetId getid = new GetId();
			int id = getid.getTableId(con, "RolesUtilCommunities", "Users");

			PreparedStatement st = con
					.prepareStatement("INSERT INTO Users (id, uName) VALUES (" + id + ",'" + user.getName() + "')");

			st.executeUpdate();

			con.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			return "Esse utilizador ja esta registado";
		} catch (Exception e) {
			return "Algo correu mal. Verifique os dados que inseriu";
		}
		return "Utilizador adicionado com sucesso!";
	}

	// quando se remove um User tem de se remover todas as suas entradas nas outras
	// tabelas
	public void removeUser(User user) {
		try {
			String timezone = "?serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone,
					"miguel", "M12345");

			PreparedStatement st1 = con.prepareStatement("DELETE FROM Users WHERE id = " + user.getId() + ";");
			st1.executeUpdate();

			PreparedStatement st2 = con
					.prepareStatement("DELETE FROM RolesUsersCommunities WHERE uID = " + user.getId() + ";");
			st2.executeUpdate();

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public List<Event> notifyUser(User u) {
		List<Event> el = new ArrayList<Event>();
		try {
			String timezone = "?serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone,
					"miguel", "M12345");

			// ter a certeza que um utilizador nao pode inserir duas vezes a mesma
			// comunidade
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT DISTINCT cID FROM RolesUsersCommunities WHERE (uID = " + u.getId() + ");");

			RestTemplate rt = new RestTemplate();
			while (rs.next()) {
				// se nao fosse possivel passar uma lista para outro microservico fazia-se com a
				// clausula "OR" do SQL para ter logo os eventos de todas as comunidades numa
				// dada lista em vez de ter os eventos de uma comunidade de cada vez
				EventListWrapper lW = rt.getForObject("localhost:8084/comEventList/" + rs.getInt(1),
						EventListWrapper.class);

				// inserir eventos na lista
				for (Event e : lW.getList()) {
					el.add(e);
				}

			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return el;
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
					+ u.getId() + "," + c.getId() + "," + r.getId() + ",TO_DATE(" + dStart + ", YYYY-MM-DD) "
					+ ", TO_DATE(" + dEnd + ", YYYY-MM-DD))");

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void removeCommunity(User u, Community c) {
		try {
			String timezone = "?serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone,
					"miguel", "M12345");

			PreparedStatement st = con
					.prepareStatement("DELETE FROM RolesUsersCommunities WHERE cID = " + c.getId() + " AND uID = " + u.getId() + ";");
			st.executeUpdate();

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	

	public boolean isRegistered(User u, String communityName) {
		try {
			String timezone = "?serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone,
					"miguel", "M12345");

			// ver se o Utilizador esta associado a uma dada communidade
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT cID FROM RolesUtilCommunities WHERE uID = " + u.getId() + ";");

			while (rs.next()) {
				ResultSet rs2 = stmt.executeQuery("SELECT cName FROM Communities WHERE cID = " + rs.getInt(1) + ";");
				if (rs2.getString(1).equals(communityName)) {
					return true;
				}
			}

			rs.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
}
