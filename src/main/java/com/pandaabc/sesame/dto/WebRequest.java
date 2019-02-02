package com.pandaabc.sesame.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebRequest implements Serializable {

	private static final long serialVersionUID = 3159882481804725690L;
	
	private List<Appointment> appointments;
	private List<Long> ids;

}
