package io.App.CommunityService.databaseAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import io.App.CommunityService.business.Operation;
import io.App.CommunityService.business.exceptions.InternalAppException;
import io.App.CommunityService.business.exceptions.NonExistantOperationException;

public class OperationsDatabaseConnection {

	// SQL Queries
	private static final String GET_ALL_OPERATIONS_SQL = "SELECT * FROM operations";
	private static final String GET_OPERATIONS_FOR_ROLE_ID_SQL = "SELECT * FROM operations "
			+ "WHERE (role_id = ?)";
	private static final String GET_OPERATION_BY_NAME_SQL = "SELECT * FROM operations "
			+ "WHERE (name = ?)";

	// import class for establishing SQL connection
	private DatabaseConnection databaseConnection;

	public OperationsDatabaseConnection() {
		this.databaseConnection = new DatabaseConnection();
	}

	public List<Operation> getAllOperations() throws InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Operation> operationList = new ArrayList<>();

		try {
			stmt = con.prepareStatement(GET_ALL_OPERATIONS_SQL);
			rs = stmt.executeQuery();
			while (rs.next()) {
				operationList.add(new Operation(rs.getInt(1), rs.getString(2),
						rs.getInt(3)));
			}
		} catch (SQLException e) {
			throw new InternalAppException(e.getMessage());
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

		return operationList;
	}

	public List<Operation> getOperationsForRoleID(int roleID)
			throws InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Operation> operations = new ArrayList<>();

		try {
			st = con.prepareStatement(GET_OPERATIONS_FOR_ROLE_ID_SQL);
			st.setInt(1, roleID);
			rs = st.executeQuery();

			// get operations
			while (rs.next()) {
				operations.add(new Operation(rs.getInt(1), rs.getString(2),
						rs.getInt(3)));
			}

		} catch (SQLException e) {
			throw new InternalAppException(e.getMessage());
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
		return operations;
	}

	public Operation getOperationByName(String name)
			throws InternalAppException, NonExistantOperationException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement st = null;
		ResultSet rs = null;
		Operation operation = null;

		try {
			st = con.prepareStatement(GET_OPERATION_BY_NAME_SQL);
			st.setString(1, name);
			rs = st.executeQuery();

			// get operation
			if (rs.next()) {
				operation = new Operation(rs.getInt(1), rs.getString(2),
						rs.getInt(3));
			} else {
				throw new NonExistantOperationException();
			}

		} catch (SQLException e) {
			throw new InternalAppException(e.getMessage());
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
		return operation;
	}

}
