package com.pandaabc.sesame.mapper;

import com.pandaabc.sesame.constant.ApptDbOpStatus;
import com.pandaabc.sesame.dto.WebAppointment;
import com.pandaabc.sesame.jpa.entity.Appointment;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApptWebDtoMapper {

    public WebAppointment map (Appointment appointment) {

        if (appointment == null) {
            return new WebAppointment(null, ApptDbOpStatus.NOTEXIST);
        } else {
            return new WebAppointment(appointment, ApptDbOpStatus.SUCCESS);
        }

    }

    public List<WebAppointment> map (List<Appointment> appointments) {

        if (CollectionUtils.isEmpty(appointments)) {
            return new ArrayList<>();
        }

        return appointments.stream().map(appointment -> map(appointment)).collect(Collectors.toList());

    }

}
