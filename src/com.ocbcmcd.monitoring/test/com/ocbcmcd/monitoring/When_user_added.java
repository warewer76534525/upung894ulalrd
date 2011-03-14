package com.ocbcmcd.monitoring;

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

import com.ocbcmcd.monitoring.dao.IUserDao;
import com.ocbcmcd.monitoring.domain.User;
import com.ocbcmcd.monitoring.service.IRegistrationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-context.xml")
public class When_user_added {
	@Autowired
	IRegistrationService registrationService;
	
	@Autowired 
	HibernateTemplate hibernateTemplate;
	
	User user = null;
	
	@Before
	public void setUp() {
		Logger.getRootLogger().setLevel(Level.INFO);
		Logger.getLogger("org.springframework").setLevel(Level.WARN);
		BasicConfigurator.configure();
		
		user = new User();
		user.setUserName("tony");
		user.setPassword("password");
	}
	
	@Test
	public void should_able_to_get_user_detail() {
		registrationService.register(user);
		User tempUser = hibernateTemplate.get(User.class, user.getId());
		Assert.assertEquals(user.getUserName(), tempUser.getUserName());
		Assert.assertEquals(user.getPassword(), tempUser.getPassword());
		Assert.assertEquals(user.getEnabled(), tempUser.getEnabled());
	}

	public void should_be_exists_in_user_list() {
		
	}
	
	public void should_able_to_change_password() {
		
	}
	
	public void should_have_default_role_as_admin() {
		
	}
	
}
