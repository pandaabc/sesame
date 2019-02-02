package com.pandaabc.sesame.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.loader.WebappClassLoader;

@Getter
@Setter
public class WebResponse implements Serializable {

	
	private static final long serialVersionUID = -5773005644331973079L;
	
	private List<WebAppointment> appointments;
	private String serverID;
	private int resultCode;
	private String resultMessage;
	
}
