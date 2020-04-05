package com.caremarque.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	// TODO:Implement DB class with phpmyadmin
	
	private static Connection connection;
	
	private DBConnection() {
		
	}
	
	public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
		
		if(connection==null ||connection.isClosed()) {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "root");
			
			System.out.println("Connected to DB");
		}
		
		return connection;
	}
	

//	public static Connection connect() {
//
//		Connection con = null;
//
//		try {
//
//			Class.forName("com.mysql.cj.jdbc.Driver");
//
//			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "root");
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//
//		return con;
//
//	}
	
}
