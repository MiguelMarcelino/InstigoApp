package io.App.RoleCatalogService.databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import io.App.RoleCatalogService.dto.RoleListWrapper;
import io.App.RoleCatalogService.roleComponent.Role;

public class RoleDatabaseConnection {

	// import class for establishing SQL connection
	private DatabaseConnection databaseConnection;

	// SQL Queries
	private static final String GET_ALL_ROLES_SQL = "SELECT * FROM Roles;";
	private static final String INSERT_ROLE_SQL = "INSERT INTO Roles (rName, cID, authLevel) VALUES (?, ?, ?)";
	private static final String DELETE_ROLE_SQL = "DELETE FROM Roles WHERE rID = ?;";
	private static final String DELETE_ROLE_FROM_ROLESUSERSCOMMUNITIES_SQL = "DELETE FROM RolesUsersCommunities WHERE rID = ?;";
	private static final String GET_ROLE_BY_NAME_SQL = "SELECT * FROM Roles WHERE (rName = ?);";

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
		PreparedStatement stmt = null;
		ResultSet rs = null;
		RoleListWrapper rLW = new RoleListWrapper();
		List<Role> roleList = new ArrayList<Role>();

		try {
			stmt = con.prepareStatement(GET_ALL_ROLES_SQL);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Role role = new Role(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(3));
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
			st = con.prepareStatement(INSERT_ROLE_SQL);
			st.setInt(2, role.getCommunityID());
			st.setInt(3, role.getAuthLevel());
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
			st1 = con.prepareStatement(DELETE_ROLE_SQL);
			st1.setInt(1, role.getId());
			st1.executeUpdate();

			st2 = con.prepareStatement(DELETE_ROLE_FROM_ROLESUSERSCOMMUNITIES_SQL);
			st2.setInt(1, role.getId());
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
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Role role = null;

		try {
			stmt = con.prepareStatement(GET_ROLE_BY_NAME_SQL);
			stmt.setString(1, name);
			rs = stmt.executeQuery();

			// get role
			rs.next();
			role = new Role(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(3));
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
