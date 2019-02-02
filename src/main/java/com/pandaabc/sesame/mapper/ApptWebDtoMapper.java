package com.pandaabc.sesame.mapper;

import com.pandaabc.sesame.constant.ApptDbOpStatus;
import com.pandaabc.sesame.dto.Appointment;
import com.pandaabc.sesame.dto.WebAppointment;
import org.springframework.stereotype.Component;

@Component
public class ApptWebDtoMapper {

    public WebAppointment map (Appointment appointment) {

        if (appointment == null) {
            return new WebAppointment(null, ApptDbOpStatus.NOTEXIST);
        } else {
            return new WebAppointment(appointment, ApptDbOpStatus.SUCCESS);
        }

    }

}
