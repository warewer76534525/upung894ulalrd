package com.ocbcmcd.monitoring;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ocbcmcd.monitoring.command.RegistrationCommand;
import com.ocbcmcd.monitoring.command.UserType;
import com.ocbcmcd.monitoring.common.MD5;
import com.ocbcmcd.monitoring.dao.IUserDao;
import com.ocbcmcd.monitoring.domain.Role;
import com.ocbcmcd.monitoring.domain.User;
import com.ocbcmcd.monitoring.query.IUserQuery;
import com.ocbcmcd.monitoring.service.IRegistrationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-context.xml")
public class When_user_added_regular_user {
	@Autowired
	IRegistrationService registrationService;
	
	@Autowired
	IUserQuery userQuery;
	
	@Autowired 
	HibernateTemplate hibernateTemplate;
	
	@Autowired
	IUserDao userDao;
	
	RegistrationCommand user = null;
	
	@Before
	public void setUp() {
		Logger.getRootLogger().setLevel(Level.INFO);
		Logger.getLogger("org.springframework").setLevel(Level.WARN);
		BasicConfigurator.configure();
		
		String password = "password";
		user = new RegistrationCommand();
		user.setUserName("tony");
		user.setPassword(password);
		user.setConfirmPassword(password);
		user.setUserType(UserType.REGULAR_TYPE);
	}
	
	@After
	public void after() { 
		System.out.println("After class");
		userDao.delete(userDao.findByUserName(user.getUserName()));
	}
	
	
	@Test
	public void should_able_to_get_user_detail() {
		registrationService.register(user);
		User tempUser = userDao.findByUserName(user.getUserName());
		Assert.assertEquals(user.getUserName(), tempUser.getUserName());
		try {
			Assert.assertEquals(MD5.hash(user.getPassword()), tempUser.getPassword());
		} catch (Exception e) {
		}
	}
	
	
	@Test
	@Transactional
	public void should_have_default_role_as_regular_user() {
		boolean roleFound = false;
		registrationService.register(user);
		
		User tempUser = userDao.findByUserName(user.getUserName());
		Set<Role> set = tempUser.getUserRoles();
		
		Iterator<Role> itr = set.iterator();
		while (itr.hasNext()) {
			Role role = (Role) itr.next();
			if (role.getRoleName().equals(UserType.REGULAR_TYPE));
				roleFound = true;
		}
		
		Assert.assertTrue(roleFound);
		
	}
	
	@Test
	public void should_be_exists_in_user_list() {
		boolean userFound = false;
		registrationService.register(user);
		
		List<User> users = userQuery.getUsers();
		for (User user : users) {
			if (user.getUserName().equals(user.getUserName())) 
				userFound = true;
		}
		
		Assert.assertTrue(userFound);
	}
	
	
	
}
