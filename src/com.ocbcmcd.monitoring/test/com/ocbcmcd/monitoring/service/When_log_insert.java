package com.ocbcmcd.monitoring.service;

import java.util.ArrayList;
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

import com.ocbcmcd.monitoring.domain.Log;
import com.ocbcmcd.monitoring.query.ILogQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-context.xml")
public class When_log_insert {
	@Autowired
	ILogService logService;
	
	@Autowired
	ILogQuery logQuery;
	
	List<Log> logs = new ArrayList<Log>();
	
	
	@Before
	public void setUp() {
		Logger.getRootLogger().setLevel(Level.INFO);
		Logger.getLogger("org.springframework").setLevel(Level.WARN);
		BasicConfigurator.configure();
				
		Log log1 = new Log("Upload XXX.txt", Log.SUCCESS, new Date());
		Log log2 = new Log("Upload BBB.txt", Log.SUCCESS, new Date());
		Log log3 = new Log("Upload BBB.txt", Log.ERROR, new Date());
		
		logs.add(log1);
		logs.add(log2);
		logs.add(log3);
	}

	
	@Test
	public void should_save_rates_in_batch_update() {
		logService.log(logs);
		List<Log> logs = logQuery.getLogs();
		Assert.assertNotSame(0, logs.size());
	}
}
