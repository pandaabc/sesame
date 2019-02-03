package com.pandaabc.sesame.processor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.pandaabc.sesame.constant.ApptDbOpStatus;
import com.pandaabc.sesame.dto.WebAppointment;
import com.pandaabc.sesame.jpa.entity.Appointment;

@Component
public class CreateOpsProcessor extends BaseProcessor implements IProcessor{
	
	@Override
	public List<WebAppointment> preprocess(List<Appointment> appointments) {
		// there could be a lot of activities in the preprocessor
		// here we only verify the incoming appointment data - time verification, duration verification, etc
		// We should also verify the appointment to be created shall not conflict with existing appointments
		return map(appointments);
	}

	@Override
	public List<WebAppointment> postprocess(List<WebAppointment> appointments) {
		appointments.forEach(appointment -> appointment.setMessage(ApptDbOpStatus.SUCCESS));
		return appointments;
	}
	
	private List<WebAppointment> map(List<Appointment> appointments) {
		return appointments
					.stream()
					.map(appointment -> {
							if (validataAppointment(appointment)) {
								return new WebAppointment(appointment, ApptDbOpStatus.TBD);
							} else {
								return new WebAppointment(appointment, ApptDbOpStatus.INVALIDDATA);
							}
						})
					.collect(Collectors.toList());
	}

}
