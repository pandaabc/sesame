package com.pandaabc.sesame.jpa;

import com.pandaabc.sesame.dto.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ApptService {

    @Autowired AppointmentJPARepo repo;

    public List<Appointment> getAppointmentsWithIds(List<Long> ids) {
        return repo.findAllById(ids);
    }

    public List<Appointment> getAppointment(Long id, LocalDateTime start, LocalDateTime end) {
        if (id != null) {
            return getAppointmentWithId(id);
        } else {
            return repo.findAppointsWithTime(start, end);
        }
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
