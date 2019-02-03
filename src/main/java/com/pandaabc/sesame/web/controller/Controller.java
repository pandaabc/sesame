package com.pandaabc.sesame.web.controller;

import com.pandaabc.sesame.constant.ApptDbOpStatus;
import com.pandaabc.sesame.dto.WebAppointment;
import com.pandaabc.sesame.dto.WebRequest;
import com.pandaabc.sesame.jpa.entity.Appointment;
import com.pandaabc.sesame.mapper.ApptWebDtoMapper;
import com.pandaabc.sesame.processor.CreateOpsProcessor;
import com.pandaabc.sesame.processor.UpdateOpsProcessor;
import com.pandaabc.sesame.service.ApptService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import com.pandaabc.sesame.dto.WebResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	UpdateOpsProcessor updateProcessor;
	
	@Autowired
	CreateOpsProcessor createProcessor;

	private static final String SUCCESS = "SUCCESS";
	private static final String ERROR = "ERROR DELETING";
	
	@GetMapping("/find")
	public WebResponse getAppointments(@RequestParam(required = false) Long id,
										  @RequestParam(required = false) String startTime,
										  @RequestParam(required = false) String endTime) {

		if (!isRequestValid(id, startTime, endTime)) {
			return getDefaultInvalidInputWebResponse();
		}

		WebResponse response = new WebResponse();
		
		try {
			
			List<WebAppointment> appointments = mapper.map(service.getAppointment(id, startTime, endTime));
			
			response.setAppointments(appointments);
			
		} catch (Exception e) {
			// log exception to file / splunk / message queue
		}
		
		response.setResultMessage(SUCCESS);
		response.setResultCode(200);
		response.setServerID("TEST-SERVER");

		return response;
		
	}
	
	@GetMapping("/delete/with-id/{id}")
	public WebResponse deleteAppointmentById(@PathVariable long id) {
		WebResponse response = new WebResponse();
		
		boolean isDeleted = false;
		
		try {
			
			isDeleted = service.deleteAppointmentWithId(id);
			
		} catch (Exception e) {
			// log exception to file / splunk / message queue
		}
		
		response.setResultMessage(isDeleted ? SUCCESS : ERROR);
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
		boolean isDeleted = false;
		try {
			// get appointments for all ids
			isDeleted = service.deleteAppointmentsWithIds(request.getIds());
		} catch (Exception e) {
			// log exception to file / splunk / message queue
		}
		// set basic response info and return
		response.setResultMessage(isDeleted ? SUCCESS : ERROR);
		response.setResultCode(200);
		response.setServerID("TEST-SERVER");

		return response;
	}


	@PostMapping("/update/")
	public WebResponse updateAppointment(WebRequest request) {
		// check request
		if (CollectionUtils.isEmpty(request.getAppointments())) {
			return getDefaultInvalidInputWebResponse();
		}

		WebResponse response = new WebResponse();
		try {
			// preProcess the request
			List<WebAppointment> webAppointments = updateProcessor.preprocess(request.getAppointments());
			// update appointments
			service.updateAppointments(getFinalAppointmentList(webAppointments));
			// postProcess information
			webAppointments = updateProcessor.postprocess(webAppointments);
			
			response.setAppointments(webAppointments);
		} catch (Exception e) {
			// log exception to file / splunk / message queue
		}

		response.setResultCode(200);
		response.setServerID("TEST-SERVER");

		return response;
	}

	@PostMapping("/create/")
	public WebResponse createAppointments(WebRequest request) {

		// check request
		if (CollectionUtils.isEmpty(request.getAppointments())) {
			return getDefaultInvalidInputWebResponse();
		}
		
		WebResponse response = new WebResponse();
		
		try {
			// preprocess the request
			List<WebAppointment> webAppointments = createProcessor.preprocess(request.getAppointments());
			// insert to table
			service.createAppointments(getFinalAppointmentList(webAppointments));
			// post process
			webAppointments = createProcessor.postprocess(webAppointments);
			
			response.setAppointments(webAppointments);
		} catch (Exception e) {
			// log exception to file / splunk / message queue
		}
		
		response.setResultCode(200);
		response.setServerID("TEST-SERVER");
		return response;

	}

	private WebResponse getDefaultInvalidInputWebResponse() {
		WebResponse response = new WebResponse();
		response.setResultCode(200);
		response.setResultMessage("INPUT NOT VALID");
		return response;
	}
	
	private List<Appointment> getFinalAppointmentList(List<WebAppointment> webAppointments) {
		return webAppointments.stream()
								.filter(webAppointment -> ApptDbOpStatus.TBD.equals(webAppointment.getMessage()))
								.map(webAppointment -> webAppointment.getAppointment())
								.collect(Collectors.toList());
	}

	private boolean isRequestValid(Long id, String startStr, String endStr) {
		
		LocalDateTime start = null;
		LocalDateTime end = null;
		
		try {
			start = startStr == null ? null : LocalDateTime.parse(startStr, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
			end = endStr == null ? null : LocalDateTime.parse(endStr, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		} catch (Exception e) {
			return false;
		}
			
		if (id != null) {
			if (start != null && end != null && start.isAfter(end)) {
				return false;
			}
			return true;
		}
		
		
		if (start != null && end != null && start.isBefore(end)) {
			return true;
		}
		return false;

	}

}
