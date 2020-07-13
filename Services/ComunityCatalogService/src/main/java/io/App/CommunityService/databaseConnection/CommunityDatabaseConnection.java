package io.App.CommunityService.databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import io.App.CommunityService.communityComponent.Community;
import io.App.CommunityService.dto.CommunityListWrapper;
import io.App.CommunityService.exceptions.CommunityAlreadyExistsException;
import io.App.CommunityService.exceptions.CommunityDoesNotExistException;
import io.App.CommunityService.exceptions.InternalAppException;

public class CommunityDatabaseConnection {

	// import class for establishing SQL connection
	private DatabaseConnection databaseConnection;

	// SQL Queries
	private static final String GET_ALL_COMMUNITIES_SQL = "SELECT * FROM Communities";
	private static final String INSERT_COMMUNITY_SQL = "INSERT INTO Communities (cName) VALUES (?)";
	private static final String DELETE_COMMUNITY_SQL = "DELETE FROM Communities WHERE cName = ?";
	// private static final String DELETE_COMMUNITY_FROM_ROLESUSERSCOMMUNITIES_SQL =
	// "DELETE FROM RolesUsersCommunities WHERE cID2 = ?;";
	private static final String GET_COMMUNITY_BY_ID = "SELECT * FROM Communities WHERE cID = ?";
	private static final String GET_COMMUNITY_BY_NAME = "SELECT * FROM Communities WHERE cName = ?";;

	public CommunityDatabaseConnection() {
		databaseConnection = new DatabaseConnection();
	}

	/**
	 * This method return a list of all communities found on the database
	 * 
	 * @return a list of all communities found on the database
	 */
	public CommunityListWrapper getCommunityDatabaseList() throws InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Community> communityList = new ArrayList<Community>();
		CommunityListWrapper cLW = new CommunityListWrapper();

		try {
			stmt = con.prepareStatement(GET_ALL_COMMUNITIES_SQL);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Community community = new Community(rs.getInt(1), rs.getString(2));
				communityList.add(community);
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
	 * @param community
	 *            - the community to add
	 * @throws CommunityAlreadyExistsException
	 */
	public void addCommunityToDatabase(Community community)
			throws CommunityAlreadyExistsException, InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement st = null;

		try {
			st = con.prepareStatement(INSERT_COMMUNITY_SQL);
			st.setString(1, community.getName());
			st.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e1) {
			throw new CommunityAlreadyExistsException(community.getName());
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
		}

	}

	/**
	 * This method remove a community from the Communities Database
	 * 
	 * @param community
	 *            - the community to remove
	 * @throws CommunityDoesNotExistException
	 */
	public void removeCommunityFromDatabase(Community community) throws InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement st1 = null;
		PreparedStatement st2 = null;

		try {
			st1 = con.prepareStatement(DELETE_COMMUNITY_SQL);
			st1.setString(1, community.getName());
			st1.executeUpdate();

			// There is no need to delete the community on RolesUsersCommunities table
			// since it has ON_DELETE_CASCADE
			// st2 = con.prepareStatement(DELETE_COMMUNITY_FROM_ROLESUSERSCOMMUNITIES_SQL);
			// st2.setInt(1, community.getId());
			// st2.executeUpdate();
		} catch (SQLException e) {
			// TODO Review this
			System.err.println(e.getMessage());
			throw new InternalAppException();
			// throw new CommunityDoesNotExistException();
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
	 * @param id
	 *            - the id of the community to get the name
	 * @return the name of the given community id
	 * @throws CommunityDoesNotExistException
	 */
	public Community getCommunityById(int id) throws InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Community c = null;

		try {
			stmt = con.prepareStatement(GET_COMMUNITY_BY_ID);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			rs.next();
			c = new Community(rs.getInt(1), rs.getString(2));
		} catch (SQLException e) {
			// TODO Review this
			System.err.println(e.getMessage());
			throw new InternalAppException();
			// throw new CommunityDoesNotExistException();
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

	public Community getCommunityByName(String cName) throws InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Community c = null;

		try {
			stmt = con.prepareStatement(GET_COMMUNITY_BY_NAME);
			stmt.setString(1, cName);
			rs = stmt.executeQuery();
			rs.next();
			c = new Community(rs.getInt(1), rs.getString(2));
		} catch (SQLException e) {
			// TODO Review this
			System.err.println(e.getMessage());
			throw new InternalAppException();
			// throw new CommunityDoesNotExistException();
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
