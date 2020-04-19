package com.caremarque.appointment.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	// If there is any issue you can change it accordingly
	// Implemented with Singleton Pattern

	private static Connection connection;

	private DBConnection() {

	}

	public static Connection getDBConnection() throws ClassNotFoundException, SQLException {

		if (connection == null || connection.isClosed()) {

			Class.forName(Constants.DB_DRIVER_NAME);

			connection = DriverManager.getConnection(Constants.DBLOCATION_STRING, Constants.DB_USERNAME,
					Constants.DB_PASSWORD);

			System.out.println("Connected to DB");
		}

		return connection;
	}


}
