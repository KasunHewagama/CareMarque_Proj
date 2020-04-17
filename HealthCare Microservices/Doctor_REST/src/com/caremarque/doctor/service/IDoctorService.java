package com.caremarque.doctor.service;

import java.util.ArrayList;

import com.caremarque.doctor.model.Doctor;

public interface IDoctorService {

	public String createDoctor(Doctor doctor);

	public ArrayList<String> getDoctorIDs();
}
