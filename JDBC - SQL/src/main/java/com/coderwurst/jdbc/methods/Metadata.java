package com.coderwurst.jdbc.methods;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Metadata {

	public static void main(String[] args) throws SQLException {

		Connection myConn = null;
		String catalog = null;
		String schemaPattern = null;
		String tableNamePattern = null;
		String columnNamePattern = null;
		String[] types = null;

		ResultSet results = null;

		try {
			myConn = DriverManager.getConnection(JdbcConstants.DB_URL, JdbcConstants.DB_USERNAME,
					JdbcConstants.DB_PASSWORD);

			System.out.println("DB connection succesful >>>");

			DatabaseMetaData meta = myConn.getMetaData();

			// db info
			System.out.println("Product name: " + meta.getDatabaseProductName());
			System.out.println("Product version: " + meta.getDatabaseProductVersion());

			// driver info
			System.out.println("Driver name: " + meta.getDriverName());
			System.out.println("Driver version: " + meta.getDriverVersion());

			// list of tables
			System.out.println("/n<<< List of Tables");

			results = meta.getTables(catalog, schemaPattern, tableNamePattern, types);

			while (results.next()) {
				System.out.println(results.getString("TABLE_NAME"));
			}

			// list of columns
			System.out.println("/n<<< List of Columns in Employees");

			results = meta.getColumns(catalog, schemaPattern, "employees", columnNamePattern);

			while (results.next()) {
				System.out.println(results.getString("COLUMN_NAME"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			myConn.close();
		}

	}

}
