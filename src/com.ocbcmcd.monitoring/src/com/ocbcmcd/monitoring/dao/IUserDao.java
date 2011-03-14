package com.ocbcmcd.monitoring.dao;

import com.ocbcmcd.monitoring.domain.User;

public interface IUserDao {

	User findById(int id);

	void save(User user);

}
