package com.pandaabc.sesame.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import com.pandaabc.sesame.constant.ApptStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "appointment")
public class Appointment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3545928623380850048L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "creation_time")
	private Timestamp creationTime;
	
	@Column(name = "appointment_datetime")
	private Timestamp appointmentDateTime;
	
	@Column(name = "appointment_duration")
	private Timestamp appointmentDuration;
	
	@Column(name = "name_of_doctor")
	private String nameOfDoctor;
	
	@Column(name = "appt_status")
	private ApptStatus apptStatus;
	
	@Column(name = "price")
	private double price;
	

}
