package com.pandaabc.sesame.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pandaabc.sesame.jpa.entity.Appointment;

import java.util.List;

@Repository
public interface AppointmentRepoImpl extends JpaRepository<Appointment, Long> {

	// note in query, the table name needs to match the class name (same applies to the field)
    @Query(value = "SELECT * FROM appointment where creation_time between ?1 and ?2", 
    		nativeQuery = true)
    List<Appointment> findAppointsWithTime(String start, String end);
}
