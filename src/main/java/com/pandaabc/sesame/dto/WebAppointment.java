package com.pandaabc.sesame.dto;

import com.pandaabc.sesame.constant.ApptDbOpStatus;
import com.pandaabc.sesame.jpa.entity.Appointment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class WebAppointment implements Serializable {

    Appointment appointment;
    ApptDbOpStatus message;

}
