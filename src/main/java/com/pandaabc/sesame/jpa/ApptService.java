package com.pandaabc.sesame.jpa;

import com.pandaabc.sesame.dto.Appointment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApptService {

    public List<Appointment> getAppointmentsWithIds(List<Long> ids) {
        return new ArrayList<>();
    }

    public boolean deleteAppointmentsWithIds(List<Long> ids) {
        return true;
    }

    public boolean updateAppointments(List<Appointment> appointments) {
        return true;
    }

}
