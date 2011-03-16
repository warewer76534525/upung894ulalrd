package com.ocbcmcd.monitoring.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ocbcmcd.monitoring.dao.IUserDao;
import com.ocbcmcd.monitoring.domain.User;

@Repository
public class UserDao extends GenericHibernateDao<User, Serializable>  implements IUserDao {
	
	@Autowired
	public UserDao(SessionFactory _sf) {
		super.setSessionFactory(_sf);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public User findByUserName(String userName) {
		List<User> users = getHibernateTemplate().find("FROM User u WHERE u.userName=?", userName);
		if (users.isEmpty())
			return null;
		else 
			return users.get(0);
	}

	
	

}
