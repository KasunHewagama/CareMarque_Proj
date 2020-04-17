package com.caremarque.appointment.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



//import org.apache.tomcat.util.security.Escape;

import com.caremarque.appointment.model.Appointment;
import com.caremarque.appointment.utils.CommonUtils;
import com.caremarque.appointment.utils.DBConnection;
import com.caremarque.appointment.utils.Constants;
import com.mysql.cj.log.Log;

public class AppointmentServiceImpl implements IAppointmentService {

	//this object is for logging
	public static final Logger log = Logger.getLogger(IAppointmentService.class.getName());

	public static Connection con;
	
	public static Statement st;
	
	@Override
	public String createAppointment(Appointment appointment) {
		// return "Appointment created successfully...!";

		String output = null;
		PreparedStatement preparedStatement = null;
		
		//Here we call the generateAppointmentIDs method to auto generate a appointmentID
		//To do that we pass the existing appointmentid set as an arraylist
		String appointmentId = CommonUtils.generateAppointmentIDs(getAppointmentIDs());
		System.out.println("AppointmentID: " +  appointmentId);


		try {
			con = DBConnection.getDBConnection();

			String query = "INSERT INTO appointment(" 
					+ "appointmentId,patientId,patientName,phone,doctorName,"
					+ "specialization,hospitalId,hospitalName,appointmentDate,appointmentTime,"
					+ "lastUpdateDate,lastUpdateTime,appointmentStatus)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			preparedStatement = con.prepareStatement(query);

			appointment.setAppointmentId(appointmentId);
			preparedStatement.setString(1, appointment.getAppointmentId());
			preparedStatement.setString(2, appointment.getPatientId());
			preparedStatement.setString(3, appointment.getPatientName());
			preparedStatement.setString(4, appointment.getPhone());
			preparedStatement.setString(5, appointment.getDoctorName());
			preparedStatement.setString(6, appointment.getSpecialization());
			preparedStatement.setString(7, appointment.getHospitalId());
			preparedStatement.setString(8, appointment.getHospitalName());
			preparedStatement.setString(9, appointment.getAppointmentDate());
			preparedStatement.setString(10, appointment.getAppointmentTime());
			preparedStatement.setString(11, LocalDate.now().toString());
			preparedStatement.setString(12, LocalTime.now().toString());
			preparedStatement.setString(13, appointment.getAppointmentStatus());

			preparedStatement.executeUpdate();

			output = "Inserted Successfully...!";

		} catch (Exception e) {

			output = "Error when Inserting the Appointment...!";
			System.err.println(e.getMessage());
			log.log(Level.SEVERE, e.getMessage());

		} finally {

			try {
				if (preparedStatement != null) {
					preparedStatement.close();
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

	@Override
	public String getAppointment(String appointmentId) {
		
		String output = null;
		ArrayList<Appointment> arrayList = new ArrayList<Appointment>();
		
		try {
			con = DBConnection.getDBConnection();
			
			String query = "SELECT * FROM appointment "
					+ "WHERE appointmentId = '"+ appointmentId + "'";
			
			PreparedStatement pStatement = con.prepareStatement(query);
			ResultSet rs = pStatement.executeQuery();
			
			output = "<table border=\"1\"> <tr><th>appointmentId</th> " 
					+ "<th>patientId</th> " 
					+ "<th>patientName</th> " 
					+ "<th>phone</th> "
					+ "<th>doctorName</th> " 
					+ "<th>specialization</th> " 
					+ "<th>hospitalId</th> "
					+ "<th>hospitalName</th> " 
					+ "<th>appointmentDate</th> " 
					+ "<th>appointmentTime</th> "
					+ "<th>lastUpdateDate</th> " 
					+ "<th>lastUpdateTime</th> " 
					+ "<th>appointmentStatus</th></tr>";
			
			while(rs.next()) {
				Appointment appointment = new Appointment();
				appointment.setPatientId(rs.getString(Constants.COLUMN_INDEX_ONE));
				appointment.setPatientName(rs.getString(Constants.COLUMN_INDEX_TWO));
				appointment.setPhone(rs.getString(Constants.COLUMN_INDEX_THREE));
				appointment.setDoctorName(rs.getString(Constants.COLUMN_INDEX_FOUR));
				appointment.setSpecialization(rs.getString(Constants.COLUMN_INDEX_FIVE));
				appointment.setHospitalId(rs.getString(Constants.COLUMN_INDEX_SIX));
				appointment.setHospitalName(rs.getString(Constants.COLUMN_INDEX_SEVEN));
				appointment.setAppointmentDate(rs.getString(Constants.COLUMN_INDEX_EIGHT));
				appointment.setAppointmentTime(rs.getString(Constants.COLUMN_INDEX_NINE));
				appointment.setLastUpdateDate(rs.getString(Constants.COLUMN_INDEX_TEN));
				appointment.setLastUpdateTime(rs.getString(Constants.COLUMN_INDEX_ELEVEN));
				appointment.setAppointmentStatus(rs.getString(Constants.COLUMN_INDEX_TWELVE));
				arrayList.add(appointment);
				
				output += "<tr><td>" + rs.getString(Constants.COLUMN_INDEX_ONE) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_TWO) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_THREE) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_FOUR) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_FIVE) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_SIX) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_SEVEN) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_EIGHT) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_NINE) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_TEN) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_ELEVEN) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_TWELVE) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_THIRTEEN) + "</td></tr>";
				
				System.out.println("Data Retrieved from DB...!");
			}
			
			output += "</table>";
		
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		
		} finally {
			try {
				if(st != null) {
					st.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		
		return output;
	}

	@Override
	public String getAppointments() {

		String output = null;
		ResultSet rs = null;
		
		ArrayList<Appointment> arrayList = new ArrayList<Appointment>();

		try {
			con = DBConnection.getDBConnection();

			String query = "SELECT * FROM appointment";

			st = con.createStatement();
			rs = st.executeQuery(query);
			
			DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
			

			output = "<table border=\"1\"> <tr><th>appointmentId</th> " 
					+ "<th>patientId</th> " 
					+ "<th>patientName</th> " 
					+ "<th>phone</th> "
					+ "<th>doctorName</th> " 
					+ "<th>specialization</th> " 
					+ "<th>hospitalId</th> "
					+ "<th>hospitalName</th> " 
					+ "<th>appointmentDate</th> " 
					+ "<th>appointmentTime</th> "
					+ "<th>lastUpdateDate</th> " 
					+ "<th>lastUpdateTime</th> " 
					+ "<th>appointmentStatus</th></tr>";

			while (rs.next()) {
				Appointment appointment = new Appointment();
				appointment.setPatientId(rs.getString(Constants.COLUMN_INDEX_ONE));
				appointment.setPatientName(rs.getString(Constants.COLUMN_INDEX_TWO));
				appointment.setPhone(rs.getString(Constants.COLUMN_INDEX_THREE));
				appointment.setDoctorName(rs.getString(Constants.COLUMN_INDEX_FOUR));
				appointment.setSpecialization(rs.getString(Constants.COLUMN_INDEX_FIVE));
				appointment.setHospitalId(rs.getString(Constants.COLUMN_INDEX_SIX));
				appointment.setHospitalName(rs.getString(Constants.COLUMN_INDEX_SEVEN));
				appointment.setAppointmentDate(rs.getString(Constants.COLUMN_INDEX_EIGHT));
				appointment.setAppointmentTime(rs.getString(Constants.COLUMN_INDEX_NINE));
				appointment.setLastUpdateDate(rs.getString(Constants.COLUMN_INDEX_TEN));
				appointment.setLastUpdateTime(rs.getString(Constants.COLUMN_INDEX_ELEVEN));
				appointment.setAppointmentStatus(rs.getString(Constants.COLUMN_INDEX_TWELVE));
				arrayList.add(appointment);
				

				output += "<tr><td>" + rs.getString(Constants.COLUMN_INDEX_ONE) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_TWO) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_THREE) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_FOUR) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_FIVE) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_SIX) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_SEVEN) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_EIGHT) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_NINE) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_TEN) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_ELEVEN) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_TWELVE) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_THIRTEEN) + "</td></tr>";
				
				
				System.out.println("Data Retrieved from database...!");
			}

			output += "</table>";

		} catch (Exception e) {

			output = "Error while reading appointment details...!";
			System.err.println(e.getMessage());
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

	@Override
	public String updateAppointment(String appointmentId, Appointment appointment) {
		
		String output = "";
		PreparedStatement pStatement = null;
		
		try {
			con = DBConnection.getDBConnection();
			
			String query = "UPDATE appointment"
					+ "SET "
					+ "patientId = ?"
					+ "patientName = ?"
					+ "phone = ?"
					+ "doctorName = ?"
					+ "specialization = ?"
					+ "hospitalId = ?"
					+ "hospitalName = ?"
					+ "appointmentDate = ?"
					+ "appointmentTime = ?"
					+ "lastUpdateDate = ?"
					+ "lastUpdateTime = ?"
					+ "appoinmentStatus = ?"
					+ "WHERE appointmentId = ?";
			
			pStatement = con.prepareStatement(query);
			
			pStatement.setString(1, appointment.getPatientId());
			pStatement.setString(2, appointment.getPatientName());
			pStatement.setString(3, appointment.getPhone());
			pStatement.setString(4, appointment.getDoctorName());
			pStatement.setString(5, appointment.getSpecialization());
			pStatement.setString(6, appointment.getHospitalId());
			pStatement.setString(7, appointment.getHospitalName());
			pStatement.setString(8, appointment.getAppointmentDate());
			pStatement.setString(9, appointment.getAppointmentTime());
			pStatement.setString(10, LocalDate.now().toString());
			pStatement.setString(11, LocalTime.now().toString());
			pStatement.setString(12, "Pending");
			pStatement.setString(13, appointmentId);
			
			pStatement.execute();
			
			output = "Successfully Updated...!";


		} catch (Exception e) {
			
			output = "Error while updating the Appointment...!";
			System.err.println(e.getMessage());
			log.log(Level.SEVERE, e.getMessage());
					
		} finally {

			try {
				if (pStatement != null) {
					pStatement.close();
				}

				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		
		return output;
	}

	@Override
	public String cancelAppointment(String appointmnetId) {
		
		String output = "";
		String state = "Cancel";
		PreparedStatement pStatement = null;
		
		try {
			con = DBConnection.getDBConnection();
			
			String query = "UPDATE appointment"
					+ "SET appointmentStatus = ?"
					+ "WHERE appointmentId = ?";
			
			pStatement = con.prepareStatement(query);
			
			pStatement.setString(Constants.COLUMN_INDEX_ONE, state);
			pStatement.setString(Constants.COLUMN_INDEX_TWO, appointmnetId);
			
			pStatement.execute();
			
			output = "Deleted " + appointmnetId + "Changed status to Cancel";
			
		} catch (Exception e) {

			output = "Error while deleting the appointment";
			System.err.println(e.getMessage());
			log.log(Level.SEVERE, e.getMessage());
		
		} finally {

			try {
				if (pStatement != null) {
					pStatement.close();
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

	//This method get all the existing appointmentids and put them into a arraylist
	@Override
	public ArrayList<String> getAppointmentIDs() {
		
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		ArrayList<String> arrayList = new ArrayList<String>();
		
		try {
			con = DBConnection.getDBConnection();
			
			String query = "SELECT appointment.appointmentId FROM appointment";
			
			preparedStatement = con.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				arrayList.add(rs.getString(1));
				
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
			}

		}
		System.out.println(arrayList.size());
		return arrayList;
	}

}
