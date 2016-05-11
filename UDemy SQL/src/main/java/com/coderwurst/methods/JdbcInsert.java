package com.coderwurst.methods;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcInsert {
	
	public static void main(String[] args) {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;	
		
		try {
			// 1. connect to DB
			myConn = DriverManager.getConnection(JdbcConnConstants.DB_URL, JdbcConnConstants.DB_USERNAME, JdbcConnConstants.DB_PASSWORD);
			
			System.out.println("DB connection succesful >>>");
			
			// 2. Create a statement
			myStmt = myConn.createStatement();
			
			// 3. Execute SQL Insert
			System.out.println("inserting a new employee >>>");
			int rowsAffected = myStmt.executeUpdate(
					"insert into employees" +
					"(last_name, first_name, email, department, salary) " +
					"values " +
					"('Doe', 'John', 'john.doe@mail.com', 'HR', 55000.00)");
			
			myRs = myStmt.executeQuery("select * from employees order by last_name");
			
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