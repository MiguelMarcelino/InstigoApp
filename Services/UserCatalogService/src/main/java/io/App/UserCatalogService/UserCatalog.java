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
	
	public User getUserInfo(int uID) {
		String uName = null;
		Connection con = null;
		ResultSet rs = null;
		
		try {
			String timezone = "?serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone,
					"miguel", "M12345");

			// ver se o Utilizador esta associado a uma dada communidade
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT uName FROM Users WHERE id = " + uID + ";");
			
			//get next value
			rs.next();
			uName = rs.getString(1);

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		User u = new User(uID, uName);
		return u;
	}
	
}
