package com.pandaabc.sesame.web.controller;

import com.pandaabc.sesame.dto.Appointment;
import com.pandaabc.sesame.dto.WebRequest;
import com.pandaabc.sesame.jpa.ApptService;

import org.assertj.core.util.Arrays;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pandaabc.sesame.dto.WebResponse;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sesame")
public class Controller {

	@Autowired
	ApptService service;
	
	@GetMapping("/find/with-id/{id}")
	public WebResponse getAppointmentById(@PathVariable long id) {
		WebResponse response = new WebResponse();

		List<Long> ids = new ArrayList<>();
		
		List<Appointment> appts = service.getAppointWithIds(ids);

		if (CollectionUtils.isEmpty(appts)) {
			response.setResultMessage("Not available.");
		} else {
			response.setAppts(appts);
		}

		response.setResultCode(200);
		response.setServerID("TEST-SERVER");

		return response;
	}


	@PostMapping("/update/")
	public WebResponse updateAppointmentWithId(WebRequest request) {
		WebResponse response = new WebResponse();
		
		
		
		List<Appointment> appts = service.getAppointWithId(id);

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
