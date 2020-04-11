package com.caremarque.service.appointment;

import java.util.ArrayList;

import com.caremarque.model.Appointment;

public interface IAppointmentService {

	public String createAppointment(Appointment appointment);
	
	public Appointment getAppointment(String appointmentId);
	
	public String getAppointments();	
	//public ArrayList<Appointment> getAppointments();
	
	public String updateAppointment(Appointment appointment);
	//public Appointment updateAppointment(String appointmentid, Appointment id);
	
	public String cancelAppointment(String appointmnetId);
	
}
