package com.coderwurst.jdbc.methods;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
	
public static void main(String[] args) {
		
		JdbcHelper helper = new JdbcHelper();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;		
		
		try {
			// 1. connect to DB
			myConn = DriverManager.getConnection(JdbcConstants.DB_URL, JdbcConstants.DB_USERNAME, JdbcConstants.DB_PASSWORD);
			
			System.out.println("DB connection succesful >>>");
			
			// 2. Create a statement
			myStmt = myConn.createStatement();
			
			System.out.println("before the delete >>>");
			helper.displayEmployee(myConn, "John", "Doe");
			
			// 3. Execute SQL Insert
			System.out.println("updating an existing employee >>>");
			int rowsAffected = myStmt.executeUpdate(
					"delete from employees " +
					"where last_name='Doe' and first_name='John' ");
						
			// display employee info
			System.out.println("after the delete <<<");
			helper.displayEmployee(myConn, "John", "Doe");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
