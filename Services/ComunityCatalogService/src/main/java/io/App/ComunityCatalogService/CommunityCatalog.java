package io.App.ComunityCatalogService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommunityCatalog {

	public CommunityCatalog() {
	}

	public CommunityListWrapper getCommunityList() {
		List<Community> communityList = new ArrayList<Community>();
		try {
			String timezone = "?serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone,
					"miguel", "M12345");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Communities");
			while (rs.next()) {
				Community community = new Community(rs.getInt(1), rs.getString(2));
				communityList.add(community);
			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		CommunityListWrapper cLW = new CommunityListWrapper();
		cLW.setList(communityList);
		return cLW;
	}

	public void addCommunity(Community community) {
		try {
			String timezone = "?serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone,
					"miguel", "M12345");

			// ir buscar o proximo id da proxima Community
			GetId getid = new GetId();
			int id = getid.getTableId(con, "RolesUtilCommunities", "Communities");

			PreparedStatement st = con.prepareStatement(
					"INSERT INTO Communities (id, cName) VALUES (" + id + ",'" + community.getName() + "')");
			st.executeUpdate();

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void removeCommunity(Community community) {
		try {
			String timezone = "?serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone,
					"miguel", "M12345");

			PreparedStatement st1 = con.prepareStatement("DELETE FROM Communities WHERE id = " + community.getId());
			st1.executeUpdate();

			PreparedStatement st2 = con
					.prepareStatement("DELETE FROM RolesUsersCommunities WHERE cID = " + community.getId() + ";");
			st2.executeUpdate();

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public Community getCommunityNameById(int id) {
		Community c = new Community(id, "");

		try {
			String timezone = "?serverTimezone=UTC";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone,
					"miguel", "M12345");

			Statement stmt = con.createStatement();
			ResultSet st1 = stmt.executeQuery("SELECT cName FROM Communities WHERE id = " + id);
			c.setName(st1.getString(1));

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return c;

	}

}
