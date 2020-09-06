package io.App.UserManagementService.databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import io.App.UserManagementService.exceptions.InternalAppException;
import io.App.UserManagementService.exceptions.UserAlreadyExistsException;
import io.App.UserManagementService.exceptions.UserDoesNotExistException;
import io.App.UserManagementService.userComponent.Role;
import io.App.UserManagementService.userComponent.User;

public class UserDatabaseConnection {

	// import class for establishing SQL connection
	private DatabaseConnection databaseConnection;

	// SQL Queries
	private static final String GET_ALL_USERS_SQL = "SELECT u.id, u.user_name, u.first_name, u.last_name, u.email,"
			+ "r.id, r.name FROM users u "
			+ "INNER JOIN roles r ON (role.id = u.role_id);";
	private static final String CREATE_USER_SQL = "INSERT INTO users (user_name, first_name, last_name, role_id, email, "
			+ "password) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String DELETE_USER_SQL = "DELETE FROM Users WHERE id = ?;";
	private static final String GET_USER_BY_ID_SQL = "SELECT u.id, u.user_name, u.first_name, u.last_name, u.email,"
			+ "r.id, r.name FROM users u "
			+ "INNER JOIN roles r ON (r.id = u.role_id)" + "WHERE u.id = ?;";
	private static final String GET_USER_BY_NAME_SQL = "SELECT u.id, u.user_name, u.first_name, u.last_name, u.email,"
			+ "r.id, r.name FROM users u "
			+ "INNER JOIN roles r ON (r.id = u.role_id)" + "WHERE u.name = ?";

	public UserDatabaseConnection() {
		databaseConnection = new DatabaseConnection();
	}

	/**
	 * This method returns a list of registered users on the database
	 * 
	 * @return List of users on the database
	 * @throws InternalAppException
	 */
	public List<User> getUsersFromDatabase() throws InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement st = null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<User>();

		try {
			st = con.prepareStatement(GET_ALL_USERS_SQL);
			rs = st.executeQuery();
			while (rs.next()) {
				Role role = new Role(rs.getInt(6), rs.getString(7));
				User user = new User(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						role);
				userList.add(user);
			}
		} catch (SQLException e) {
			throw new InternalAppException();
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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return userList;
	}

	/**
	 * This method adds a user to the User database
	 * 
	 * @param user - the user to add
	 * @throws UserAlreadyExistsException
	 * @throws InternalAppException
	 */
	public void addUser(User user) throws InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = con.prepareStatement(CREATE_USER_SQL);
			st.setString(1, user.getUserName());
			st.setString(2, user.getFirstName());
			st.setString(3, user.getLastName());
			st.setString(4, user.getRole().getName());
			st.setString(5, user.getEmail());
			// protect password with hash and salt
			st.setString(6, user.getPassword());
			st.executeUpdate();

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new InternalAppException();
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
			if (rs != null) {
				try {
					rs.close();
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
	 * @throws InternalAppException
	 */
	public void removeUser(int userId) throws InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement st1 = null;

		try {
			st1 = con.prepareStatement(DELETE_USER_SQL);
			st1.setInt(1, userId);
			st1.executeUpdate();

			// Because we use ON DELETE CASCADE there is no need to delete
			// The user from the table user_subscribed_communities
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new InternalAppException();
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
		}

	}

	/**
	 * This method gets a user from the database by its id
	 * 
	 * @param uID - Get user info based on user id
	 * @return a user with all parameters from database
	 * @throws InternalAppException
	 * @throws UserDoesNotExistException
	 */
	public User getUserById(int uID)
			throws InternalAppException, UserDoesNotExistException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement st = null;
		ResultSet rs = null;
		User user = null;
		try {
			st = con.prepareStatement(GET_USER_BY_ID_SQL);
			st.setInt(1, uID);
			rs = st.executeQuery();

			if (rs.next()) {
				Role role = new Role(rs.getInt(6), rs.getString(7));
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), role);
			} else {
				throw new UserDoesNotExistException();
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new InternalAppException();
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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return user;
	}

	/**
	 * 
	 * @param name
	 * @return
	 * @throws InternalAppException
	 * @throws UserDoesNotExistException
	 */
	public User getUserByName(String name)
			throws InternalAppException, UserDoesNotExistException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement st = null;
		ResultSet rs = null;
		User user = null;

		try {
			st = con.prepareStatement(GET_USER_BY_NAME_SQL);
			st.setString(1, name);
			rs = st.executeQuery();

			// get user
			if (rs.next()) {
				Role role = new Role(rs.getInt(6), rs.getString(7));
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), role);
			} else {
				throw new UserDoesNotExistException();
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new InternalAppException();
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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return user;
	}

}
