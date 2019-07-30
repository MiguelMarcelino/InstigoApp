package io.App.RoleCatalogService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleCatalog {

	public RoleCatalog() {

	}

	public List<Role> getRoleList() {
		List<Role> userList = new ArrayList<Role>();
		try {
			String timezone = "?serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone,
					"miguel", "M12345");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Roles;");
			while (rs.next()) {
				Role role = new Role(rs.getInt(1), rs.getString(2), rs.getInt(3));
				userList.add(role);
			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return userList;
	}

	public void addRole(Role role) {
		try {
			String timezone = "?serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone,
					"miguel", "M12345");

			// ir buscar o proximo id do proximo Role
			GetId getid = new GetId();
			int id = getid.getTableId(con, "RolesUtilCommunities", "Roles");

			PreparedStatement st = con.prepareStatement("INSERT INTO Roles (id, rName, authLevel) VALUES (" + id + ",'"
					+ role.getName() + "'," + role.getAuthLevel() + ");");
			st.executeUpdate();

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void removeRole(Role role) {
		try {
			String timezone = "?serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone,
					"miguel", "M12345");

			PreparedStatement st1 = con.prepareStatement("DELETE FROM Roles WHERE id = " + role.getId() + ";");
			st1.executeUpdate();

			PreparedStatement st2 = con
					.prepareStatement("DELETE FROM RolesUsersCommunities WHERE rID = " + role.getId() + ";");
			st2.executeUpdate();

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
