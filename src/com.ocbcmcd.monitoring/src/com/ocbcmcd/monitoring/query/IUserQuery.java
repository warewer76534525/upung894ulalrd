package com.ocbcmcd.monitoring.query;

import java.util.List;

import com.ocbcmcd.monitoring.command.UserSearchCommand;
import com.ocbcmcd.monitoring.domain.User;

public interface IUserQuery {

	List<User> getUsers();

	List<User> getUsers(UserSearchCommand command);

}
