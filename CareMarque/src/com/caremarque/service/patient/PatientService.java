package com.caremarque.service.patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.caremarque.model.Patient;
//import com.caremarque.model.Patient;
import com.caremarque.utils.DBConnection;

public class PatientService implements IPatientService {

//	public String getPatient()
//	{
//		String output = "";
//		
//		try
//		{
//			Connection con = DBConnection.getDBConnection();
//			
//			String query = "SELECT * FROM patient";			
//			Statement st = con.createStatement();			
//			ResultSet rs = st.executeQuery(query);
//			
//			output = "<table border=\"1\"> "
//					+ "<tr><th>firstName</th> "
//					+ "<th>lastName</th> "
//					+ "<th>gender</th> "
//					+ "<th>NIC</th></tr>"; 
//			
//			while(rs.next())
//			{
//				
//				String firstName = rs.getString("firstName");
//				String lastName = rs.getString("lastName");
//				String gender = rs.getString("gender");
//				String NIC = rs.getString("NIC");
//				String DOB = rs.getDate("DOB").toString();
//				String email = rs.getString("email");
//				String phone = rs.getString("phone");
//				String bloodGroup = rs.getString("bloodGroup");
//				String allergies = rs.getString("allergies");
//				String password = rs.getString("password");
//				String cPassword = rs.getString("cPassword");
//				
//				 output += "<tr><td>" + firstName + "</td>";
//				 output += "<td>" + lastName + "</td>";
//				 output += "<td>" + gender + "</td>";
//				 output += "<td>" + NIC + "</td>"; 
//				
//			}
//			// Complete the html table
//			 output += "</table>"; 
//			con.close();
//			
//		}catch (Exception e)
//		{
//			//output = "Error while reading the patient details...!";
//			System.err.println(e.getMessage());
//		}
//		
//		return output;
//		
//	}

	/*
	 * public String registerPatient(String firstName, String lastName, String
	 * gender, String NIC, String DOB, String email, String phone, String
	 * bloodGroup, String allergies, String password, String cPassword) {
	 * 
	 * String output = ""; Connection con = null; PreparedStatement
	 * preparedStatement = null;
	 * 
	 * try {
	 * 
	 * con = DBConnection.getDBConnection();
	 * 
	 * String query =
	 * "INSERT INTO patient(firstName, lastName, gender, NIC, DOB, email, phone, bloodGroup, allergies, password, cPassword) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
	 * ;
	 * 
	 * preparedStatement = con.prepareStatement(query);
	 * 
	 * //preparedStatement.setString(1, patientId); preparedStatement.setString(1,
	 * firstName); preparedStatement.setString(2, lastName);
	 * preparedStatement.setString(3, gender); preparedStatement.setString(4, NIC);
	 * preparedStatement.setString(5, DOB); preparedStatement.setString(6, email);
	 * preparedStatement.setString(7, phone); preparedStatement.setString(8,
	 * bloodGroup); preparedStatement.setString(9, allergies);
	 * preparedStatement.setString(10, password); preparedStatement.setString(11,
	 * cPassword);
	 * 
	 * preparedStatement.executeUpdate();
	 * 
	 * output = "Inserted successfully";
	 * 
	 * } catch (Exception e) {
	 * 
	 * output = "Error while inserting the item..!";
	 * System.err.println(e.getMessage()); }finally {
	 * 
	 * try { if(preparedStatement != null) { preparedStatement.close(); }
	 * 
	 * if(con != null) { con.close(); } }catch (Exception e) { e.printStackTrace();
	 * } }
	 * 
	 * return output;
	 * 
	 * }
	 */

	public String registerPatient(Patient patient) {

		String output = "";
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			con = DBConnection.getDBConnection();

			String query = "INSERT INTO patient(firstName, lastName, gender, NIC, DOB, email, phone, bloodGroup, allergies, password, cPassword) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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

		} finally {

			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return output;

	}

	@Override
	public Patient getPatient(String patientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Patient> getPatients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patient updatePatientDetails(String patientId, Patient patient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deletePatient(String patientId) {
		// TODO Auto-generated method stub
		return null;
	}

}
