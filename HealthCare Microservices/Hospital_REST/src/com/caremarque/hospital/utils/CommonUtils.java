
  package com.caremarque.hospital.utils;
 
  import java.util.ArrayList;
  
  public class CommonUtils {
	  public static String generateHospitalIDs(ArrayList<String> arrayList) {
 
		  String HOSID; 
		  int next = arrayList.size();
		  next++; 
		  HOSID = Constants.HOSPITAL_ID_PREFIX + next;
		  if(arrayList.contains(HOSID)) { 
			  next++;
			  HOSID = Constants.HOSPITAL_ID_PREFIX + next; 
			}
 
		  return HOSID;
 
 }
 
 }
 