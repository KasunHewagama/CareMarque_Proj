package com.caremarque.service.patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.caremarque.model.Patient;
import com.caremarque.utils.DBConnection;

public class PatientService {

//	public String registerPatient(String firstName, String lastName, String gender, String NIC, Date DOB, String email,
//			String phone, String bloodGroup, String allergies, String password, String cPassword) {
//
//		String output = "";
//		Connection con = null;
//		PreparedStatement preparedStatement = null;
//
//		try {
//
//			con = DBConnection.getDBConnection();
//
//			if (con == null) {
//				return "Error while connecting to the database for inserting..!";
//			}
//
//			String query = "INSERT INTO patient(firstName, lastName, gender, NIC, DOB, email, phone, bloodGroup, allergies, password, cPassword) "
//					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//			preparedStatement = con.prepareStatement(query);
//
//			preparedStatement.setString(1, firstName);
//			preparedStatement.setString(2, lastName);
//			preparedStatement.setString(3, gender);
//			preparedStatement.setString(4, NIC);
//			preparedStatement.setDate(5, DOB);
//			preparedStatement.setString(6, email);
//			preparedStatement.setString(7, phone);
//			preparedStatement.setString(8, bloodGroup);
//			preparedStatement.setString(9, allergies);
//			preparedStatement.setString(10, password);
//			preparedStatement.setString(11, cPassword);
//
//			preparedStatement.executeUpdate();
//			con.close();
//
//			output = "Inserted successfully";
//
//		} catch (Exception e) {
//			
//			output = "Error while inserting the item..!";
//			System.err.println(e.getMessage());
//		}
//
//		return output;
//
//	}
	
	public String registerPatient(Patient patient) {

		String output = "";
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			con = DBConnection.getDBConnection();

			String query = "INSERT INTO patient(firstName, lastName, gender, NIC, DOB, email, phone, bloodGroup, allergies, password, cPassword) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			preparedStatement = con.prepareStatement(query);

			preparedStatement.setString(1, patient.getFirstName());
			preparedStatement.setString(2, patient.getLastName());
			preparedStatement.setString(3, patient.getGender());
			preparedStatement.setString(4, patient.getNIC());
			preparedStatement.setString(5, patient.getDOB());
			preparedStatement.setString(6, patient.getEmail());
			preparedStatement.setString(7, patient.getPhone());
			preparedStatement.setString(8, patient.getBloodGroup());
			preparedStatement.setString(9, patient.getAllergy());
			preparedStatement.setString(10, patient.getPassword());
			preparedStatement.setString(11, patient.getConfirmPassword());

			preparedStatement.executeUpdate();

			output = "Inserted successfully";

		} catch (Exception e) {

			output = "Error while inserting the item..!";
			System.err.println(e.getMessage());
			
		}finally {
			
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				
				if(con != null) {
					con.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

		return output;

	}

}
