package com.ocbcmcd.monitoring;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.ocbcmcd.message.OcbcFileSendingFailed;
import com.ocbcmcd.monitoring.domain.LogEvent;
import com.ocbcmcd.monitoring.query.ILogEventQuery;
import com.ocbcmcd.monitoring.service.IMonitoringService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-context.xml")
public class When_file_sending_failed_received {
	private static final String FILE_NAME = "sendinfFailed.txt";
	private OcbcFileSendingFailed event;
	
	@Autowired
	IMonitoringService monitoringService;
	
	@Autowired
	ILogEventQuery logQuery;

	
	@Before
	public void setUp() {
		Logger.getRootLogger().setLevel(Level.INFO);
		Logger.getLogger("org.springframework").setLevel(Level.WARN);
		BasicConfigurator.configure();
				
		event = new OcbcFileSendingFailed(FILE_NAME, "FTP ERROR", "FTP ERROR");
	}

	
	@Test
	public void should_save_data_to_logevent() {
		monitoringService.logSendingFailed(event);
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
	public void should_able_display_in_recent_log() {
		List<LogEvent> logs = logQuery.getLogs();
		Assert.assertNotSame(0, logs.size());
	}
	
	@Test
	public void should_able_to_display_log_with_criteria() throws ParseException {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		
		Date startDate = formater.parse("2011-03-07");
		Date endDate = formater.parse("2011-03-30");
		List<LogEvent> logs = logQuery.getLogs(startDate, endDate);
		Assert.assertNotSame(0, logs.size());
		System.out.println("row count: " +  logs.size());
	}
	
}
