package com.pandaabc.sesame.processor;

import java.util.List;

import com.pandaabc.sesame.dto.Appointment;
import com.pandaabc.sesame.dto.WebAppointment;

public interface IProcessor {
	public List<WebAppointment> preprocess(List<Appointment> appointments);
	public List<WebAppointment> postprocess(List<WebAppointment> appointments);
}
