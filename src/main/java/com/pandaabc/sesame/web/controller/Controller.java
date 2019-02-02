package com.pandaabc.sesame.web.controller;

import com.pandaabc.sesame.constant.ApptDbOpStatus;
import com.pandaabc.sesame.dto.WebAppointment;
import com.pandaabc.sesame.dto.WebRequest;
import com.pandaabc.sesame.jpa.ApptService;

import com.pandaabc.sesame.mapper.ApptWebDtoMapper;
import com.pandaabc.sesame.processor.UpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pandaabc.sesame.dto.WebResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sesame")
public class Controller {

	@Autowired
	ApptService service;

	@Autowired
	ApptWebDtoMapper mapper;

	@Autowired
	UpdateProcessor updateProcessor;

	private static final String SUCCESS = "SUCCESS";
	private static final String ERROR = "ERROR DELETING";
	
	@PostMapping("/find/with-ids/")
	public WebResponse getAppointmentByIds(WebRequest request) {
		// check request
		if (CollectionUtils.isEmpty(request.getIds())) {
			return getDefaultInvalidInputWebResponse();
		}

		WebResponse response = new WebResponse();
		// get appointments for all ids
		List<WebAppointment> appointments = service.getAppointmentsWithIds(request.getIds())
													.stream()
													.map(appointment -> mapper.map(appointment))
													.collect(Collectors.toList());
		// set basic response info and return
		response.setAppointments(appointments);
		response.setResultMessage(SUCCESS);
		response.setResultCode(200);
		response.setServerID("TEST-SERVER");

		return response;
	}

	@PostMapping("/delete/with-ids/")
	public WebResponse deleteAppointmentByIds(WebRequest request) {
		// check request
		if (CollectionUtils.isEmpty(request.getIds())) {
			return getDefaultInvalidInputWebResponse();
		}

		WebResponse response = new WebResponse();
		// get appointments for all ids
		boolean isDeleted = service.deleteAppointmentsWithIds(request.getIds());
		// set basic response info and return
		response.setResultMessage(isDeleted ? SUCCESS : ERROR);
		response.setResultCode(200);
		response.setServerID("TEST-SERVER");

		return response;
	}


	@PostMapping("/update/")
	public WebResponse updateAppointmentWithId(WebRequest request) {
		// check request
		if (CollectionUtils.isEmpty(request.getAppointments())) {
			return getDefaultInvalidInputWebResponse();
		}

		WebResponse response = new WebResponse();
		// preProcess the request
		List<WebAppointment> webAppointments = updateProcessor.preProcess(request.getAppointments());
		// update appointments
		service.updateAppointments(webAppointments.stream()
													.filter(webAppointment -> ApptDbOpStatus.TBD.equals(webAppointment.getMessage()))
													.map(webAppointment -> webAppointment.getAppointment())
													.collect(Collectors.toList()));
		// postProcess information
		webAppointments = updateProcessor.postProcess(webAppointments);

		response.setAppointments(webAppointments);
		response.setResultCode(200);
		response.setServerID("TEST-SERVER");

		return response;
	}

	@PostMapping("/create/")
	public WebResponse createAppointments(WebRequest request) {

		

	}

	private WebResponse getDefaultInvalidInputWebResponse() {
		WebResponse response = new WebResponse();
		response.setResultCode(200);
		response.setResultMessage("ID NOT VALID.  Please provide id list.");
		return response;
	}
	
	

}
