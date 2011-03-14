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
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ocbcmcd.monitoring.common.MD5;
import com.ocbcmcd.monitoring.domain.RegistrationCommand;
import com.ocbcmcd.monitoring.domain.User;
import com.ocbcmcd.monitoring.service.IRegistrationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-context.xml")
public class When_user_added {
	@Autowired
	IRegistrationService registrationService;
	
	@Autowired 
	HibernateTemplate hibernateTemplate;
	
	RegistrationCommand user = null;
	
	@Before
	public void setUp() {
		Logger.getRootLogger().setLevel(Level.INFO);
		Logger.getLogger("org.springframework").setLevel(Level.WARN);
		BasicConfigurator.configure();
		
		user = new RegistrationCommand();
		user.setUserName("tony");
		user.setPassword("password");
	}
	
	@Test
	public void should_able_to_get_user_detail() {
		registrationService.register(user);
		@SuppressWarnings("unchecked")
		List<User> tempUsers = hibernateTemplate.find("FROM User u WHERE u.userName=?", user.getUserName());
		User tempUser = tempUsers.get(0);
		Assert.assertEquals(user.getUserName(), tempUser.getUserName());
		try {
			Assert.assertEquals(MD5.hash(user.getPassword()), tempUser.getPassword());
		} catch (Exception e) {
			
		}
	}
	
	
	@Test
	public void should_have_default_role_as_admin() {
		
	}
	
	@Test
	public void should_be_exists_in_user_list() {
		
	}
	
	public void should_able_to_change_password() {
		
	}
	
	
	
}
