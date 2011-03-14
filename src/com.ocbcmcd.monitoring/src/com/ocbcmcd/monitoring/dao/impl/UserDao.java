package com.ocbcmcd.monitoring.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ocbcmcd.monitoring.dao.IUserDao;
import com.ocbcmcd.monitoring.domain.User;

public class UserDao extends HibernateDaoSupport implements IUserDao {

	@Override
	public User findById(int id) {
		return null;
	}

	@Override
	public void save(User user) {
		
	}

}
