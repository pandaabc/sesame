package com.pandaabc.sesame.schedule;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pandaabc.sesame.constant.ApptStatus;
import com.pandaabc.sesame.jpa.entity.Appointment;
import com.pandaabc.sesame.service.ApptService;

@Component
public class RandomTaskRunner {
	
	private static ApptService staticService;
	
	@Autowired
	ApptService service;

	static Timer timer = new Timer();
	
    static class Task extends TimerTask {
        @Override
        public void run() {
        	try {
	        	excute();
	            int delay = (10 + new Random().nextInt(15)) * 1000;
	            timer.schedule(new Task(), delay);
        	} catch (Exception e) {
        		// logs ---
        	}
        }
        
    }
	
	@PostConstruct
	public void init() {
		this.staticService = this.service;
		int delay = 1 * 1000;
		timer.schedule(new Task(), delay);
	}
	
	protected static void excute() {
		
		Appointment appointment = new Appointment();
		appointment.setCreationTime(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
		appointment.setAppointmentDateTime(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
		appointment.setAppointmentDuration(30l);
		appointment.setApptStatus(ApptStatus.AVAILABLE);
		appointment.setNameOfDoctor("Good Dr");
		appointment.setPrice(50.1);
		List<Appointment> list = new ArrayList<>();
		list.add(appointment);
		staticService.createAppointments(list);
		    		
	}
	
}
