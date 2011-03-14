package com.ocbcmcd.monitoring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ocbcmcd.monitoring.domain.User;
import com.ocbcmcd.monitoring.service.IRegistrationService;

@Service
@Transactional
public class RegistrationService implements IRegistrationService {
	
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Override
	public void register(User user) {
		hibernateTemplate.save(hibernateTemplate);
	}

}
