package com.coderwurst.jdbc.methods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Transaction {

	public static void main(String[] args) {
		Connection myConn = null;
		Statement myStmt = null;
		JdbcHelper helper = new JdbcHelper();

		// 1. connect to DB
		try {
			myConn = DriverManager.getConnection(JdbcConstants.DB_URL, JdbcConstants.DB_USERNAME,
					JdbcConstants.DB_PASSWORD);

			System.out.println("DB connection succesful >>>");

			myConn.setAutoCommit(false); // deactivate default auto commit
											// implementation

			// show salaries before
			System.out.println("<<< Salaries before");
			helper.showSalaries(myConn, "HR");
			helper.showSalaries(myConn, "Engineering");

			// Trans step 1 - delete all HR Employees
			myStmt = myConn.createStatement();
			myStmt.executeUpdate("delete from employees where department='HR'");

			// Trans step 2 - set engineer salaries to 300000 for all
			// Engineering employees
			myStmt.executeUpdate("update employees set salary=300000 where department='Engineering'");

			System.out.println(">>> Trans steps ready");

			// ask user if ok to save
			boolean ok = helper.askUserToSave();

			if (ok) {
				// store in db
				// myConn.commit();
				myConn.rollback();		// disabled for testing
				System.out.println(">>> Transaction COMMITTED");
			} else {
				// discard changes
				myConn.rollback();
				System.out.println(">>> Transaction ROLLED BACK");
			}

			// show salaries after
			System.out.println("<<< Salaries after");
			helper.showSalaries(myConn, "HR");
			helper.showSalaries(myConn, "Engineering");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
