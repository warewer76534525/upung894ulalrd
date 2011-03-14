package com.ocbcmcd.monitoring.service;

import com.ocbcmcd.monitoring.domain.RegistrationCommand;
import com.ocbcmcd.monitoring.domain.User;

public interface IRegistrationService {

	void register(RegistrationCommand user);

}
