

package com.caremarque.hospital.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.caremarque.hospital.model.Hospital;
import com.caremarque.hospital.utils.CommonUtils;
import com.caremarque.hospital.utils.Constants;
import com.caremarque.hospital.utils.DBConnection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class HospitalServiceImpl implements IHospitalService {

	public static final Logger Log = Logger.getLogger(IHospitalService.class.getName());

	public static Connection con;
	public static Statement st;

	@Override
	public String createHospital(Hospital hospital) {
		

		String output = null;
		PreparedStatement preparedStatement = null;

		String hospitalId = CommonUtils.generateHospitalIDs(getHospitalIDs());
		System.out.println("hospitalId " + hospitalId);

		try {
			con = DBConnection.getDBConnection();

			String query = "INSERT INTO hospital(hospitalId,hospitalName,address,phone,regNo,Open_Hours,Close_Hours,email,channelingFee)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			preparedStatement = con.prepareStatement(query);

			hospital.setHospitalId(hospitalId);
			preparedStatement.setString(1, hospital.getHospitalId());
			preparedStatement.setString(2, hospital.getHospitalName());
			preparedStatement.setString(3, hospital.getAddress());
			preparedStatement.setString(4, hospital.getPhone());
			preparedStatement.setString(5, hospital.getRegNo());
			preparedStatement.setString(6, hospital.getOpen_Hours());
			preparedStatement.setString(7, hospital.getClose_Hours());
			preparedStatement.setString(8, hospital.getEmail());
			preparedStatement.setString(9, hospital.getChannelingFee());

			preparedStatement.executeUpdate();

			output = "Hospital  profile created Successfully";

		} catch (Exception e) {

			output = "Hospital Profile Not created";
			System.err.println(e.getMessage());
			Log.log(Level.SEVERE, e.getMessage());
		} finally {

			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				Log.log(Level.SEVERE, e.getMessage());
			}
		}

		return output;
	}

	@Override
	public String getHospital(String hospitalId) {
		String result = null;
		ArrayList<Hospital> arrayList=new ArrayList<Hospital>();
		
		try {
			con=DBConnection.getDBConnection();
			
			String query = "SELECT * FROM hospital "
					+ "WHERE hospitalId = '"+ hospitalId + "'";
			
			PreparedStatement preparedStatement=con.prepareStatement(query);
			ResultSet rSet=preparedStatement.executeQuery();
			
			result = "<table border=\"1\"> <tr>" + "<th>hospitalId</th> " + "<th>hospitalName</th> " + "<th>phone</th> "
					+ "<th>regNo</th> " + "<th>address</th> " + "<th>Open_Hours</th> " + "<th>Close_Hours</th> "+ "<th>email</th> " + "<th>channelingFee</th></tr> ";


			while (rSet.next()) {

				Hospital hospital = new Hospital();

				
				hospital.setHospitalName(rSet.getString(Constants.COLUMN_INDEX_ONE));
				hospital.setAddress(rSet.getString(Constants.COLUMN_INDEX_TWO));
				hospital.setPhone(rSet.getString(Constants.COLUMN_INDEX_THREE));
				hospital.setRegNo(rSet.getString(Constants.COLUMN_INDEX_FOUR));
				hospital.setOpen_Hours(rSet.getString(Constants.COLUMN_INDEX_FIVE));
				hospital.setClose_Hours(rSet.getString(Constants.COLUMN_INDEX_SIX));
				hospital.setEmail(rSet.getString(Constants.COLUMN_INDEX_SEVEN));
				hospital.setChannelingFee(rSet.getString(Constants.COLUMN_INDEX_EIGHT));
				arrayList.add(hospital);

				result += "<tr><td>" + rSet.getString(Constants.COLUMN_INDEX_ONE) + "</td>";
				result += "<td>" + rSet.getString(Constants.COLUMN_INDEX_TWO) + "</td>";
				result += "<td>" + rSet.getString(Constants.COLUMN_INDEX_THREE) + "</td>";
				result += "<td>" + rSet.getString(Constants.COLUMN_INDEX_FOUR) + "</td>";
				result += "<td>" + rSet.getString(Constants.COLUMN_INDEX_FIVE) + "</td>";
				result += "<td>" + rSet.getString(Constants.COLUMN_INDEX_SIX) + "</td>";
				result += "<td>" + rSet.getString(Constants.COLUMN_INDEX_SEVEN) + "</td>";
				result += "<td>" + rSet.getString(Constants.COLUMN_INDEX_EIGHT) + "</td>";
				result += "<td>" + rSet.getString(Constants.COLUMN_INDEX_NINE) + "</td>";
				
				System.out.println("Data Retrived");

			}

			result += "</table>";

			
		} catch (Exception e) {
			Log.log(Level.SEVERE, e.getMessage());
		}finally {

			try {
				if(st != null) {
					st.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				Log.log(Level.SEVERE, e.getMessage());
			}
		
		}
		return result;
	}
	
	
	//TO REURN A HOSPITAL LIST
	public ArrayList<Hospital> getHospitalByID(String hospitalId){
		
		String output = null;
		ArrayList<Hospital> arrayList = new ArrayList<Hospital>();
		
		try {
			con = DBConnection.getDBConnection();
			
			String query = "SELECT * FROM hospital"
					+ "WHERE hospitalId = '"+ hospitalId +"'";
			
			PreparedStatement pStatement = con.prepareStatement(query);
			ResultSet rs = pStatement.executeQuery();
			
			while(rs.next()) {
				Hospital hospital = new Hospital();
				hospital.setHospitalId(hospitalId);
				hospital.setHospitalName(rs.getString(Constants.COLUMN_INDEX_ONE));
				hospital.setAddress(rs.getString(Constants.COLUMN_INDEX_TWO));
				hospital.setPhone(rs.getString(Constants.COLUMN_INDEX_THREE));
				hospital.setRegNo(rs.getString(Constants.COLUMN_INDEX_FOUR));
				hospital.setOpen_Hours(rs.getString(Constants.COLUMN_INDEX_FIVE));
				hospital.setClose_Hours(rs.getString(Constants.COLUMN_INDEX_SIX));
				arrayList.add(hospital);
				
				System.out.println("Data Retrieved from DB...!");
			}
			
			
		
		} catch (Exception e) {
			Log.log(Level.SEVERE, e.getMessage());
		
		} finally {
			try {
				if(st != null) {
					st.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				Log.log(Level.SEVERE, e.getMessage());
			}
		}
		
		return arrayList;
	}

	@Override
	public String getHospitals() {
		
		String result = null;
		ResultSet rs = null;
		

		ArrayList<Hospital> arrayList = new ArrayList<Hospital>();

		try {
			con = DBConnection.getDBConnection();

			String query = "SELECT * from hospital";

			st = con.createStatement();
			rs = st.executeQuery(query);

			result = "<table border=\"1\"> <tr>" + "<th>hospitalId</th> " + "<th>hospitalName</th> " + "<th>address</th> "
					+ "<th>phone</th> " + "<th>regNo</th> " + "<th>Open_Hours</th> " + "<th>Close_Hours</th> " +  "<th>email</th> " + "<th>channelingFee</th></tr> ";

			while (rs.next()) {

				Hospital hospital = new Hospital();

				
				hospital.setHospitalName(rs.getString(Constants.COLUMN_INDEX_ONE));
				hospital.setAddress(rs.getString(Constants.COLUMN_INDEX_TWO));
				hospital.setPhone(rs.getString(Constants.COLUMN_INDEX_THREE));
				hospital.setRegNo(rs.getString(Constants.COLUMN_INDEX_FOUR));
				hospital.setOpen_Hours(rs.getString(Constants.COLUMN_INDEX_FIVE));
				hospital.setClose_Hours(rs.getString(Constants.COLUMN_INDEX_SIX));
				hospital.setEmail(rs.getString(Constants.COLUMN_INDEX_SEVEN));
				hospital.setChannelingFee(rs.getString(Constants.COLUMN_INDEX_EIGHT));
				arrayList.add(hospital);

				result += "<tr><td>" + rs.getString(Constants.COLUMN_INDEX_ONE) + "</td>";
				result += "<td>" + rs.getString(Constants.COLUMN_INDEX_TWO) + "</td>";
				result += "<td>" + rs.getString(Constants.COLUMN_INDEX_THREE) + "</td>";
				result += "<td>" + rs.getString(Constants.COLUMN_INDEX_FOUR) + "</td>";
				result += "<td>" + rs.getString(Constants.COLUMN_INDEX_FIVE) + "</td>";
				result += "<td>" + rs.getString(Constants.COLUMN_INDEX_SIX) + "</td>";
				result += "<td>" + rs.getString(Constants.COLUMN_INDEX_SEVEN) + "</td>";
				result += "<td>" + rs.getString(Constants.COLUMN_INDEX_EIGHT) + "</td>";
				result += "<td>" + rs.getString(Constants.COLUMN_INDEX_NINE) + "</td>";

				System.out.println("Data Retrived");

			}

			result += "</table>";

		} catch (Exception e) {
			

			result = "Error Occured.Cant read Hospital details";
			System.err.println(e.getMessage());
			Log.log(Level.SEVERE, e.getMessage());
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
			
				Log.log(Level.SEVERE, e.getMessage());
			}
		}
		return result;
	}

	@Override
	public String updateHospital(String hospitalId, Hospital hospital) {
		
		String result = "";
		PreparedStatement preparedStatement = null;

		try {
			con = DBConnection.getDBConnection();

			String query = "UPDATE hospital SET hospitalId =?, hospitalName = ?, address = ?, phone = ?, regNo = ?, Open_Hours = ?, Close_Hours = ?, email = ?, channelingFee = ? WHERE hospitalId = ?";
			preparedStatement = con.prepareStatement(query);

			preparedStatement.setString(1, hospital.getHospitalId());
			preparedStatement.setString(2, hospital.getHospitalName());
			preparedStatement.setString(3, hospital.getAddress());
			preparedStatement.setString(4, hospital.getPhone());
			preparedStatement.setString(5, hospital.getRegNo());
			preparedStatement.setString(6, hospital.getOpen_Hours());
			preparedStatement.setString(7, hospital.getClose_Hours());
			preparedStatement.setString(8, hospital.getEmail());
			preparedStatement.setString(9, hospital.getChannelingFee());
			preparedStatement.setString(10, hospital.getHospitalId());

			preparedStatement.execute();

			result = "Successfully Updated";

		} catch (Exception e) {
			

			result = "Update Error has Occured";
			System.err.println(e.getMessage());
			Log.log(Level.SEVERE, e.getMessage());
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
				Log.log(Level.SEVERE, e.getMessage());
			}
		}
		return result;
	}

	@Override
	public String DeleteHospital(String hospitalId) {
		

		String result = "";
		PreparedStatement pStatement = null;
		Connection con = null;

		try {
			con = DBConnection.getDBConnection();

			String query = "DELETE FROM hospital WHERE hospitalId = ?";

			pStatement = con.prepareStatement(query);
			
			pStatement.setString(1, hospitalId);
			pStatement.execute();

			result = "Deleted " + hospitalId + " ";

		} catch (Exception e) {

			result = "Error while deleting the appointment";
			System.err.println(e.getMessage());

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
			}
		}

		return result;

	}

	@Override
	public ArrayList<String> getHospitalIDs() {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ArrayList<String> arrayList = new ArrayList<String>();

		try {
			con = DBConnection.getDBConnection();

			String queryString = "SELECT hospital.hospitalId FROM hospital";

			preparedStatement = con.prepareStatement(queryString);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				arrayList.add(resultSet.getString(1));

			}
		} catch (Exception e) {
			
			Log.log(Level.SEVERE, e.getMessage());

		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
			
				Log.log(Level.SEVERE, e.getMessage());
			}
		}
		System.out.println(arrayList.size());
		return arrayList;
	}
	
	public String createAppointment(String hospitalId) {
		
		Client client = Client.create();
		String result = "";
		ArrayList<Hospital> hospList = new ArrayList<Hospital>();
		hospList = getHospitalByID(hospitalId);
		
		WebResource webResource = client.resource("http://localhost:8088/Appointment_REST/myService/Payment/fromHospital");
		ObjectMapper mapper = new ObjectMapper();
		String jsonInput = "";
		
		try {
			jsonInput = mapper.writeValueAsString(hospList.get(0));
			System.out.println("JSON : " +jsonInput);
		} catch (Exception e) {
			Log.log(Level.SEVERE, e.getMessage());
		}
		
		try {
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, jsonInput);
			
			if(response.getStatus() != 201) {
				throw new RuntimeException("HTTP Error : " + response.getStatus());
			}
			result = response.getEntity(String.class);
		} catch (Exception e) {
			Log.log(Level.SEVERE, e.getMessage());
		}
			System.out.println("Response from the server...!");
			System.out.println(result);
			
			return result;
		
	}

}
