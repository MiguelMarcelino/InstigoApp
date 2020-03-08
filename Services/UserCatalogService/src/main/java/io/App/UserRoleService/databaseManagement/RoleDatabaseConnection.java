package io.App.UserRoleService.databaseManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.UserRoleService.databaseManagement.DatabaseConnection;
import io.App.UserRoleService.dto.RoleListWrapper;
import io.App.UserRoleService.roleComponent.Operation;
import io.App.UserRoleService.roleComponent.Role;

@SpringBootApplication
public class RoleDatabaseConnection {

	private DatabaseConnection databaseConnection;

	public RoleDatabaseConnection() {
		databaseConnection = new DatabaseConnection();
	}

	/**
	 * This method returns the list of Roles on the database
	 * 
	 * @return a list of roles
	 */
	public RoleListWrapper getRoleList() {
		Connection con = databaseConnection.connectToDatabase();
		Statement stmt = null;
		ResultSet rs = null;
		RoleListWrapper rLW = new RoleListWrapper();
		ArrayList<Role> roleList = new ArrayList<Role>();

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Roles;");
			while (rs.next()) {
				
				/*
				 * Operations in the database have the following format
				 * opAuthLevel:opDescription;opAuthLevel:opDescription ...
				 */
				String allOpps = rs.getString(3);
				String[] rest = allOpps.split(";");
				ArrayList<Operation> operations = new ArrayList<Operation>();
				String[] temp = null;
				for (int i = 0; i < rest.length; i++) {
					temp = rest[i].split(":");
					operations.add(new Operation(Integer.parseInt(temp[0]), temp[1]));
				}
				Role role = new Role(rs.getInt(1), rs.getString(2), operations);
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
	public void addRole(Role role) {
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
	public void removeRole(Role role) {
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
}
