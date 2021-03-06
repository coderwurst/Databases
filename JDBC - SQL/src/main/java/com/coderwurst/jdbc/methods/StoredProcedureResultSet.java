package com.coderwurst.jdbc.methods;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class StoredProcedureResultSet {

	public static void main(String[] args) throws SQLException {

		Connection myConn = null;
		CallableStatement myStmt = null;
		ResultSet myRs = null;

		String department = "Engineering";

		// 1. Get a connection to database
		try {
			myConn = DriverManager.getConnection(JdbcConstants.DB_URL, JdbcConstants.DB_USERNAME,
					JdbcConstants.DB_PASSWORD);

			// 2. Prepare statement
			myStmt = myConn.prepareCall("{call get_employees_for_department(?)}");

			// 3. Set params
			myStmt.setString(1, department);

			myStmt.execute();

			// 4. Get results contained in out param
			myRs = myStmt.getResultSet();
			
			
			System.out.println("Employees in Dept:");
			while (myRs.next()) {
				System.out.println(myRs.getString("last_name") + ", " + myRs.getString("first_name"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
