package com.ocbcmcd.monitoring.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ocbcmcd.monitoring.command.DbConfigCommand;

@Component
public class DbConfigValidator implements Validator {
	protected Log log = LogFactory.getLog(getClass());
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return DbConfigCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "url", "db.url.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "db.userName.required");
	}

}