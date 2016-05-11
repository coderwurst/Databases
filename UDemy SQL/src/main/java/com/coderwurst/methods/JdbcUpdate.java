package com.coderwurst.methods;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUpdate {
	
	public static void main(String[] args) {
		
		JdbcHelper helper = new JdbcHelper();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;		
		
		try {
			// 1. connect to DB
			myConn = DriverManager.getConnection(JdbcConnConstants.DB_URL, JdbcConnConstants.DB_USERNAME, JdbcConnConstants.DB_PASSWORD);
			
			System.out.println("DB connection succesful >>>");
			
			// 2. Create a statement
			myStmt = myConn.createStatement();
			
			System.out.println("before the update >>>");
			helper.displayEmployee(myConn, "John", "Doe");
			
			// 3. Execute SQL Insert
			System.out.println("updating an existing employee >>>");
			int rowsAffected = myStmt.executeUpdate(
					"update employees " +
					"set email ='john.doe@work.com' " +
					"where last_name='Doe' and first_name='John' ");
						
			// display employee info
			System.out.println("after the update <<<");
			helper.displayEmployee(myConn, "John", "Doe");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
