package io.App.UserCatalogService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

@RestController
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

	public void addUser(User user) {
		try {
			String timezone = "?serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone,
					"miguel", "M12345");

			// ir buscar o proximo id do proximo User
			GetId getid = new GetId();
			int id = getid.getTableId(con, "RolesUtilCommunities", "Users");

			PreparedStatement st = con.prepareStatement(
					"INSERT INTO Users (id, uName) VALUES (" + id + ",'" + user.getName() + "')");

			st.executeUpdate();

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
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
}
