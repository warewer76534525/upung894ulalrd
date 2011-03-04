package com.ocbcmcd.monitoring;

import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ocbcmcd.message.OcbcFileSent;
import com.ocbcmcd.monitoring.domain.LogEvent;
import com.ocbcmcd.monitoring.query.ILogEventQuery;
import com.ocbcmcd.monitoring.service.IMonitoringService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-context.xml")
public class When_file_sent_successfully {
	private static final String FILE_NAME = "data.txt";
	private OcbcFileSent event;
	
	@Autowired
	IMonitoringService monitoringService;
	
	@Autowired
	ILogEventQuery logQuery;
	
	
	
	@Before
	public void setUp() {
		Logger.getRootLogger().setLevel(Level.INFO);
		Logger.getLogger("org.springframework").setLevel(Level.WARN);
		BasicConfigurator.configure();
				
		event = new OcbcFileSent(FILE_NAME);
	}

	
	@Test
	public void should_save_sent_event_to_logevent() {
		monitoringService.logFileSentEvent(event);
		List<LogEvent> logs = logQuery.getLogs();
		boolean isFound = false;
		
		System.out.println("-- " + event.getTime());
		for (LogEvent logEvent : logs) {
			if (logEvent.getTime().toString().equals(event.getTime().toString()))
				isFound = true;
		}
		
		Assert.assertTrue(isFound);
	}
	
	@Test
	public void should_able_display_sent_event_in_recent_log() {
		List<LogEvent> logs = logQuery.getLogs();
		Assert.assertNotSame(0, logs.size());
	}
}
