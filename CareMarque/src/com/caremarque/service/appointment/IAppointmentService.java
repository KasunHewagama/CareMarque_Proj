package com.caremarque.service.appointment;

import java.util.ArrayList;

import com.caremarque.model.Appointment;

public interface IAppointmentService {

	public String createAppointment(Appointment a);
	
	public Appointment getAppointment(String appointmentId);
	
	public ArrayList<Appointment> getAppointments();
	
	public Appointment updateAppointment(String appointmentid, Appointment id);
	
	public void cancelAppointment(String appointmnetId);
	
}
