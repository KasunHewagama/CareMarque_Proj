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

public class HospitalServiceImpl implements IHospitalService{
	
	public static final Logger Log = Logger.getLogger(IHospitalService.class.getName());

	public static Connection con;
	public static Statement statement;
	
	@Override
	public String createHospital(Hospital hospital) {
		// TODO Auto-generated method stub

		String output = null;
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		
		// String hospitalId = CommonUtils.generateHospitalIDs(getHospitalIDs());
		String hospitalId = CommonUtils.generateHospitalIDs(getHospitalIDs());
		System.out.println("hospitalId " + hospitalId);
		 
		try {
			con = DBConnection.getDBConnection();

			String query = "INSERT INTO hospital(hospitalId,hospitalName,address,phone,regNo,Open_Hours,Close_Hours)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

			preparedStatement = con.prepareStatement(query);
			
			hospital.setHospitalId(hospitalId);
			preparedStatement.setString(1, hospital.getHospitalId());
			preparedStatement.setString(2, hospital.getHospitalName());
			preparedStatement.setString(3, hospital.getAddress());
			preparedStatement.setString(4, hospital.getPhone());
			preparedStatement.setString(5, hospital.getRegNo());
			preparedStatement.setString(6, hospital.getOpen_Hours());
			preparedStatement.setString(7, hospital.getClose_Hours());

			preparedStatement.executeUpdate();

			output = "Hospital  profile created Successfully";

		} catch (Exception e) {
			// TODO: handle exception

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
				// TODO: handle exception
				Log.log(Level.SEVERE, e.getMessage());
			}
		}

		return output;
	}

	@Override
	public Hospital getHospital(String hospitalId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHospitals() {
		// TODO Auto-generated method stub
		String output = null;
		Statement st = null;
		ResultSet rs = null;
		Connection con = null;

		ArrayList<Hospital> arrayList = new ArrayList<Hospital>();
		
		try {
			con = DBConnection.getDBConnection();

			String query = "SELECT * from hospital";

			st = con.createStatement();
			rs = st.executeQuery(query);

			output = "<table border=\"1\"> <tr>"
					+ "<th>hospitalId</th> " 
					+ "<th>hospitalName</th> "
					+ "<th>phone</th> " 
					+ "<th>regNo</th> "
					+ "<th>address</th> " 
					+ "<th>Open_Hours</th> "
					+ "<th>Close_Hours</th></tr> ";

			while (rs.next()) {
				
				Hospital hospital = new Hospital();
				
				//hospital.setHospitalId(rs.getString(Constants.COLUMN_INDEX_ONE));
				hospital.setHospitalName(rs.getString(Constants.COLUMN_INDEX_ONE));
				hospital.setAddress(rs.getString(Constants.COLUMN_INDEX_TWO));
				hospital.setPhone(rs.getString(Constants.COLUMN_INDEX_THREE));
				hospital.setRegNo(rs.getString(Constants.COLUMN_INDEX_FOUR));
				hospital.setOpen_Hours(rs.getString(Constants.COLUMN_INDEX_FIVE));
				hospital.setClose_Hours(rs.getString(Constants.COLUMN_INDEX_SIX));
				arrayList.add(hospital);
				
				
				
				/*
				 * String hospitalId = resultobj.getString("hospitalId"); String hospitalName =
				 * resultobj.getString("hospitalName"); String phone =
				 * resultobj.getString("phone"); String regNo = resultobj.getString("regNo");
				 * String address = resultobj.getString("address"); String Open_Hours =
				 * resultobj.getString("Open_Hours"); String Close_Hours =
				 * resultobj.getString("Close_Hours");
				 */

				output += "<tr><td>" + rs.getString(Constants.COLUMN_INDEX_ONE) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_TWO) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_THREE)+ "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_FOUR) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_FIVE) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_SIX) + "</td>";
				output += "<td>" + rs.getString(Constants.COLUMN_INDEX_SEVEN) + "</td>";

				System.out.println("Data Retrived");

			}

			output += "</table>";

		} catch (Exception e) {
			// TODO: handle exception

			output = "Error Occured.Cant read Hospital details";
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
				// TODO: handle exception
				Log.log(Level.SEVERE, e.getMessage());
			}
		}
		return output;
	}

	


	@Override
	public String DeleteHospital(String hospitalId) {
		// TODO Auto-generated method stub

		String output = "";
		PreparedStatement pStatement = null;
		Connection con = null;
		
		try {
			con = DBConnection.getDBConnection();
			
			String query = "DELETE FROM hospital WHERE (hospitalId = ?)";
			
			pStatement = con.prepareStatement(query);
			
			
			pStatement.execute();
			
			output = "Deleted " + hospitalId + "Changed status to Cancel";
			
		} catch (Exception e) {

			output = "Error while deleting the appointment";
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

		return output;
	
		
	}

	@Override
	public ArrayList<String> getHospitalIDs() {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet= null;
		Connection connection=null;
		
		ArrayList<String> arrayList = new ArrayList<String>();
		
		try {
			connection = DBConnection.getDBConnection();
			
			String queryString = "SELECT hospital.hospitalId FROM hospital";
			
			preparedStatement = connection.prepareStatement(queryString);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				arrayList.add(resultSet.getString(1));
				
			}
		}catch (Exception e) {
			// TODO: handle exception
			Log.log(Level.SEVERE, e.getMessage());
			
		}finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch (Exception e) {
				// TODO: handle exception
				Log.log(Level.SEVERE, e.getMessage());
			}
		}
			System.out.println(arrayList.size());
			return arrayList;
		}

	@Override
	public String updateHospital(String hospitalId, Hospital hospital) {
		// TODO Auto-generated method stub
		String output = "";
		PreparedStatement preparedStatement = null;
		
		try {
			con = DBConnection.getDBConnection();
			
			String query = "UPDATE hospital"
					+ "SET"
					+ "hospitalName = ?"
					+ "address = ?"
					+ "phone = ?"
					+ "regNo = ?"
					+ "Open_Hours = ?"
					+ "Close_Hours = ?"
					+ "WHERE hospitalId = ?";
		preparedStatement = con.prepareStatement(query);
		
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		return null;
	}
}
