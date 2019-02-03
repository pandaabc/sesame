package com.pandaabc.sesame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pandaabc.sesame.jpa.AppointmentRepoImpl;
import com.pandaabc.sesame.jpa.entity.Appointment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ApptService {

    @Autowired AppointmentRepoImpl repo;

    public List<Appointment> getAppointmentsWithIds(List<Long> ids) {
        return repo.findAllById(ids);
    }

    public List<Appointment> getAppointment(Long id, String start, String end) {
    	try {
	        if (id != null) {
	            return getAppointmentWithId(id);
	        } else {
	            return repo.findAppointsWithTime(start, end);
	        }
    	} catch (Exception e) {
    		
    	}
    	return new ArrayList<>();
    }
    
    public List<Appointment> getAppointmentWithId(long id) {
        List<Appointment> res = new ArrayList<>();
        Optional<Appointment> appointment = repo.findById(id);
        if (appointment.isPresent()) {
            res.add(appointment.get());
        }
        return res;
    }

    public boolean deleteAppointmentsWithIds(List<Long> ids) {
        List<Appointment> appointments = getAppointmentsWithIds(ids);
        repo.deleteAll(appointments);
        return true;
    }
    
    public boolean deleteAppointmentWithId(long id) {
        List<Long> ids = new ArrayList<>();
        ids.add(id);
        return deleteAppointmentsWithIds(ids);
    }

    public boolean updateAppointments(List<Appointment> appointments) {
        repo.saveAll(appointments);
        return true;
    }
    
    public boolean createAppointments (List<Appointment> appointments) {
        repo.saveAll(appointments);
        return true;
    }

}
