package io.App.UserManagementService.databaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import io.App.UserManagementService.dto.UserListWrapper;
import io.App.UserManagementService.exceptions.InternalAppException;
import io.App.UserManagementService.exceptions.UserAlreadyExistsException;
import io.App.UserManagementService.userComponent.User;

public class UserDatabaseConnection {

	// import class for establishing SQL connection
	private DatabaseConnection databaseConnection;

	// SQL Queries
	private static final String GET_ALL_USERS_SQL = "SELECT * FROM Users";
	private static final String INSERT_USER_SQL = "INSERT INTO Users (uName, firstName, lastName, uEmail, uPassword) VALUES (?, ?, ?, ?, ?)";
	private static final String ADD_USER_ROLE_AND_COMMUNITY_SQL = "INSERT INTO RolesUsersCommunities (uID, cID, rID, dStart, dEnd) "
			+ "VALUES (?, ?, ?, ?, ?)";
	private static final String DELETE_USER_FROM_USER_TABLE_SQL = "DELETE FROM Users WHERE uID = ?;";
	private static final String DELETE_USER_FROM_ROLESUSERSCOMMUNITIES_SQL = "DELETE FROM RolesUsersCommunities WHERE uID = ?;";
	private static final String SELECT_USER_BY_ID = "SELECT uName FROM Users WHERE uID = ?;";
	private static final String SELECT_USER_BY_NAME = "SELECT * FROM Users WHERE uName = ?;";

	public UserDatabaseConnection() {
		databaseConnection = new DatabaseConnection();
	}

	/**
	 * This method returns a list of registered users on the database
	 * 
	 * @return List of users on the database
	 */
	public UserListWrapper getUsersFromDatabase() {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement st = null;
		ResultSet rs = null;
		UserListWrapper uLW = new UserListWrapper();
		ArrayList<User> userList = new ArrayList<User>();

		try {
			st = con.prepareStatement(GET_ALL_USERS_SQL);
			rs = st.executeQuery();
			while (rs.next()) {
				User user = new User(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6));
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

		uLW.setList(userList);
		return uLW;
	}

	/**
	 * This method adds a user to the User database
	 * 
	 * @param user - the user to add
	 * @throws UserAlreadyExistsException
	 * @throws InternalAppException
	 */
	public void addUser(User user)
			throws UserAlreadyExistsException, InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement st = null;
		PreparedStatement st2 = null;
		ResultSet rs = null;

		try {
			st = con.prepareStatement(INSERT_USER_SQL,
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, user.getUserName());
			st.setString(2, user.getFirstName());
			st.setString(3, user.getLastName());
			st.setString(4, user.getEmail());
			st.setString(5, user.getPassword());
			st.executeUpdate();

			// Get newly generated id
			rs = st.getGeneratedKeys();
			rs.next();
			int newUserID = rs.getInt(1);

			// Insert user into "ALL_USER_COMMUNITY" (cID=1) community with
			// "DEFAULT_ROLE"
			// (rID=1)
			// default role time will be 1 year. After that it will have to be
			// renewed
			int cID = 1;
			int rID = 1;
			LocalDate dateStart = LocalDate.now();
			LocalDate dateEnd = dateStart.plusYears(1);
			st2 = con.prepareStatement(ADD_USER_ROLE_AND_COMMUNITY_SQL);
			st2.setInt(1, newUserID);
			st2.setInt(2, cID);
			st2.setInt(3, rID);
			st2.setDate(4, Date.valueOf(dateStart));
			st2.setDate(5, Date.valueOf(dateEnd));
			st2.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			throw new UserAlreadyExistsException();
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
			if (st2 != null) {
				try {
					st2.close();
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
	public void removeUser(User user) throws InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement st1 = null;
		PreparedStatement st2 = null;

		try {
			st1 = con.prepareStatement(DELETE_USER_FROM_USER_TABLE_SQL);
			st1.setInt(1, user.getId());
			st1.executeUpdate();

			st2 = con.prepareStatement(
					DELETE_USER_FROM_ROLESUSERSCOMMUNITIES_SQL);
			st2.setInt(1, user.getId());
			st2.executeUpdate();
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
	 * @throws InternalAppException
	 */
	public User getUserInfo(int uID) throws InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement st = null;
		ResultSet rs = null;
		User u = null;
		String uName = null;
		String uFirstName = null;
		String uLastName = null;
		String uEmail = null;
		String uPassword = null;

		try {
			// ver se o Utilizador esta associado a uma dada communidade
			st = con.prepareStatement(SELECT_USER_BY_ID);
			st.setInt(1, uID);
			rs = st.executeQuery();

			// get next value
			rs.next();
			uName = rs.getString(2);
			uFirstName = rs.getString(3);
			uLastName = rs.getString(4);
			uEmail = rs.getString(5);
			uPassword = rs.getString(6);

		} catch (Exception e) {
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

		u = new User(uID, uName, uFirstName, uLastName, uEmail, uPassword);
		return u;
	}

	/**
	 * 
	 * @param name
	 * @return
	 * @throws InternalAppException
	 */
	public User getUserByName(String name) throws InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement st = null;
		ResultSet rs = null;
		User u = null;

		try {
			st = con.prepareStatement(SELECT_USER_BY_NAME);
			st.setString(1, name);
			rs = st.executeQuery();

			// get user
			rs.next();
			u = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5), rs.getString(6));

		} catch (Exception e) {
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

		return u;
	}

}
