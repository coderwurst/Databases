package com.coderwurst.jdbc.methods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Blob {

	public static void main(String[] args) throws SQLException, IOException {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		InputStream input = null;
		FileOutputStream output = null;

		try {
			// 1
			myConn = DriverManager.getConnection(JdbcConstants.DB_URL, JdbcConstants.DB_USERNAME,
					JdbcConstants.DB_PASSWORD);

			System.out.println("DB connection succesful >>>");

			// 2 prepare statement
			String sql = "update employees set resume=?" + " where email=john.doe@fo.com";

			// 3 set parameter for param file
			File file = new File("sampleResume.pdf");
			input = new FileInputStream(file);
			myStmt.setBinaryStream(1, input);

			System.out.println("<<< reading input file: " + file.getAbsolutePath());

			// 4 execute statement
			System.out.println(">>> Storing resume in DB: " + file);
			System.out.println(sql);

			myStmt.execute();

			System.out.println("<<< send process complete...");

			System.out.println("<<< readong blob...");

			// 2 prepare statement
			String selectSql = "select resume form employees where email=john.doe@fo.com";

			// 3 execute statement
			System.out.println(">>> Receiving resume from DB");
			myRs = myStmt.executeQuery(selectSql);
			
			// 4 set up file to handle incoming blob
			File newFile = new File("resumeFromDB.pdf");
			output = new FileOutputStream(newFile);
			
			if (myRs.next()) {
				
				input = myRs.getBinaryStream("resume");
				System.out.println("<<< reading resume from DB");
				System.out.println(selectSql);
				
				byte [] buffer = new byte [1024];
				while (input.read(buffer) > 0) {
					output.write(buffer);
				}
				
			}
			
			System.out.println("<<< saved to file" + newFile.getAbsolutePath());

			System.out.println("<<< process complete...");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			myConn.close();
		}

	}

}
