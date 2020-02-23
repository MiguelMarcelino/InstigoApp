package io.App.ComunityCatalogService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommunityCatalog {

	private DatabaseConnection databaseConnection;

	public CommunityCatalog() {
		databaseConnection = new DatabaseConnection();
	}

	/**
	 * This method return a list of all communities found on the database
	 * 
	 * @return a list of all communities found on the database
	 */
	public CommunityListWrapper getCommunityList() {
		Connection con = databaseConnection.connectToDatabase();
		List<Community> communityList = new ArrayList<Community>();
		CommunityListWrapper cLW = new CommunityListWrapper();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Communities");
			while (rs.next()) {
				Community community = new Community(rs.getInt(1), rs.getString(2));
				communityList.add(community);
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

		cLW.setList(communityList);
		return cLW;
	}

	/**
	 * This method adds a community to the Communities Database
	 * 
	 * @param community - the community to add
	 */
	public void addCommunity(Community community) {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement st = null;

		try {
			st = con.prepareStatement("INSERT INTO Communities (cName) VALUES ('" + community.getName() + "')");
			st.executeUpdate();
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
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * This method remove a community from the Communities Database
	 * 
	 * @param community - the community to remove
	 */
	public void removeCommunity(Community community) {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement st1 = null;
		PreparedStatement st2 = null;

		try {
			st1 = con.prepareStatement("DELETE FROM Communities WHERE cID = " + community.getId());
			st1.executeUpdate();

			st2 = con.prepareStatement("DELETE FROM RolesUsersCommunities WHERE cID = " + community.getId() + ";");
			st2.executeUpdate();

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
			if (st1 != null) {
				try {
					st1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (st2 != null) {
				try {
					st2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * This method returns a community name given a community id
	 * 
	 * @param id - the id of the community to get the name
	 * @return the name of the given community id
	 */
	public Community getCommunityNameById(int id) {
		Connection con = databaseConnection.connectToDatabase();
		Statement stmt = null;
		ResultSet rs = null;
		Community c = new Community(id, "");

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT cName FROM Communities WHERE cID = " + id);
			c.setName(rs.getString(1));
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

		return c;

	}

}
