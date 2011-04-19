package com.ocbcmcd.monitoring.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ocbcmcd.monitoring.command.FtpConfigCommand;

@Component
public class FtpConfigValidator implements Validator {
	protected Log log = LogFactory.getLog(getClass());
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return FtpConfigCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "host", "ftp.host.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "port", "ftp.port.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "ftp.userName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "remoteDir", "ftp.remoteDir.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "chkDir", "ftp.chkDir.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reportDir", "ftp.reportDir.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "maxRetry", "ftp.maxRetry.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "retryInterval", "ftp.retryInterval.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "watcherRetry", "ftp.watcherRetry.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "watcherInterval", "ftp.watcherInterval.required");
		
	}

}