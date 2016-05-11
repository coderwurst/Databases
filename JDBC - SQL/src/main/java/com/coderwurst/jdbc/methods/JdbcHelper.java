package com.coderwurst.jdbc.methods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcHelper {

	String dbUrl = "jdbc:mysql://localhost:3306/demo";
	String username = "student";
	String password = "student";

	Statement myStmt = null;
	ResultSet myRs = null;

	public void displayEmployee(Connection myConn, String firstName, String lastName) throws SQLException {

		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery(
				"select * from employees where last_name='" + lastName + "' and first_name='" + firstName + "'");

		while (myRs.next()) {
			System.out.println("Employee: " + myRs.getString("last_name") + ", " + myRs.getString("first_name") + ", "
					+ myRs.getString("email") + ", " + myRs.getString("department") + ", " + myRs.getString("salary"));
		}
	}

	public void display(ResultSet myRs) throws SQLException {
		while (myRs.next()) {
			String lastName = myRs.getString("last_name");
			String firstName = myRs.getString("first_name");
			double salary = myRs.getDouble("salary");
			String department = myRs.getString("department");

			System.out.printf("%s, %s, %.2f, %s\n", lastName, firstName, salary, department);
		}
	}

	public void showSalaries(Connection myConn, String department) throws SQLException {

		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery(
				"select * from employees where department='" + department + "'");

		while (myRs.next()) {
			System.out.println("Employee: " + myRs.getString("department") + ", " + myRs.getString("salary"));
		}
	}

	@SuppressWarnings("resource")
	public boolean askUserToSave() {
		
		boolean action = false;
		
		System.out.println("Save changes and proceed?");
		Scanner reader = new Scanner(System.in);
		String response = reader.next();
		
		if (response.startsWith("y")) {
			action = true;
		}
		
		return action;
	}
	
}
