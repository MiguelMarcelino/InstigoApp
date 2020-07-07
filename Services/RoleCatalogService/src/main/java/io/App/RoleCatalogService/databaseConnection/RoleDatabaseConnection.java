package io.App.RoleCatalogService.databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import io.App.RoleCatalogService.dto.RoleListWrapper;
import io.App.RoleCatalogService.roleComponent.Role;

public class RoleDatabaseConnection {

	private DatabaseConnection databaseConnection;

	public RoleDatabaseConnection() {
		this.databaseConnection = new DatabaseConnection();
	}

	/**
	 * This method returns the list of Roles on the database
	 * 
	 * @return a list of roles
	 */
	public RoleListWrapper getRoleDatabaseList() {
		Connection con = databaseConnection.connectToDatabase();
		Statement stmt = null;
		ResultSet rs = null;
		RoleListWrapper rLW = new RoleListWrapper();
		List<Role> roleList = new ArrayList<Role>();

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Roles;");
			while (rs.next()) {
				Role role = new Role(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(3));
				roleList.add(role);
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

		rLW.setRoleList(roleList);
		return rLW;
	}

	/**
	 * This method adds a role to the database
	 * 
	 * @param role - the role to add
	 */
	public void addRoleToDatabase(Role role) {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement st = null;

		try {
			st = con.prepareStatement("INSERT INTO Roles (rName, authLevel) VALUES ('" + role.getName() + "',"
					+ role.getAuthLevel() + ");");
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
	 * This method removes a role from the database
	 * 
	 * @param role - The role to remove
	 */
	public void removeRoleFromDatabase(Role role) {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement st1 = null;
		PreparedStatement st2 = null;

		try {
			st1 = con.prepareStatement("DELETE FROM Roles WHERE rID = " + role.getId() + ";");
			st1.executeUpdate();

			st2 = con.prepareStatement("DELETE FROM RolesUsersCommunities WHERE rID = " + role.getId() + ";");
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

	public Role getRoleByName(String name) {
		Connection con = databaseConnection.connectToDatabase();
		Statement stmt = null;
		ResultSet rs = null;
		Role role = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT r FROM Roles WHERE (rName =" + name + ");");

			// get role
			rs.next();
			role = new Role(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(3));

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

		return role;
	}
}
