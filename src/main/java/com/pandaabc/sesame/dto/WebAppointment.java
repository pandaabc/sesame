package com.pandaabc.sesame.dto;

import com.pandaabc.sesame.constant.ApptDbOpStatus;
import com.pandaabc.sesame.jpa.entity.Appointment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * webAppointment is used to return to the client
 * @author ywu
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class WebAppointment implements Serializable {

	private static final long serialVersionUID = -5993895315536315489L;
	Appointment appointment;
    ApptDbOpStatus message;

}
