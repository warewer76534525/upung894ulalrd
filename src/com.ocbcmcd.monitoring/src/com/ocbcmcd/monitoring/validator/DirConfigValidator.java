package com.ocbcmcd.monitoring.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ocbcmcd.monitoring.command.DirConfigCommand;

@Component
public class DirConfigValidator implements Validator {
	protected Log log = LogFactory.getLog(getClass());
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return DirConfigCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,  "incomingDir" ,"dir.incomingDir.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,  "encryptedDir" ,"dir.encryptedDir.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,  "processingDir" ,"dir.processingDir.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,  "outgoingDir" ,"dir.outgoingDir.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,  "dailyReportDir" ,"dir.dailyReportDir.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,  "failedDir" ,"dir.failedDir.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,  "eomScheduler" ,"dir.eomScheduler.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,  "eomSchedulerStatus" ,"dir.eomSchedulerStatus.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,  "eodScheduler" ,"dir.eodScheduler.required");
	}

}