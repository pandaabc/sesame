package com.pandaabc.sesame.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import com.pandaabc.sesame.constant.ApptStatus;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "appointment")
public class Appointment implements Serializable{

	private static final long serialVersionUID = 3545928623380850048L;

	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "creation_time")
	@EqualsAndHashCode.Exclude
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
