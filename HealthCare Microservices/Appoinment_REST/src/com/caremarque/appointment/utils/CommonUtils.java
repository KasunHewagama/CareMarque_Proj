package com.caremarque.appointment.utils;

import java.util.ArrayList;


public class CommonUtils {
		//TODO: Here we can implement some common functions used in though out the project 
	
		//*************************Appointment ID Generator*************************
		//It takes the all the values from the payment table and get the count then increase the count by 1
		//and create next id with the given prefix
	
	public static String generateAppointmentIDs(ArrayList<String> arrayList) {
		
		String id;
		int next = arrayList.size();
		next++;
		id = Constants.APPOINTMENT_ID_PREFIX + next;
		if(arrayList.contains(id)) {
			next++;
			id = Constants.APPOINTMENT_ID_PREFIX + next;
		}
		return id;
	}

}
