package com.pandaabc.sesame.jpa;

import com.pandaabc.sesame.dto.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentJPARepo extends JpaRepository<Appointment, Long> {

    @Query("select a from appointment a where a.creation_time between ?1 and ?2")
    List<Appointment> findAppointsWithTime(LocalDateTime start, LocalDateTime end);
}
