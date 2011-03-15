package com.ocbcmcd.monitoring.dao;

import java.io.Serializable;

import com.ocbcmcd.monitoring.domain.User;

public interface IUserDao extends IGenericDao<User, Serializable> {
	User findByUserName(String userName);
}
