package com.pandaabc.sesame.processor;

import org.springframework.util.StringUtils;

import com.pandaabc.sesame.dto.Appointment;

public class BaseProcessor {
	
	public boolean validataAppointment(Appointment appointment) {
		
		return appointment.getAppointmentDuration() != null && appointment.getAppointmentDuration() > 0 
				&& appointment.getPrice() != null && appointment.getPrice() > 0.0
				&& !StringUtils.isEmpty(appointment.getNameOfDoctor())
				&& appointment.getAppointmentDateTime() != null;
				
	}

}