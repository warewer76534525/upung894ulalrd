package com.ocbcmcd.monitoring.service;

import com.ocbcmcd.monitoring.domain.RegistrationCommand;
import com.ocbcmcd.monitoring.domain.User;
import com.ocbcmcd.monitoring.exception.UserNotFoundException;

public interface IRegistrationService {

	void register(RegistrationCommand user);
	User getUser(int id);
	void disable(int userId) throws UserNotFoundException;
	void enable(int userId) throws UserNotFoundException;
}
