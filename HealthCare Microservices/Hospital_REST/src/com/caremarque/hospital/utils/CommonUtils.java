package com.caremarque.hospital.utils;

import java.util.ArrayList;

public class CommonUtils {
	public static String generateHospitalIDs(ArrayList<String> arrayList) {
		
		String hID;
		int Array_Num = arrayList.size();
		
		
		Array_Num++;
		hID=Constants.HOSPITAL_ID_PREFIX + Array_Num;
		
		if(arrayList.contains(hID)) {
			Array_Num++;
			hID = Constants.HOSPITAL_ID_PREFIX + Array_Num;	
			
		}
		
		return hID;
		
	}

}
