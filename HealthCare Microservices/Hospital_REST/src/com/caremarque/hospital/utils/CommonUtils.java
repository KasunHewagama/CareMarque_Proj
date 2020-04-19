
  package com.caremarque.hospital.utils;
 
  import java.util.ArrayList;
  
  public class CommonUtils {
	  public static String generateHospitalIDs(ArrayList<String> arrayList) {
 
		  String HOSID; 
		  int num = arrayList.size();
		  num++; 
		  HOSID = Constants.HOSPITAL_ID_PREFIX + num;
		  if(arrayList.contains(HOSID)) { 
			  num++;
			  HOSID = Constants.HOSPITAL_ID_PREFIX + num; 
			}
 
		  return HOSID;
 
 }
 
 }
 