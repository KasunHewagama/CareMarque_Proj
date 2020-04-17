package com.caremarque.patient.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

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

public class PatientServiceImpl implements IPatientService {
	
	public static final Logger log = Logger.getLogger(PatientServiceImpl.class.getName());

	public static Connection con;
	
	public static PreparedStatement preparedStmt;
	
	public static Statement st;
	
	public List<PatientAuthentication> getAuthDetails() {

		List<PatientAuthentication> patientAuthList = new ArrayList<PatientAuthentication>();

		try {

			Client client = Client.create();
			
			WebResource webResource = client
					.resource("http://localhost:9090/UserAuth_REST/myService/UserAuthentication/getPatientAuth");

			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());

			}
			String output = response.getEntity(String.class);

			Gson gson = new Gson();
			JsonElement list = new JsonParser().parse(output).getAsJsonObject().get("UserAuthentication");
			List<PatientAuthentication> listObj = gson.fromJson(list, new TypeToken<List<PatientAuthentication>>() {}.getType());
			System.out.println(listObj.size());

			for (PatientAuthentication patientAuthentication : listObj) {
				System.out.println(patientAuthentication);
				patientAuthList.add(patientAuthentication);

			}

			for (PatientAuthentication patientAuthentication : patientAuthList) {
				System.out.println("ID : " + patientAuthentication.getPatientAuthId());
				System.out.println("email : " + patientAuthentication.getUserName());

			}

			System.out.println(listObj.get(0).getPatientAuthId());
			System.out.println(listObj.get(0).getUserName());
			System.out.println(listObj.get(0).getPassword());

		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return patientAuthList;
	}
	
	

	//to insert patient details to the db
	@Override
	public String registerPatient(Patient patient) {
		
		String output = "";
		boolean validate = false;
		
		List<PatientAuthentication> patientAuthList = getAuthDetails();
		
		//String patientId = 
		
//		Pattern alphaPattern = Pattern.compile("/^[a-zA-Z]+$/");
//		Pattern nicPattern = Pattern.compile("/^[0-9]{9}[vVxX]$/");
//		Pattern emailPattern = Pattern.compile("/^[\\w\\-\\.\\+]+\\@[a-zA-Z0-9\\.\\-]+\\.[a-zA-z0-9]{2,4}$/");
//		Pattern dobPattern = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");
//		Pattern bloodTypePattern = Pattern.compile("^(A|B|AB|O)[+-]$");
//		Pattern pwdPattern = Pattern.compile("/(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}/");
//		Pattern phonePattern = Pattern.compile("/^\\d{10}$/");
		

		try {

			con = DBConnection.getDBConnection();

			String query = "INSERT INTO patient(firstName, lastName, gender, NIC, DOB, email, phone, bloodGroup, allergies, password, cPassword) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			preparedStmt = con.prepareStatement(query);
			
			System.out.println("before for loop");
			
			
			String fName = patient.getFirstName();
			System.out.println("fname"+fName);
			String lName = patient.getLastName();
			String gender = patient.getGender();
			String NIC = patient.getNIC();
			String dob = patient.getDOB();
			String email = patient.getEmail();
			String phone = patient.getPhone();
			String bloodGroup = patient.getBloodGroup();
			String allergy = patient.getAllergy();
			String pwd = patient.getPassword();
			String cPwd = patient.getConfirmPassword();
			
			System.out.println(fName.trim().length());
			
//			if(fName.trim().length() < 0 && !fName.matches(alphaReg)) {
//				output = "Please use alphabets only for first name..!";
//			}
//			else if(lName.trim().length() < 0 && !alphaPattern.matcher(lName).matches()) {
//				output = "Please use alphabets only for last name..!";
//			}
//			else if(gender.trim().length() < 0 && !alphaPattern.matcher(gender).matches()) {
//				output = "Please use alphabets only for gender..!";
//			}
//			else if(NIC.trim().length() < 0 && !nicPattern.matcher(NIC).matches()) {
//				output = "Please enter a correct NIC number...!";
//			}
//			else if(dob.trim().length() < 0 && !dobPattern.matcher(dob).matches()) {
//				output = "Please use dd/mm/yyyy format...!";
//			}
//			else if(email.trim().length() < 0 && !emailPattern.matcher(email).matches()) {
//				output = "Please enter a valid email...!";
//			}
//			else if(phone.trim().length() < 0 && !phonePattern.matcher(phone).matches()) {
//				output = "Please enter a valid phone number...!";
//			}
//			else if(bloodGroup.trim().length() < 0 && !bloodTypePattern.matcher(bloodGroup).matches()) {
//				output = "Please enter correct blood group...!";
//			}
//			else if(allergy.trim().length() < 0 && !alphaPattern.matcher(allergy).matches()) {
//				output = "Please use alphabets only for allergies..!";
//			}
//			else if(pwd.trim().length() < 0 && !pwdPattern.matcher(pwd).matches()) {
//				output = "Please enter a password with at least six characters containing one number, one lowercase and one uppercase letter..!";
//			}
//			else if(cPwd.trim().length() < 0 && !cPwd.equals(pwd)) {
//				output = "Passwords are not match..!";
//			}
			//else {
				preparedStmt.setString(1, fName);
				preparedStmt.setString(2, lName);
				preparedStmt.setString(3, gender);
				preparedStmt.setString(4, NIC);
				preparedStmt.setString(5, dob);
				preparedStmt.setString(6, email);
				preparedStmt.setString(7, phone);
				preparedStmt.setString(8, bloodGroup);
				preparedStmt.setString(9, allergy);
				preparedStmt.setString(10, pwd);
				preparedStmt.setString(11, cPwd);
				
				int result = 0;
				   
				   result = preparedStmt.executeUpdate();			
				  
				   if(result > 0) {
					output = "Inserted successfully";	
				   }
			//}
			
				
			
		/*	for(PatientAuthentication patientAuthentication : patientAuthList) {
				System.out.println("PAUTH 1 : " + patientAuthentication.getUserName());
				System.out.println("PAUTH 2 : " + patient.getEmail());
				System.out.println("PAUTH 3 : " + patientAuthentication.getPassword());
				System.out.println("PAUTH 4 : " + patient.getPassword());

				if(patient.getEmail().equals(patientAuthentication.getUserName())){
					
					validate = true;
					System.out.println("VALIDATE : " + validate);
					break;
				}
			}
			
			
			if(validate == true) {
				
				output = "You already have an account from email" + patient.getEmail() + "..!!!";
			
			
//			 int result = 0;
//			   
//			   result = preparedStmt.executeUpdate();
//		
//			   if(result > 0) {
//				output = "Inserted successfully";	
//			   }
//			
			}else {
				
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
				
				preparedStmt.execute();
				output = "Inserted successfully";
			}*/

		} catch (Exception e) {

			output = "Error while inserting the item..!";
			log.log(Level.SEVERE, e.getMessage());

		} finally {

			try {
				if (preparedStmt != null) {
					preparedStmt.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

		return output;

	}

	//to get details of one patient
	@Override
	public String getPatientDetail(int patientId) {
		
		String output = "";
//		Statement st = null;
		ResultSet rs = null;
	//	Connection con = null;

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
			log.log(Level.SEVERE, e.getMessage());

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
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return output;

	}

	//to get details of all the registered patients
	@Override
	public String getAllPatients() {

		String output = "";
		//Statement st = null;
		ResultSet rs = null;
		//Connection con = null;

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
			log.log(Level.SEVERE, e.getMessage());

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
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return output;

	}

	//to update patient details
	@Override
	public String updatePatientDetails(Patient patient) {

		String output = "";
		//Connection con = null;
		//PreparedStatement preparedStmt = null;

		try {

			con = DBConnection.getDBConnection();

			String query = "UPDATE patient SET firstName=?, lastName=?, gender=?, NIC=?, DOB=?, email=?, phone=?, bloodGroup=?, allergies=?, password=?, cPassword=?"
					+ " WHERE patientId=? ";

			preparedStmt = con.prepareStatement(query);

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
			preparedStmt.setInt(12, patient.getPatientId());
			


			preparedStmt.execute();

			output = "Updated Successfully..!";

		} catch (Exception e) {

			output = "Error while updating the item..!";
			log.log(Level.SEVERE, e.getMessage());

		} finally {

			try {
				if (preparedStmt != null) {
					preparedStmt.close();
				}

				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

		return output;
	}

	//to delete a patient from the system
	@Override
	public String deletePatient(String patientId) {

		String output = "";
		//PreparedStatement preparedStmt = null;
		//Connection con = null;

		try {

			con = DBConnection.getDBConnection();

			String query = "DELETE FROM patient WHERE patientId = ?";

			preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, Integer.parseInt(patientId));

			preparedStmt.execute();

			output = "Deleted successfully..!";

		} catch (Exception e) {

			output = "Error while deleting the item..!";
			log.log(Level.SEVERE, e.getMessage());

		} finally {

			try {
				if (preparedStmt != null) {
					preparedStmt.close();
				}

				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

		return output;
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
