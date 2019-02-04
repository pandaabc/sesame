package com.pandaabc.sesame.processor;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;

import com.pandaabc.sesame.constant.ApptDbOpStatus;
import com.pandaabc.sesame.dto.WebAppointment;
import com.pandaabc.sesame.service.ApptService;


@ContextConfiguration(classes={UpdateOpsProcessor.class})
public class UpdateOpsProcessorTest {
	
	@InjectMocks
	UpdateOpsProcessor processor = Mockito.spy(new UpdateOpsProcessor());
	
	@Mock
	ApptService service;
	
	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testPreprocessWithValidInput() {
		
		// setup service to return 1 valid stub when requested.
		Mockito.doReturn(ProcessorTestStubs.getAppointmentList()).when(service).getAppointmentsWithIds(ArgumentMatchers.any());
		// setup argument check for the service
		ArgumentCaptor<List> argument = ArgumentCaptor.forClass(List.class);
		// run processor
		List<WebAppointment> res = processor.preprocess(ProcessorTestStubs.getAppointmentList());
		// verify argument.  service shall receive a list of 1 id
		Mockito.verify(service).getAppointmentsWithIds(argument.capture());
		Assert.assertEquals(1, argument.getValue().size());
		// res shall be of size 1
		Assert.assertEquals(1, res.size());
		
	}
	
	@Test
	public void testPreprocessWithInvalidInput() {
		
		// setup service to return 1 invalid stub when requested.  (Status is not TBD)
		Mockito.doReturn(new ArrayList<>()).when(service).getAppointmentsWithIds(ArgumentMatchers.any());
		// setup argument check for the service
		ArgumentCaptor<List> argument = ArgumentCaptor.forClass(List.class);
		// run processor
		List<WebAppointment> res = processor.preprocess(ProcessorTestStubs.getAppointmentWithoutIdList());
		// verify argument.  service shall receive a list of 0 id
		Mockito.verify(service).getAppointmentsWithIds(argument.capture());
		Assert.assertEquals(0, argument.getValue().size());
		// res shall be of size 1
		Assert.assertEquals(1, res.size());
		Assert.assertEquals(ApptDbOpStatus.NOID, res.get(0).getMessage());
		
	}
	
	@Test
	public void testMapAppointment() {
		// when input is null
		Assert.assertEquals(ApptDbOpStatus.NULL, processor.mapAppointment(null).getMessage());
		// when input has no id
		Assert.assertEquals(ApptDbOpStatus.NOID, processor.mapAppointment(ProcessorTestStubs.getAppointmentWithoutId()).getMessage());
		// when input is valid
		Assert.assertEquals(ApptDbOpStatus.TBD, processor.mapAppointment(ProcessorTestStubs.getAppointment()).getMessage());
	}
	
	
}
