package com.ocbcmcd.monitoring.validator;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ocbcmcd.monitoring.command.AdminUpdateUserCommand;

@Component 
@Scope("prototype")
public class AdminUserUpdateValidator  implements Validator {
	protected Log log = LogFactory.getLog(getClass());
		
	@Override
	public boolean supports(Class<?> clazz) {
		return AdminUpdateUserCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AdminUpdateUserCommand command = (AdminUpdateUserCommand) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "userName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "confirmPassword.required");
		
		
		if (!StringUtils.isEmpty(command.getPassword()) && !StringUtils.isEmpty(command.getConfirmPassword()) && !command.isEqualsToConfirmPassword()) {
			errors.rejectValue("password", "password.match");
		}
				
	}

}
