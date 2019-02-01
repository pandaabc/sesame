package com.pandaabc.sesame.web.controller;

import com.pandaabc.sesame.dto.Appointment;
import com.pandaabc.sesame.jpa.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pandaabc.sesame.dto.WebResponse;

import java.util.List;

@RestController
@RequestMapping("/sesame")
public class Controller {

	@Autowired
	Supplier supplier;
	
	@GetMapping("/with-id/{id}")
	public WebResponse getAppointmentById(@PathVariable long id) {
		WebResponse response = new WebResponse();

		List<Appointment> appts = supplier.getAppointWithId(id);

		if (appts == null) {
			response.setResultMessage("Not available.");
		} else {
			response.setAppts(appts);
		}

		response.setResultCode(200);
		response.setServerID("TEST-SERVER");

		return response;
	}


	@GetMapping("/with-id/{id}")
	public WebResponse getAppointmentById(@PathVariable long id) {
		WebResponse response = new WebResponse();

		List<Appointment> appts = supplier.getAppointWithId(id);

		if (appts == null) {
			response.setResultMessage("Not available.");
		} else {
			response.setAppts(appts);
		}

		response.setResultCode(200);
		response.setServerID("TEST-SERVER");

		return response;
	}
	
	

}
