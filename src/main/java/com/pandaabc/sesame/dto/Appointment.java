package com.pandaabc.sesame.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
	@Temporal(TemporalType.TIMESTAMP)
	@EqualsAndHashCode.Exclude
	private LocalDateTime creationTime;
	
	@Column(name = "appointment_datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime appointmentDateTime;
	
	@Column(name = "appointment_duration")
	private Long appointmentDuration;
	
	@Column(name = "name_of_doctor")
	private String nameOfDoctor;
	
	@Column(name = "appt_status")
	private ApptStatus apptStatus;
	
	@Column(name = "price")
	private Double price;
	

}
