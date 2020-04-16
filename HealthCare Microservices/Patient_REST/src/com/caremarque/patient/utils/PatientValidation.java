package com.caremarque.patient.utils;

import java.util.regex.Pattern;

import com.caremarque.patient.model.Patient;

public class PatientValidation {

	public static boolean isValid(Patient patient) {
		
		Pattern alphaPattern = Pattern.compile("/^[a-zA-Z]+$/");
		Pattern nicPattern = Pattern.compile("/^[0-9]{9}[vVxX]$/");
		Pattern emailPattern = Pattern.compile("/^[\\w\\-\\.\\+]+\\@[a-zA-Z0-9\\.\\-]+\\.[a-zA-z0-9]{2,4}$/");
		Pattern dobPattern = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");
		Pattern bloodTypePattern = Pattern.compile("^(A|B|AB|O)[+-]$");
		Pattern pwdPattern = Pattern.compile("/(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}/");
		Pattern phonePattern = Pattern.compile("/^\\d{10}$/");
		
//		if(patient != null) {
			if((patient.getFirstName() != null) && (!patient.getFirstName().isEmpty()) && alphaPattern.matcher(patient.getFirstName()).matches()) {
				if((patient.getLastName() != null) && (!patient.getLastName().isEmpty()) && alphaPattern.matcher(patient.getLastName()).matches()) {
					if((patient.getGender() != null) && (!patient.getGender().isEmpty()) && alphaPattern.matcher(patient.getGender()).matches()) {
						if((patient.getNIC() != null) && (!patient.getNIC().isEmpty()) && nicPattern.matcher(patient.getNIC()).matches()) {
						   if((patient.getDOB() != null) && (!patient.getDOB().isEmpty()) && dobPattern.matcher(patient.getDOB()).matches()) {
							   if((patient.getEmail() != null) && (!patient.getEmail().isEmpty()) && emailPattern.matcher(patient.getEmail()).matches()) {
								   if((patient.getPhone() != null) && (!patient.getPhone().isEmpty()) && phonePattern.matcher(patient.getPhone()).matches()) {
									   if((patient.getBloodGroup() != null) && (!patient.getBloodGroup().isEmpty()) && bloodTypePattern.matcher(patient.getBloodGroup()).matches()) {
										   if((patient.getPassword() != null) && (!patient.getPassword().isEmpty()) && pwdPattern.matcher(patient.getPassword()).matches()) {
											   if((patient.getConfirmPassword() != null) && (!patient.getConfirmPassword().isEmpty()) && patient.getConfirmPassword().equals(patient.getPassword())) {
												   
												   return true;					   
											   }
										   }
									   }
								   }
							   }
							   
						   }
						}
					}
				}
			}
		
		return false;
	}
	
}
