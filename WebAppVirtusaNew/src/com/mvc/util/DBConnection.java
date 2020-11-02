package com.mvc.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection createConnection() {
		// Connection and access values for SQL Database
		Connection con = null;
		// url and name of database
		String url = "jdbc:mysql://localhost:3306/virtusa";
		// username
		String username = "root";
		// password
		String password = "root";
		try {
			try {
				// loading mysql driver
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			//connecting to the database
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Printing connection object " + con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}