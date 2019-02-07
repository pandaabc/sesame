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

import com.pandaabc.sesame.jpa.entity.Appointment;
import com.pandaabc.sesame.service.ApptService;

@Component
public class RandomTaskRunner {
	
	private static ApptService staticService;
	
	private static final int BASETIME_IN_SECOND = 10;
	private static final int RANDOM_TIME_IN_SECOND = 15;
	
	@Autowired
	ApptService service;

	static Timer timer = new Timer();
	
	/**
	 * This task, runs with a minimum time interval plus a randomizer. 
	 * Each time after the timer finish the current task, it adds a new task, with a random delay.
	 * @author ywu
	 *
	 */
    static class Task extends TimerTask {
        @Override
        public void run() {
        	try {
	        	excute();
	            int delay = (BASETIME_IN_SECOND + new Random().nextInt(RANDOM_TIME_IN_SECOND)) * 1000;
	            timer.schedule(new Task(), delay);
        	} catch (Exception e) {
        		// logs ---
        	}
        }
        
    }
	
    /**
     * This method runs automatically after the bean is created, and runs the timer to schedule the task.
     */
	@PostConstruct
	public void init() {
		this.staticService = this.service;
		int delay = 30 * 1000;
		timer.schedule(new Task(), delay);
	}
	
	/**
	 * task to execute
	 */
	protected static void excute() {
		
		Appointment appointment = new Appointment();
		appointment.setCreationTime(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
		appointment.setAppointmentDateTime(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
		appointment.setAppointmentDuration(30l);
		appointment.setApptStatus("Available");
		appointment.setNameOfDoctor("Good Dr");
		appointment.setPrice(50.1);
		List<Appointment> list = new ArrayList<>();
		list.add(appointment);
		staticService.createAppointments(list);
		    		
	}
	
}
