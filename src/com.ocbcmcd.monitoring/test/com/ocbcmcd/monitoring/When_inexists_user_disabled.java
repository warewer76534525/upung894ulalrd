package com.ocbcmcd.monitoring;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ocbcmcd.monitoring.exception.UserNotFoundException;
import com.ocbcmcd.monitoring.service.IRegistrationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-context.xml")
public class When_inexists_user_disabled {
	@Autowired
	IRegistrationService registrationService;
	
	private int userId;
	
	@Before
	public void setUp() {
		Logger.getRootLogger().setLevel(Level.INFO);
		Logger.getLogger("org.springframework").setLevel(Level.WARN);
		BasicConfigurator.configure();
		
		userId = 999;
	}
	
	@Test(expected=UserNotFoundException.class)
	public void user_should_have_disabled() throws UserNotFoundException {
		registrationService.disable(userId);
	}
	
}
