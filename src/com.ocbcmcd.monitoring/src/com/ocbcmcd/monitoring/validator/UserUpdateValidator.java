package com.ocbcmcd.monitoring.validator;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ocbcmcd.monitoring.command.UserUpdateCommand;
import com.ocbcmcd.monitoring.domain.User;

@Component
@Scope("prototype")
public class UserUpdateValidator  implements Validator {
	protected Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
		
	@Override
	public boolean supports(Class<?> clazz) {
		return UserUpdateCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserUpdateCommand command = (UserUpdateCommand) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "userName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword", "currentPassword.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "confirmPassword.required");
		
		if (!StringUtils.isEmpty(command.getCurrentPassword()) && !StringUtils.isEmpty(command.getPassword())) {
			User user = hibernateTemplate.get(User.class, command.getId());
			if (!user.passwordEquals(command.getHashedCurrentPassword())) {
				errors.rejectValue("currentPassword", "currentPassword.match");
			} else if (!StringUtils.isEmpty(command.getPassword()) && !StringUtils.isEmpty(command.getConfirmPassword()) && !command.isEqualsToConfirmPassword()) {
				errors.rejectValue("password", "password.match");
			}
		}
				
	}

}
