package com.caremarque.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	//TODO:Implement DB class with phpmyadmin
	
public static Connection connect() {
		
		Connection con = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "root");
			
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return con;
		
	}
}
