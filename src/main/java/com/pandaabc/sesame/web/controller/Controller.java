package com.pandaabc.sesame.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pandaabc.sesame.dto.WebResponse;

@RestController
@RequestMapping("/sesame")
public class Controller {
	
	
	
	public WebResponse getAppointmentById() {
		WebResponse response = new WebResponse();
		
		return response;
	}
	
	

}
