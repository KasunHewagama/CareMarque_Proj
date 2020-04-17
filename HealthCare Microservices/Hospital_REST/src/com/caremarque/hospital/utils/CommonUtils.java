
  package com.caremarque.hospital.utils;
 
  import java.util.ArrayList;
  
  public class CommonUtils {
	  public static String generateHospitalIDs(ArrayList<String> arrayList) {
 
		  String id; 
		  int next = arrayList.size();
		  next++; 
		  id = Constants.HOSPITAL_ID_PREFIX + next;
		  if(arrayList.contains(id)) { 
			  next++;
			  id = Constants.HOSPITAL_ID_PREFIX + next; 
			}
 
		  return id;
 
 }
 
 }
 