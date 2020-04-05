package com.caremarque.service.patient;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import com.caremarque.utils.DBConnection;

public class PatientService {

	public String registerPatient(String firstName, String lastName, String gender, String NIC, Date DOB, String email,
			String phone, String bloodGroup, String allergies, String password, String cPassword) {

		String output = "";
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {

			con = DBConnection.getDBConnection();

			if (con == null) {
				return "Error while connecting to the database for inserting..!";
			}

			String query = "INSERT INTO patient(firstName, lastName, gender, NIC, DOB, email, phone, bloodGroup, allergies, password, cPassword) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			preparedStatement = con.prepareStatement(query);

			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, gender);
			preparedStatement.setString(4, NIC);
			preparedStatement.setDate(5, DOB);
			preparedStatement.setString(6, email);
			preparedStatement.setString(7, phone);
			preparedStatement.setString(8, bloodGroup);
			preparedStatement.setString(9, allergies);
			preparedStatement.setString(10, password);
			preparedStatement.setString(11, cPassword);

			preparedStatement.execute();
			con.close();

			output = "Inserted successfully";

		} catch (Exception e) {
			
			output = "Error while inserting the item..!";
			System.err.println(e.getMessage());
		}

		return output;

	}
}
