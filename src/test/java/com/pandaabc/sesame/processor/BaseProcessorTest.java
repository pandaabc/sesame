package com.pandaabc.sesame.processor;

import org.junit.Assert;
import org.junit.Test;

import com.pandaabc.sesame.jpa.entity.Appointment;

public class BaseProcessorTest {
	
	BaseProcessor baseProcessor = new BaseProcessor();

	@Test
	public void testValidataAppointment() {
		// test null input
		Assert.assertFalse(baseProcessor.validataAppointment(null));
		// test empty input
		Appointment appointment = new Appointment();
		Assert.assertFalse(baseProcessor.validataAppointment(appointment));
		// test valid input
		appointment = ProcessorTestStubs.getAppointment();
		Assert.assertTrue(baseProcessor.validataAppointment(appointment));
		// test negative duration
		appointment = ProcessorTestStubs.getAppointment();
		appointment.setAppointmentDuration(-30l);
		Assert.assertFalse(baseProcessor.validataAppointment(appointment));
		// test null duration
		appointment = ProcessorTestStubs.getAppointment();
		appointment.setAppointmentDuration(null);
		Assert.assertFalse(baseProcessor.validataAppointment(appointment));
		// test null dr
		appointment = ProcessorTestStubs.getAppointment();
		appointment.setNameOfDoctor(null);
		Assert.assertFalse(baseProcessor.validataAppointment(appointment));
		// test null appt status
		appointment = ProcessorTestStubs.getAppointment();
		appointment.setApptStatus(null);
		Assert.assertFalse(baseProcessor.validataAppointment(appointment));
		
	}
	
}
