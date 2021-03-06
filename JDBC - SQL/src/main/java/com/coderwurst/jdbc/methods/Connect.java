package com.coderwurst.jdbc.methods;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {

	public static void main(String[] args) {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;		
		
		try {
			// 1. connect to DB
			myConn = DriverManager.getConnection(JdbcConstants.DB_URL, JdbcConstants.DB_USERNAME, JdbcConstants.DB_PASSWORD);
			
			System.out.println("DB connection succesful >>>");
			
			// 2. Create a statement
			myStmt = myConn.createStatement();
			
			// 3. Execute SQL Query
			myRs = myStmt.executeQuery("select * from employees");
			
			// 4. Process Result set
			while (myRs.next()) {
				System.out.println(myRs.getString("last_name") + ", " + myRs.getString("first_name"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
