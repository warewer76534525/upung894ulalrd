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

import com.ocbcmcd.monitoring.command.LogSearchCommand;
import com.ocbcmcd.monitoring.domain.LogEvent;
import com.ocbcmcd.monitoring.query.ILogEventQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-context.xml")
public class When_search_log {

	@Autowired
	ILogEventQuery logQuery;

	
	@Before
	public void setUp() {
		Logger.getRootLogger().setLevel(Level.INFO);
		Logger.getLogger("org.springframework").setLevel(Level.WARN);
		BasicConfigurator.configure();

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
	
	@Test
	public void should_able_to_display_log_with_no_date_range() throws ParseException {		
		LogSearchCommand command = new LogSearchCommand();
		
		List<LogEvent> logs = logQuery.getLogs(command);
		Assert.assertNotSame(0, logs.size());
		System.out.println("row count: " +  logs.size());
	}
	
	@Test
	public void should_able_to_display_log_with_date_range_criteria() throws ParseException {
		LogSearchCommand command = new LogSearchCommand();
		command.setFrom("03/07/2011");
		command.setTo("03/20/2011");
		System.out.println(command);
		List<LogEvent> logs = logQuery.getLogs(command);
		
		for (LogEvent logEvent : logs) {
			LogEvent log = logQuery.getLog(logEvent.getId());
			Assert.assertEquals(logEvent.getId(), log.getId());
		}
		Assert.assertNotSame(0, logs.size());
		System.out.println("row count: " +  logs.size());
	}
	
	@Test
	public void should_able_to_display_log_with_date_range_criteria_and_file() throws ParseException {
		LogSearchCommand command = new LogSearchCommand();
		command.setFile("1");
		command.setFrom("03/07/2011");
		command.setTo("03/22/2011");
		System.out.println(command);
		List<LogEvent> logs = logQuery.getLogs(command);
		System.out.println("row count: " +  logs.size());
		
		Assert.assertNotSame(0, logs.size());
	}
}
