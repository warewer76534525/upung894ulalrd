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

import com.ocbcmcd.monitoring.command.UserSearchCommand;
import com.ocbcmcd.monitoring.domain.User;
import com.ocbcmcd.monitoring.query.IUserQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-context.xml")
public class When_search_user {

	@Autowired
	IUserQuery userQuery;

	
	@Before
	public void setUp() {
		Logger.getRootLogger().setLevel(Level.INFO);
		Logger.getLogger("org.springframework").setLevel(Level.WARN);
		BasicConfigurator.configure();

	}
	
	@Test
	public void should_return_user_list_with_user_criteria() {
		UserSearchCommand command = new UserSearchCommand();
		command.setUserName("a");
		List<User> users = userQuery.getUsers(command );
		
		System.out.println("user size: " + users.size());
		Assert.assertNotSame(0, users.size());
	}
}
