package com.caremarque.appointment.service;

import java.util.ArrayList;

import com.caremarque.appointment.model.Appointment;

public interface IAppointmentService {

	public String createAppointment(Appointment appointment);
	
	public String getAppointment(String appointmentId);
	
	public String getAppointments();	
	
	public String updateAppointment(String appointmentId,Appointment appointment);
	//public Appointment updateAppointment(String appointmentid, Appointment id);
	
	public String cancelAppointment(String appointmnetId);
	
	public ArrayList<String> getAppointmentIDs();
	
}
