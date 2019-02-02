package com.pandaabc.sesame.processor;

import com.pandaabc.sesame.constant.ApptDbOpStatus;
import com.pandaabc.sesame.dto.Appointment;
import com.pandaabc.sesame.dto.WebAppointment;
import com.pandaabc.sesame.jpa.ApptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UpdateOpsProcessor extends BaseProcessor implements IProcessor {

    @Autowired
    ApptService service;

    @Override
    public List<WebAppointment> preprocess(List<Appointment> appointments) {
        // remove appointments without id
        List<WebAppointment> webAppointments =  appointments.stream()
                                                        .map(appointment -> mapAppointment(appointment))
                                                        .collect(Collectors.toList());
        // make it to map
        Map<Long, WebAppointment> webAppointmentsById = webAppointments.stream()
                                                                .filter(webAppointment -> ApptDbOpStatus.TBD.equals(webAppointment.getMessage()))
                                                                .collect(Collectors.toMap(webAppointment -> webAppointment.getAppointment().getId(), webAppointment -> webAppointment));
        // get appointments from db, from the ids from the request
        Map<Long, Appointment> existingAppointmentById = service.getAppointmentsWithIds(new ArrayList<>(webAppointmentsById.keySet()))
                                                            .stream()
                                                            .collect(Collectors.toMap(Appointment::getId, appointment -> appointment));
        // compare the requested and existing, and reset status
        webAppointmentsById.entrySet().forEach(entry -> precheckAgainstDbData(entry.getValue(), existingAppointmentById.get(entry.getKey())));

        return webAppointments;
    }

    @Override
    public List<WebAppointment> postprocess(List<WebAppointment> appointments) {
        // convert webappointments to map
        Map<Long, WebAppointment> webAppointmentsById = appointments.stream()
                                                            .filter(appointment -> ApptDbOpStatus.TBD.equals(appointment.getMessage()))
                                                            .collect(Collectors.toMap(appointment -> appointment.getAppointment().getId(), appointment -> appointment));
        // get appointments from the database
        Map<Long, Appointment> updatedAppointmentById = service.getAppointmentsWithIds(new ArrayList<>(webAppointmentsById.keySet()))
                                                                .stream()
                                                                .collect(Collectors.toMap(Appointment::getId, appointment -> appointment));
        // compare and reset status
        webAppointmentsById.entrySet().forEach(entry -> postcheckAgainstDbData(entry.getValue(), updatedAppointmentById.get(entry.getKey())));

        return appointments;
    }

    private WebAppointment mapAppointment(Appointment appointment) {
        if (appointment == null) {
            return new WebAppointment(null, ApptDbOpStatus.NULL);
        } else if (appointment.getId() == null) {
            return new WebAppointment(appointment, ApptDbOpStatus.NOID);
        } else if (!validataAppointment(appointment)) {
        	return new WebAppointment(appointment, ApptDbOpStatus.INVALIDDATA);
        }
        
        return new WebAppointment(appointment, ApptDbOpStatus.TBD);
    }

    private void precheckAgainstDbData(WebAppointment requestAppointment, Appointment dbAppointment) {

        if (dbAppointment == null) {
            requestAppointment.setMessage(ApptDbOpStatus.NOTEXIST);
        } else if (requestAppointment.getAppointment().equals(dbAppointment)) {
            requestAppointment.setMessage(ApptDbOpStatus.NOCHANGE);
        }

    }

    private void postcheckAgainstDbData(WebAppointment requestAppointment, Appointment dbAppointment) {

        if (requestAppointment.getAppointment().equals(dbAppointment)) {
            requestAppointment.setMessage(ApptDbOpStatus.SUCCESS);
        } else {
            requestAppointment.setMessage(ApptDbOpStatus.FAILURE);
        }

    }



}
