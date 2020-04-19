package com.caremarque.patient.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.caremarque.patient.model.Patient;
import com.caremarque.patient.model.PatientAuthentication;
import com.caremarque.patient.utils.CommonUtils;
import com.caremarque.patient.utils.Constants;
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

	private static Connection con;

	private static PreparedStatement preparedStmt;

	private static Statement st;

	public List<PatientAuthentication> getAuthDetails() {

		List<PatientAuthentication> patientAuthList = new ArrayList<PatientAuthentication>();

		try {

			Client client = Client.create();

			WebResource webResource = client
					.resource("http://localhost:9090/PatientAuth_REST/patientAuthService/PatientAuthentication/getPatientAuth");

			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());

			}
			String output = response.getEntity(String.class);
			System.out.println("Result: " + output);

			Gson gson = new Gson();
			JsonElement list = new JsonParser().parse(output).getAsJsonObject().get("patientAuthentication");
			List<PatientAuthentication> listObj = gson.fromJson(list, new TypeToken<List<PatientAuthentication>>() {
			}.getType());
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
			

		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return patientAuthList;
	}

	// to insert patient details to the db
	@Override
	public String registerPatient(Patient patient) {

		String output = "";
		boolean validate = false;

		List<PatientAuthentication> patientAuthList = getAuthDetails();

		// Here we call the generatePatientIDs method to auto generate a patientId
		String patientId = CommonUtils.generatePatientIDs(getPatientIDs());

		try {

			con = DBConnection.getDBConnection();

			String query = "INSERT INTO patient(patientId, firstName, lastName, gender, NIC, DOB, email, phone, bloodGroup, allergies, password, cPassword) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			preparedStmt = con.prepareStatement(query);

			System.out.println("before for loop");

			for (PatientAuthentication patientAuthentication : patientAuthList) {
				System.out.println("PAUTH 1 : " + patientAuthentication.getUserName());
				System.out.println("PAUTH 2 : " + patient.getEmail());

				if (patient.getEmail().equals(patientAuthentication.getUserName())) {

					validate = true;
					System.out.println("VALIDATE : " + validate);
					break;
				}
			}

			if (validate == true) {

				output = "You already have an account from email " + patient.getEmail() + " ..!!!";

			} else {

				patient.setPatientId(patientId);
				preparedStmt.setString(Constants.COLUMN_INDEX_ONE, patient.getPatientId());
				preparedStmt.setString(Constants.COLUMN_INDEX_TWO, patient.getFirstName());
				preparedStmt.setString(Constants.COLUMN_INDEX_THREE, patient.getLastName());
				preparedStmt.setString(Constants.COLUMN_INDEX_FOUR, patient.getGender());
				preparedStmt.setString(Constants.COLUMN_INDEX_FIVE, patient.getNIC());
				preparedStmt.setString(Constants.COLUMN_INDEX_SIX, patient.getDOB());
				preparedStmt.setString(Constants.COLUMN_INDEX_SEVEN, patient.getEmail());
				preparedStmt.setString(Constants.COLUMN_INDEX_EIGHT, patient.getPhone());
				preparedStmt.setString(Constants.COLUMN_INDEX_NINE, patient.getBloodGroup());
				preparedStmt.setString(Constants.COLUMN_INDEX_TEN, patient.getAllergy());
				preparedStmt.setString(Constants.COLUMN_INDEX_ELEVEN, patient.getPassword());
				preparedStmt.setString(Constants.COLUMN_INDEX_TWELVE, patient.getConfirmPassword());

				int result = 0;

				result = preparedStmt.executeUpdate();

				if (result > 0) {

					output = patient.getFirstName() + " " + patient.getLastName()
							+ ", You are successfully registered to the system..!";
				}
			}

		} catch (Exception e) {

			output = "Error while registering to the system..!";
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

	// to get details of one patient
	@Override
	public String getPatientDetailById(int patientId) {

		String output = "";
		ResultSet rs = null;

		try {
			con = DBConnection.getDBConnection();

			String query = "SELECT * FROM patient WHERE patientId = '" + patientId + "'";

			st = con.createStatement();
			rs = st.executeQuery(query);

			output = "<table border=\"1\"> " + "<tr>" + "<th>Patient Id</th> " + "<th>First Name</th> "
					+ "<th>Last Name</th> " + "<th>Gender</th> " + "<th>NIC</th> " + "<th>DOB</th> " + "<th>Email</th> "
					+ "<th>Phone</th> " + "<th>Blood Group</th> " + "<th>Allergies</th> " + "<th>Password</th> "
					+ "</tr>";

			while (rs.next()) {

				String paId = rs.getString("patientId");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String gender = rs.getString("gender");
				String nic = rs.getString("NIC");
				String dob = rs.getString("DOB");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String bloodGroup = rs.getString("bloodGroup");
				String allergies = rs.getString("allergies");
				String password = rs.getString("password");

				output += "<tr><td>" + paId + "</td>";
				output += "<td>" + firstName + "</td>";
				output += "<td>" + lastName + "</td>";
				output += "<td>" + gender + "</td>";
				output += "<td>" + nic + "</td>";
				output += "<td>" + dob + "</td>";
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

	// to get details of all the registered patients
	@Override
	public String getAllPatients() {

		String output = "";
		ResultSet rs = null;

		try {
			con = DBConnection.getDBConnection();

			String query = "SELECT patientId, firstName, lastName, gender, NIC, DOB, email, phone, bloodGroup, allergies FROM patient";

			st = con.createStatement();
			rs = st.executeQuery(query);

			output = "<table border=\"1\"> " + "<tr>" + "<th>Patient Id</th> " + "<th>First Name</th> "
					+ "<th>Last Name</th> " + "<th>Gender</th> " + "<th>NIC</th> " + "<th>DOB</th> " + "<th>Email</th> "
					+ "<th>Phone</th> " + "<th>Blood Group</th> " + "<th>Allergies</th> " + "</tr>";

			while (rs.next()) {

				String patientId = rs.getString("patientId");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String gender = rs.getString("gender");
				String NIC = rs.getString("NIC");
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

	// to update patient details
	@Override
	public String updatePatientDetails(Patient patient) {

		String output = "";

		try {

			con = DBConnection.getDBConnection();

			String query = "UPDATE patient SET firstName=?, lastName=?, gender=?, NIC=?, DOB=?, phone=?, bloodGroup=?, allergies=?, password=?, cPassword=?"
					+ " WHERE patientId=? ";
			

			preparedStmt = con.prepareStatement(query);
			
			preparedStmt.setString(Constants.COLUMN_INDEX_ONE, patient.getFirstName());
			preparedStmt.setString(Constants.COLUMN_INDEX_TWO, patient.getLastName());
			preparedStmt.setString(Constants.COLUMN_INDEX_THREE, patient.getGender());
			preparedStmt.setString(Constants.COLUMN_INDEX_FOUR, patient.getNIC());
			preparedStmt.setString(Constants.COLUMN_INDEX_FIVE, patient.getDOB());
			preparedStmt.setString(Constants.COLUMN_INDEX_SIX, patient.getPhone());
			preparedStmt.setString(Constants.COLUMN_INDEX_SEVEN, patient.getBloodGroup());
			preparedStmt.setString(Constants.COLUMN_INDEX_EIGHT, patient.getAllergy());
			preparedStmt.setString(Constants.COLUMN_INDEX_NINE, patient.getPassword());
			preparedStmt.setString(Constants.COLUMN_INDEX_TEN, patient.getConfirmPassword());
			preparedStmt.setString(Constants.COLUMN_INDEX_ELEVEN, patient.getPatientId());
			
			preparedStmt.execute();

			output = "Patient details updated successfully..!";
			
		} catch (Exception e) {

			output = "Error while updating the patient details..!";
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
	
	// to update patient email
	@Override
	public String updatePatientEmail(Patient patient) {

		String output = "";
		boolean validate = false;

		List<PatientAuthentication> patientAuthList = getAuthDetails();

		try {

			con = DBConnection.getDBConnection();

			String query = "UPDATE patient SET email=? WHERE patientId=? ";
			

			preparedStmt = con.prepareStatement(query);

			for (PatientAuthentication patientAuthentication : patientAuthList) {
				System.out.println("PAUTH 1 : " + patientAuthentication.getUserName());
				System.out.println("PAUTH 2 : " + patient.getEmail());

				if (patient.getEmail().equals(patientAuthentication.getUserName())) {

					validate = true;
					System.out.println("VALIDATE : " + validate);
					break;
				}
			}

			if (validate == true) {

				output = "You already have an account from email " + patient.getEmail() + " ..!!!";

			} else {
				preparedStmt.setString(Constants.COLUMN_INDEX_ONE, patient.getEmail());
				preparedStmt.setString(Constants.COLUMN_INDEX_TWO, patient.getPatientId());
				
				preparedStmt.execute();

				output = "Email updated successfully..!";
			}

			
		} catch (Exception e) {

			output = "Error while updating the email..!";
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

	// to delete a patient from the system
	@Override
	public String deletePatient(String patientId) {

		String output = "";

		try {

			con = DBConnection.getDBConnection();

			String query = "DELETE FROM patient WHERE patientId = ?";

			preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(Constants.COLUMN_INDEX_ONE, patientId);

			preparedStmt.execute();

			output = "Delete account successfully..!";

		} catch (Exception e) {

			output = "Error while deleting the patient account..!";
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
	@Override
	public ArrayList<String> getPatientIDs() {

		ArrayList<String> patientList = new ArrayList<String>();

		String output = "";
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

			log.log(Level.SEVERE, e.getMessage());

		} finally {
			try {
				if (preparedStmt != null) {
					preparedStmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return patientList;
	}

}
