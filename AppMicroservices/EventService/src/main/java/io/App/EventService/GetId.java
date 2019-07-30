package io.App.EventService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetId {

	public GetId() {
		// TODO Auto-generated constructor stub
	}

	public int getTableId(Connection con, String DatabaseName, String tableName) throws SQLException {
		int id = 0;

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE (TABLE_SCHEMA = '"
				+ DatabaseName + "') AND (TABLE_NAME = '" + tableName + "');");

		while (rs.next()) {
			id = rs.getInt(1);
		}

		return id;

	}
}
