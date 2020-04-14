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
	@Override
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
	//public ArrayList<Patient> getPatients() {
	public String getPatients() {

		String output = "";
		Statement st = null;
		ResultSet rs = null;
		Connection con = null;
		
		try
		{
		    con = DBConnection.getDBConnection();
			
			String query = "SELECT * FROM patient";			
			
			st = con.createStatement();			
		    rs = st.executeQuery(query);
			
			output = "<table border=\"1\"> "
					+ "<tr><th>firstName</th> "
					+ "<th>lastName</th> "
					+ "<th>gender</th> "
					+ "<th>NIC</th> "
					+ "<th>DOB</th> "
					+ "<th>email</th> "
					+ "<th>phone</th> "
					+ "<th>bloodGroup</th> "
					+ "<th>allergies</th> "
					+ "<th>password</th> "
					+ "<th>cPassword</th></tr>"; 
			
			while(rs.next())
			{
				
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String gender = rs.getString("gender");
				String NIC = rs.getString("NIC");
				//String DOB = rs.getDate("DOB").toString();
				String DOB = rs.getString("DOB");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String bloodGroup = rs.getString("bloodGroup");
				String allergies = rs.getString("allergies");
				String password = rs.getString("password");
				String cPassword = rs.getString("cPassword");
				
				 output += "<tr><td>" + firstName + "</td>";
				 output += "<td>" + lastName + "</td>";
				 output += "<td>" + gender + "</td>";
				 output += "<td>" + NIC + "</td>"; 
				 output += "<td>" + DOB + "</td>"; 
				 output += "<td>" + email + "</td>"; 
				 output += "<td>" + phone + "</td>"; 
				 output += "<td>" + bloodGroup + "</td>"; 
				 output += "<td>" + allergies + "</td>"; 
				 output += "<td>" + password + "</td>"; 
				 output += "<td>" + cPassword + "</td></tr>"; 

				
			}
			// Complete the html table
			 output += "</table>"; 
			
			
		}catch (Exception e){
			
			output = "Error while reading the patient details...!";
			System.err.println(e.getMessage());
			
		}finally {

			try {
				if (st != null) {
					st.close();
				}

				if (con != null) {
					con.close();
				}
				
				if(rs != null) {
					rs.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return output;
		
		
	}

	@Override
	public String updatePatientDetails(Patient patient) {
		
		String output = "";
		Connection con = null;
		PreparedStatement preparedStmt = null;
		
		try {
			
			con = DBConnection.getDBConnection();
			
			String query = "UPDATE patient SET firstName=?, lastName=?, gender=?, NIC=?, DOB=?, email=?, phone=?, bloodGroup=?, allergies=?, password=?, cPassword=? WHERE patientId=? ";
			
			preparedStmt = con.prepareStatement(query);
			
			//preparedStmt.setInt(1, patient.getPatientId());
			preparedStmt.setString(1, patient.getFirstName());
			preparedStmt.setString(2, patient.getLastName());
			preparedStmt.setString(3, patient.getGender());
			preparedStmt.setString(4, patient.getNIC());
			preparedStmt.setString(5, patient.getDOB());
			preparedStmt.setString(6, patient.getEmail());
			preparedStmt.setString(7, patient.getPhone());
			preparedStmt.setString(8, patient.getBloodGroup());
			preparedStmt.setString(9, patient.getAllergy());
			preparedStmt.setString(10, patient.getPassword());
			preparedStmt.setString(11, patient.getConfirmPassword());

			preparedStmt.executeUpdate();
			
			output = "Updated Successfully..!";
			
		}catch (Exception e) {
		
			output = "Error while updating the item..!";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

	@Override
	public String deletePatient(String patientId) {

		String output = "";		
		PreparedStatement preparedStmt = null;
		Connection con = null;
		
		try {
			
			con = DBConnection.getDBConnection();
			
			String query = "DELETE FROM patient WHERE patientId = ?";
			
			preparedStmt = con.prepareStatement(query);
			
			preparedStmt.setInt(1, Integer.parseInt(patientId));
			
			preparedStmt.execute();
			
			output = "Deleted successfully..!";
		
		}catch (Exception e) {
			
			output = "Error while deleting the item..!";
			System.err.println(e.getMessage());

		}finally {

			try {
				if (preparedStmt != null) {
					preparedStmt.close();
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

}
