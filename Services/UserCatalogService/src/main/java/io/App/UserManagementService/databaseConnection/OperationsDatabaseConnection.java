package io.App.UserManagementService.databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import io.App.UserManagementService.business.Operation;
import io.App.UserManagementService.exceptions.InternalAppException;

public class OperationsDatabaseConnection {

	private static final String GET_OPERATIONS_FOR_ROLE_ID = "SELECT * FROM operations "
			+ "WHERE (role_id = ?)";

	// import class for establishing SQL connection
	private DatabaseConnection databaseConnection;

	public OperationsDatabaseConnection() {
		// TODO Auto-generated constructor stub
	}

	public List<Operation> getOperationsForRole(int roleID)
			throws InternalAppException {
		Connection con = databaseConnection.connectToDatabase();
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Operation> operations = new ArrayList<>();

		try {
			st = con.prepareStatement(GET_OPERATIONS_FOR_ROLE_ID);
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
}
