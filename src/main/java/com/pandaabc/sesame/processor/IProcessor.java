package com.pandaabc.sesame.processor;

import java.util.List;

import com.pandaabc.sesame.dto.WebAppointment;
import com.pandaabc.sesame.jpa.entity.Appointment;

public interface IProcessor {
	public List<WebAppointment> preprocess(List<Appointment> appointments);
	public List<WebAppointment> postprocess(List<WebAppointment> appointments);
}
