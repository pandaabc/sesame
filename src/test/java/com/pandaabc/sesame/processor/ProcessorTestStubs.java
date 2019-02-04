package com.pandaabc.sesame.processor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pandaabc.sesame.constant.ApptDbOpStatus;
import com.pandaabc.sesame.dto.WebAppointment;
import com.pandaabc.sesame.jpa.entity.Appointment;

public class ProcessorTestStubs {
	
	public static Appointment getAppointment() {
		Appointment appointment = new Appointment();
		appointment.setCreationTime(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
		appointment.setAppointmentDateTime(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
		appointment.setAppointmentDuration(30l);
		appointment.setApptStatus("Available");
		appointment.setNameOfDoctor("Good Dr");
		appointment.setPrice(50.1);
		appointment.setId(1001l);
		
		return appointment;
	}
	
	public static Appointment getAppointmentWithoutId() {
		Appointment appointment = new Appointment();
		appointment.setCreationTime(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
		appointment.setAppointmentDateTime(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
		appointment.setAppointmentDuration(30l);
		appointment.setApptStatus("Available");
		appointment.setNameOfDoctor("Good Dr");
		appointment.setPrice(50.1);
		
		return appointment;
	}
	
	public static List<Appointment> getAppointmentList() {
		
		List<Appointment> list = new ArrayList<>();
		list.add(getAppointment());
		
		return list;
	}
	
public static List<Appointment> getAppointmentWithoutIdList() {
		
		List<Appointment> list = new ArrayList<>();
		list.add(getAppointmentWithoutId());
		
		return list;
	}
	
	public static WebAppointment getWebAppointment() {
		return new WebAppointment(getAppointment(), ApptDbOpStatus.TBD);
	}
	
public static List<WebAppointment> getWebAppointmentList() {
		
		List<WebAppointment> list = new ArrayList<>();
		list.add(getWebAppointment());
		
		return list;
	}

}
