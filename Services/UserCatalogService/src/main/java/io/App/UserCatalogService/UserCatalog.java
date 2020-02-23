package io.App.UserCatalogService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserCatalog {

	private DatabaseConnection databaseConnection;

	public UserCatalog() {
		databaseConnection = new DatabaseConnection();
	}

	/**
	 * This method returns a list of registered users on the database
	 * 
	 * @return List of users on the database
	 */
	public UserListWrapper getUsers() {
		Connection con = databaseConnection.connectToDatabase();
		Statement stmt = null;
		ResultSet rs = null;
		UserListWrapper uLW = new UserListWrapper();
		List<User> userList = new ArrayList<User>();

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Users");
			while (rs.next()) {
				User user = new User(rs.getInt(1), rs.getString(2));
				userList.add(user);
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

		uLW.setList(userList);
		return uLW;
	}

	/**
	 * This method adds a user to the User database
	 * 
	 * @param user - the user to add
	 */
	public void addUser(User user) {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement st = null;

		try {
			st = con.prepareStatement("INSERT INTO Users (uName) VALUES ('" + user.getName() + "')");
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
	 * This method removes a user from the User Database
	 * 
	 * @param user - the user to remove
	 */
	public void removeUser(User user) {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement st1 = null;
		PreparedStatement st2 = null;

		try {
			st1 = con.prepareStatement("DELETE FROM Users WHERE uID = " + user.getId() + ";");
			st1.executeUpdate();

			st2 = con.prepareStatement("DELETE FROM RolesUsersCommunities WHERE uID = " + user.getId() + ";");
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
	 * 
	 * @param uID - Get user info based on user id
	 * @return a user with all parameters from database
	 */
	public User getUserInfo(int uID) {
		Connection con = databaseConnection.connectToDatabase();
		Statement stmt = null;
		ResultSet rs = null;
		User u = null;
		String uName = null;

		try {
			// ver se o Utilizador esta associado a uma dada communidade
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT uName FROM Users WHERE uID = " + uID + ";");

			// get next value
			rs.next();
			uName = rs.getString(1);

		} catch (Exception e) {
			System.out.println(e);
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

		u = new User(uID, uName);
		return u;
	}

}
