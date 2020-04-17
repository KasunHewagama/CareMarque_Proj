package com.caremarque.patient.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import com.caremarque.patient.model.Patient;
import com.caremarque.patient.model.PatientAuthentication;
import com.caremarque.patient.utils.DBConnection;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class PatientService implements IPatientService {

	Pattern alphaPattern = Pattern.compile("/^[a-zA-Z]+$/");
	Pattern nicPattern = Pattern.compile("/^[0-9]{9}[vVxX]$/");
	Pattern emailPattern = Pattern.compile("/^[\\w\\-\\.\\+]+\\@[a-zA-Z0-9\\.\\-]+\\.[a-zA-z0-9]{2,4}$/");
	Pattern dobPattern = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");
	Pattern bloodTypePattern = Pattern.compile("^(A|B|AB|O)[+-]$");
	Pattern pwdPattern = Pattern.compile("/(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}/");
	Pattern phonePattern = Pattern.compile("/^\\d{10}$/");

	//to insert patient details to the db
	@Override
	public String registerPatient(Patient patient) {

		String output = "";
		Connection con = null;
		PreparedStatement preparedStmt = null;
		boolean validate = true;
		
		List<PatientAuthentication> patientAuthList = getPatientAuthDetails();
		

		try {
			con = DBConnection.getDBConnection();

			String query = "INSERT INTO patient(firstName, lastName, gender, NIC, DOB, email, phone, bloodGroup, allergies, password, cPassword) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			preparedStmt = con.prepareStatement(query);
			
			for(PatientAuthentication patientAuthentication : patientAuthList) {
				System.out.println("PAUTH 1 : " + patientAuthentication.getUserName());
				System.out.println("PAUTH 2 : " + patient.getEmail());
				System.out.println("PAUTH 3 : " + patientAuthentication.getPassword());
				System.out.println("PAUTH 4 : " + patient.getPassword());

				if(patientAuthentication.getUserName().equals(patient.getEmail()) && patientAuthentication.getPassword().equals(patient.getPassword()) ){
					
					validate = false;
					System.out.println("VALIDATE : " + validate);
					break;
				}

			}
			
			if(validate == false) {
				
			
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
			
			 int result = 0;
			   
			   result = preparedStmt.executeUpdate();
		
			   if(result > 0) {
				output = "Inserted successfully";	
			   }
			
			}else {
				output = "Authentication Error!!!";
				
			}

//			String fName = patient.getFirstName();
//			String lName = patient.getLastName();
//			String gender = patient.getGender();
//			String NIC = patient.getNIC();
//			String DOB = patient.getDOB();
//			String email =  patient.getEmail();
//			String phone = patient.getPhone();
//			String bloodGroup = patient.getBloodGroup();
//			String allergy = patient.getAllergy();
//			String pwd = patient.getPassword();
//			String cPwd = patient.getConfirmPassword();
		

//				
//			if((patient.getFirstName() != null) && (!patient.getFirstName().isEmpty()) && alphaPattern.matcher(patient.getFirstName()).matches()) 
//				if((patient.getLastName() != null) && (!patient.getLastName().isEmpty()) && alphaPattern.matcher(patient.getLastName()).matches()) 
//					if((patient.getGender() != null) && (!patient.getGender().isEmpty()) && alphaPattern.matcher(patient.getGender()).matches()) 
//						if((patient.getNIC() != null) && (!patient.getNIC().isEmpty()) && nicPattern.matcher(patient.getNIC()).matches()) 
//							   if((patient.getDOB() != null) && (!patient.getDOB().isEmpty()) && dobPattern.matcher(patient.getDOB()).matches()) 
//								   if((patient.getEmail() != null) && (!patient.getEmail().isEmpty()) && emailPattern.matcher(patient.getEmail()).matches()) 
//									   if((patient.getPhone() != null) && (!patient.getPhone().isEmpty()) && phonePattern.matcher(patient.getPhone()).matches()) 
//										   if((patient.getBloodGroup() != null) && (!patient.getBloodGroup().isEmpty()) && bloodTypePattern.matcher(patient.getBloodGroup()).matches()) 
//											   if((patient.getPassword() != null) && (!patient.getPassword().isEmpty()) && pwdPattern.matcher(patient.getPassword()).matches()) 
//												   if((patient.getConfirmPassword() != null) && (!patient.getConfirmPassword().isEmpty()) && patient.getConfirmPassword().equals(patient.getPassword())) {
//													   
//														preparedStmt.setString(1, patient.getFirstName());
//														preparedStmt.setString(2, patient.getLastName());
//														preparedStmt.setString(3, patient.getGender());
//														preparedStmt.setString(4, patient.getNIC());
//														preparedStmt.setString(5, patient.getDOB());
//														preparedStmt.setString(6, patient.getEmail());
//														preparedStmt.setString(7, patient.getPhone());
//														preparedStmt.setString(8, patient.getBloodGroup());
//														preparedStmt.setString(9, patient.getAllergy());
//														preparedStmt.setString(10, patient.getPassword());
//														preparedStmt.setString(11, patient.getConfirmPassword());
//													   
//													   int result = 0;
//													   
//													   result = preparedStmt.executeUpdate();
//												
//													   if(result > 0) {
//														output = "Inserted successfully";	
//													   }
//												   }else 
//													   output="Cpassword";
//												   
//
//											   else 
//												   output="password";
//											   
//
//										   else 
//											   output="blood";
//										   
//
//									   else 
//										   output="phone";
//									   
// 
//								   else 
//									   output="DOB";
//								   
//
//							   else
//								   output="DOB";
//							   
//
//						else 
//							output="NIC";
//						
//
//					else 
//						output="gender";
//					
//
//				else 
//					output="lName";
//				
//
//			else 
//				output="fName";
			
				
			

		} catch (Exception e) {

			output = "Error while inserting the item..!";
			System.err.println(e.getMessage());

		} finally {

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

	//to get details of one patient
	@Override
	public String getPatientDetail(int patientId) {
		
		String output = "";
		Statement st = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = DBConnection.getDBConnection();

			String query = "SELECT * FROM patient WHERE patientId = '"+ patientId + "'";

			st = con.createStatement();
			rs = st.executeQuery(query);

			output = "<table border=\"1\"> " + "<tr>" + "<th>firstName</th> " + "<th>lastName</th> "
					+ "<th>gender</th> " + "<th>NIC</th> " + "<th>DOB</th> " + "<th>email</th> " + "<th>phone</th> "
					+ "<th>bloodGroup</th> " + "<th>allergies</th> " + "<th>password</th> " 
					+ "</tr>";

			while (rs.next()) {

				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String gender = rs.getString("gender");
				String NIC = rs.getString("NIC");
				// String DOB = rs.getDate("DOB").toString();
				String DOB = rs.getString("DOB");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String bloodGroup = rs.getString("bloodGroup");
				String allergies = rs.getString("allergies");
				String password = rs.getString("password");

				output += "<tr><td>" + firstName + "</td>";
				output += "<td>" + lastName + "</td>";
				output += "<td>" + gender + "</td>";
				output += "<td>" + NIC + "</td>";
				output += "<td>" + DOB + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + phone + "</td>";
				output += "<td>" + bloodGroup + "</td>";
				output += "<td>" + allergies + "</td>";
				output += "<td>" + password + "</td></tr>";

			}
			// Complete the html table
			output += "</table>";

		} catch (Exception e) {

			output = "Error while reading the patient details...!";
			System.err.println(e.getMessage());

		} finally {

			try {
				if (st != null) {
					st.close();
				}

				if (con != null) {
					con.close();
				}

				if (rs != null) {
					rs.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return output;

	}

	//to get details of all the registered patients
	@Override
	public String getAllPatients() {

		String output = "";
		Statement st = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = DBConnection.getDBConnection();

			String query = "SELECT patientId, firstName, lastName, gender, NIC, DOB, email, phone, bloodGroup, allergies FROM patient";

			st = con.createStatement();
			rs = st.executeQuery(query);

			output = "<table border=\"1\"> " + "<tr>" + "<th>patientId</th> " + "<th>firstName</th> " + "<th>lastName</th> "
					+ "<th>gender</th> " + "<th>NIC</th> " + "<th>DOB</th> " + "<th>email</th> " + "<th>phone</th> "
					+ "<th>bloodGroup</th> " + "<th>allergies</th> " 
					+ "</tr>";

			while (rs.next()) {

				String patientId = Integer.toString(rs.getInt("patientId"));
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String gender = rs.getString("gender");
				String NIC = rs.getString("NIC");
				// String DOB = rs.getDate("DOB").toString();
				String DOB = rs.getString("DOB");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String bloodGroup = rs.getString("bloodGroup");
				String allergies = rs.getString("allergies");

				output += "<tr><td>" + patientId + "</td>";
				output += "<td>" + firstName + "</td>";
				output += "<td>" + lastName + "</td>";
				output += "<td>" + gender + "</td>";
				output += "<td>" + NIC + "</td>";
				output += "<td>" + DOB + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + phone + "</td>";
				output += "<td>" + bloodGroup + "</td>";
				output += "<td>" + allergies + "</td></tr>";

			}
			// Complete the html table
			output += "</table>";

		} catch (Exception e) {

			output = "Error while reading the patient details...!";
			System.err.println(e.getMessage());

		} finally {

			try {
				if (st != null) {
					st.close();
				}

				if (con != null) {
					con.close();
				}

				if (rs != null) {
					rs.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return output;

	}

	//to update patient details
	@Override
	public String updatePatientDetails(Patient patient) {

		String output = "";
		Connection con = null;
		PreparedStatement preparedStmt = null;

		try {

			con = DBConnection.getDBConnection();

			String query = "UPDATE patient SET firstName=?, lastName=?, gender=?, NIC=?, DOB=?, email=?, phone=?, bloodGroup=?, allergies=?, password=?, cPassword=?"
					+ " WHERE patientId=? ";

			preparedStmt = con.prepareStatement(query);

			/*preparedStmt.setString(1, patient.getFirstName());
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
			preparedStmt.setInt(12, patient.getPatientId());*/
			
//			int columnIndex = 0;
//			Pattern pattern;
//			
//			switch (columnIndex) {
//			case 1:
//				
//				pattern = Pattern.compile("/^[a-zA-Z]+$/");
//
//				if(patient.getFirstName() != null && pattern.matcher(patient.getFirstName()).matches()) {
//					preparedStmt.setString(1, patient.getFirstName());
//				}
//				else if(patient.getFirstName() == null && patient.getFirstName().isEmpty()){
//					output="Please enter first name..!";
//				}
//				else {
//					output="Please use only alphabets for the first name..!";
//
//				}
//				
//				break;
//				
//			case 2:
//				
//				pattern = Pattern.compile("/^[a-zA-Z]+$/");
//
//				if(patient.getLastName() != null && pattern.matcher(patient.getLastName()).matches()) {
//					preparedStmt.setString(2, patient.getLastName());
//				}
//				else if(patient.getLastName() == null && patient.getLastName().isEmpty()){
//					output="Please enter last name..!";
//				}
//				else {
//					output="Please use only alphabets for the last name..!";
//
//				}
//				
//				break;
//				
//	       case 3:
//				
//				pattern = Pattern.compile("/^[a-zA-Z]+$/");
//
//				if(patient.getGender() != null && pattern.matcher(patient.getGender()).matches()) {
//					preparedStmt.setString(3, patient.getGender());
//				}
//				else if(patient.getGender() == null && patient.getGender().isEmpty()){
//					output="Please enter gender..!";
//				}
//				else {
//					output="Please use only alphabets for the gender..!";
//
//				}
//				
//				break;
//				
//	       case 4:
//				
//				pattern = Pattern.compile("/^[0-9]{9}[vVxX]$/");
//
//				if(patient.getNIC() != null && pattern.matcher(patient.getNIC()).matches()) {
//					preparedStmt.setString(4, patient.getNIC());
//				}
//				else if(patient.getNIC() == null && patient.getNIC().isEmpty()){
//					output="Please enter NIC..!";
//				}
//				else {
//					output="Please enter a correct NIC number...!";
//
//				}
//				
//				break;
//				
//	       case 5:
//				
//				pattern = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");
//				
//				if(patient.getDOB() != null && pattern.matcher(patient.getDOB()).matches()) {
//					preparedStmt.setString(5, patient.getDOB());
//				}
//				else if(patient.getDOB() == null && patient.getDOB().isEmpty()){
//					output="Please enter DOB..!";
//				}
//				else {
//					output="Please enter dob in format dd/mm/yyyy..!";
//
//				}
//				
//				break;
//				
//	       case 6:
//				
//				pattern = Pattern.compile("/^[\\w\\-\\.\\+]+\\@[a-zA-Z0-9\\.\\-]+\\.[a-zA-z0-9]{2,4}$/");
//				
//				if(patient.getEmail() != null && pattern.matcher(patient.getEmail()).matches()) {
//					preparedStmt.setString(6, patient.getEmail());
//				}
//				else if(patient.getEmail() == null && patient.getEmail().isEmpty()){
//					output="Please enter email..!";
//				}
//				else {
//					output="Please enter a valid email...!";
//
//				}
//				
//				break;
//				
//	       case 7:
//				
//				pattern = Pattern.compile("/^\\d{10}$/");
//				
//				if(patient.getPhone() != null && pattern.matcher(patient.getPhone()).matches()) {
//					preparedStmt.setString(7, patient.getPhone());
//				}
//				else if(patient.getPhone() == null && patient.getPhone().isEmpty()){
//					output="Please enter phone..!";
//				}
//				else {
//					output="Please enter a valid phone...!";
//
//				}
//				
//				break;
//
//			default:
//				break;
//			}

			preparedStmt.execute();

			output = "Updated Successfully..!";

		} catch (Exception e) {

			output = "Error while updating the item..!";
			System.err.println(e.getMessage());

		} finally {

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

	//to delete a patient from the system
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

		} catch (Exception e) {

			output = "Error while deleting the item..!";
			System.err.println(e.getMessage());

		} finally {

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
	
	public List<PatientAuthentication> getPatientAuthDetails() {

		List<PatientAuthentication> patientAuthList = new ArrayList<PatientAuthentication>();

		try {

			Client client = Client.create();

			WebResource webResource = client
					.resource("http://localhost:8088/PaymentAuth_REST/myService/PaymentAuthentication/getAuthDetails");

			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());

			}
			String output = response.getEntity(String.class);

			Gson gson = new Gson();
			JsonElement list = new JsonParser().parse(output).getAsJsonObject().get("UserAuthentication");
			List<PatientAuthentication> listObj = gson.fromJson(list, new TypeToken<List<PatientAuthentication>>() {
			}.getType());
			System.out.println(listObj.size());

			for (PatientAuthentication patientAuthentication : listObj) {
				System.out.println(patientAuthentication);
				patientAuthList.add(patientAuthentication);

			}

			for (PatientAuthentication patientAuthentication : patientAuthList) {
				System.out.println("ID : " + patientAuthentication.getPatientAuthId());
				System.out.println("ID : " + patientAuthentication.getUserName());

			}

			System.out.println(listObj.get(0).getPatientAuthId());
			System.out.println(listObj.get(0).getUserName());
			System.out.println(listObj.get(0).getPassword());

		} catch (Exception e) {
			e.getMessage();
		}
		return patientAuthList;
	}
		
		

	

	// to get all the registerd patients to a arraylist
	/*@Override
	public ArrayList<String> getPatientIDs() {

		ArrayList<String> patientList = new ArrayList<String>();

		String output = "";
		Connection con = null;
		PreparedStatement preparedStmt = null;
		ResultSet rs = null;

		try {
			con = DBConnection.getDBConnection();

			String query = "SELECT patientId FROM patient";

			preparedStmt = con.prepareStatement(query);
			rs = preparedStmt.executeQuery();

			while (rs.next()) {

				patientList.add(rs.getString(1));

			}

		} catch (Exception e) {

			System.err.println(e.getMessage());

		} finally {
			try {
				if (preparedStmt != null) {
					preparedStmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}
		return patientList;
	}*/

}
