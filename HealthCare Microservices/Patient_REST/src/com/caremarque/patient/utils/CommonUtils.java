package com.caremarque.patient.utils;

import java.util.List;

public class CommonUtils {

	// *************************Patient ID Generator*************************
	// It takes the all the values from the patient table and get the count then
	// increase the count by 1
	// and create next id with the given prefix
	public static String generatePatientIDs(List<String> list) {

		String id;
		int next = list.size();
		next++;
		id = Constants.PATIENT_ID_PREFIX + next;
		if (list.contains(id)) {
			next++;
			id = Constants.PATIENT_ID_PREFIX + next;
		}
		return id;
	}
}
