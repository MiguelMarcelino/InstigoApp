package io.App.CommunityService.databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import io.App.CommunityService.communityComponent.Operation;
import io.App.CommunityService.exceptions.InternalAppException;

public class OperationsDatabaseConnection {

	// SQL Queries
	private static final String GET_ALL_OPERATIONS_SQL = "SELECT * FROM operations";

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
}
