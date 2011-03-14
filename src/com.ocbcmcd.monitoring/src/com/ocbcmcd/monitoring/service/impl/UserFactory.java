package com.ocbcmcd.monitoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.ocbcmcd.monitoring.common.MD5;
import com.ocbcmcd.monitoring.domain.RegistrationCommand;
import com.ocbcmcd.monitoring.domain.Role;
import com.ocbcmcd.monitoring.domain.User;

@Component
public class UserFactory {
	private static final Object ROLE_ADMIN = "ROLE_ADMIN";
	
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	public User createUser(RegistrationCommand command) {
		User user = new User();
		
		user.setUserName(command.getUserName());
		try {
			user.setPassword(MD5.hash(command.getPassword()));
		} catch (Exception e) {
		}
		
		List<Role> roles = hibernateTemplate.find("FROM Role r WHERE r.roleName=?", ROLE_ADMIN);
		user.addRole(roles.get(0));
		
		return user;
	} 
}
