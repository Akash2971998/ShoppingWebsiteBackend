package com.edu.repository;

import java.sql.*;

public class DatabaseConnection {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/amazon";
	private static String user = "root";
	private static String pass = "root1234";
	
	static Connection conn;
	
	public static Connection getDatabaseConnection() throws SQLException, ClassNotFoundException {
		
		Class.forName(driver);
		conn = DriverManager.getConnection(url,user,pass);
		
		if(conn==null)
			System.out.println("Connection failed");
		return conn;
		
	}
}
