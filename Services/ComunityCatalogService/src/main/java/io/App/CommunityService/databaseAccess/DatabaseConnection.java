package io.App.CommunityService.databaseAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This Class was created for the purpose of facilitating changes to database
 * related data
 */
public class DatabaseConnection {

	public DatabaseConnection() {
		// Empty Constructor
	}

	/**
	 * 
	 * @return new Connection to Database
	 */
	public Connection connectToDatabase() {
		Connection con = null;
		String timezone = "?serverTimezone=UTC";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RolesUtilCommunities" + timezone, "miguel",
					"M12345");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;

	}
}
