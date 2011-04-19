package com.ocbcmcd.monitoring.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ocbcmcd.monitoring.command.MailConfigCommand;

@Component
public class MailConfigValidator implements Validator {
	protected Log log = LogFactory.getLog(getClass());
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return MailConfigCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "host", "mail.host.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "port", "mail.port.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "mail.userName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "smtpProtocol", "mail.smtpProtocol.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "smtpAuth", "mail.smtpAuth.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "from", "mail.from.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toEod", "mail.toEod.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "eodSubject","mail.eodSubject.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toProcessedFailed", "mail.toProcessedFailed.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "processedFailedSubject", "mail.processedFailedSubject.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toSentFailed", "mail.toSentFailed.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sentFailedSubject", "mail.sentFailedSubject.required");
	}

}