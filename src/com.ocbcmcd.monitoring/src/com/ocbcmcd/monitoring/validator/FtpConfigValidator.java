package com.ocbcmcd.monitoring.validator;

import org.apache.commons.lang.StringUtils;
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
		FtpConfigCommand command = (FtpConfigCommand) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "host", "ftp.host.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "port", "ftp.port.required");
		if (StringUtils.isNotBlank(command.getPort()) && !StringUtils.isNumeric(command.getPort())) {
			errors.rejectValue("port", "ftp.port.notnumeric");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "ftp.userName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "remoteDir", "ftp.remoteDir.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "chkDir", "ftp.chkDir.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reportDir", "ftp.reportDir.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "maxRetry", "ftp.maxRetry.required");
		if (StringUtils.isNotBlank(command.getMaxRetry()) && !StringUtils.isNumeric(command.getMaxRetry())) {
			errors.rejectValue("maxRetry", "ftp.maxRetry.notnumeric");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "retryInterval", "ftp.retryInterval.required");
		if (StringUtils.isNotBlank(command.getRetryInterval()) && !StringUtils.isNumeric(command.getRetryInterval())) {
			errors.rejectValue("retryInterval", "ftp.retryInterval.notnumeric");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "watcherRetry", "ftp.watcherRetry.required");
		if (StringUtils.isNotBlank(command.getWatcherRetry()) && !StringUtils.isNumeric(command.getWatcherRetry())) {
			errors.rejectValue("watcherRetry", "ftp.watcherRetry.notnumeric");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "watcherInterval", "ftp.watcherInterval.required");
		if (StringUtils.isNotBlank(command.getWatcherInterval()) && !StringUtils.isNumeric(command.getWatcherInterval())) {
			errors.rejectValue("watcherInterval", "ftp.watcherInterval.notnumeric");
		}
		
	}

}