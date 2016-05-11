package com.coderwurst.jdbc.methods;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class StoredProperties {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		Connection myConn = null;
		
		try {
			System.out.println("connecting to DB >>>");
			// 1. load properties file
			Properties props = new Properties();
			props.load(new FileInputStream("demo.properties"));
			// props.load(new FileInputStream (../..);
			
			// 2. read the props
			String user = props.getProperty("user");
			System.out.println("user: " + user);
			String password = props.getProperty("password");
			String url = props.getProperty("url");
			System.out.println("url: " + url);
			
			DriverManager.getConnection(url, user, password);
			
			System.out.println("DB connection succesful >>>");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
