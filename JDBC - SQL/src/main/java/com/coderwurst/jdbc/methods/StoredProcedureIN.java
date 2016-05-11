package com.coderwurst.jdbc.methods;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class StoredProcedureIN {

	public static void main(String[] args) throws SQLException {

		Connection myConn = null;
		CallableStatement myStmt = null;

		String department = "Engineering";

		// 1. Get a connection to database
		try {
			myConn = DriverManager.getConnection(JdbcConstants.DB_URL, JdbcConstants.DB_USERNAME,
					JdbcConstants.DB_PASSWORD);

			// 2. Prepare statement
			myStmt = myConn.prepareCall("{call greet_the_department(?)}");

			// 3. Set params
			myStmt.registerOutParameter(1, Types.VARCHAR);		// used for IN/OUT
			myStmt.setString(1, department);
			
			myStmt.execute();

			String result = myStmt.getString(1);
			
			System.out.println("Result: " + result);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
