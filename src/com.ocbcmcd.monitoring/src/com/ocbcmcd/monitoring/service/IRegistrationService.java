package com.ocbcmcd.monitoring.service;

import com.ocbcmcd.monitoring.command.AdminUpdateUserCommand;
import com.ocbcmcd.monitoring.command.RegistrationCommand;
import com.ocbcmcd.monitoring.command.UserUpdateCommand;
import com.ocbcmcd.monitoring.domain.User;
import com.ocbcmcd.monitoring.exception.UserNotFoundException;
 
public interface IRegistrationService {

	void register(RegistrationCommand user);
	User getUser(int id);
	User getUser(String userName);
	void disable(int userId) throws UserNotFoundException;
	void enable(int userId) throws UserNotFoundException;
	void update(UserUpdateCommand user) throws UserNotFoundException;
	void update(AdminUpdateUserCommand command) throws UserNotFoundException;
}
