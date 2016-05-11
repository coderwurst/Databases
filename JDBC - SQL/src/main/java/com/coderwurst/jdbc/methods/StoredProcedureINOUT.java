package com.coderwurst.jdbc.methods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoredProcedureINOUT {

	public static void main(String[] args) throws SQLException {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		JdbcHelper helper = new JdbcHelper();

		String department = "Engineering";
		int increase = 10000;

		// 1. Get a connection to database
		try {
			myConn = DriverManager.getConnection(JdbcConstants.DB_URL, JdbcConstants.DB_USERNAME,
					JdbcConstants.DB_PASSWORD);

			// 2. Prepare statement
			myStmt = myConn.prepareCall("{call increase_salaries_for_department(?, ?)}");

			// show salaries before
			System.out.println("<<< salaries before");
			helper.showSalaries(myConn, department);

			// 3. Set params
			myStmt.setString(1, department);
			myStmt.setDouble(2, increase);
			
			System.out.println(">>> calling stored procedure");
			myStmt.execute();
			System.out.println("<<< finished calling stored procedure");

			// show salaries after
			System.out.println("<<< salaries after");
			helper.showSalaries(myConn, department);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
