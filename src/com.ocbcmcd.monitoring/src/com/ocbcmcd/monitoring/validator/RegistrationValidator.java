package com.ocbcmcd.monitoring.validator;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ocbcmcd.monitoring.dao.IUserDao;
import com.ocbcmcd.monitoring.domain.RegistrationCommand;

@Component
@Scope("prototype")
public class RegistrationValidator  implements Validator {
	protected Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private IUserDao userDao;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return RegistrationCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		RegistrationCommand command = (RegistrationCommand) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "userName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "password.required");
		
		if (!StringUtils.isEmpty(command.getConfirmPassword()) && !command.isEqualsToConfirmPassword()) {
			errors.rejectValue("password", "password.match");
		}
		
		if (userDao.findByUserName(command.getUserName().trim()) != null) {
			errors.rejectValue("userName", "userName.exists");
		}
		
	}

}
