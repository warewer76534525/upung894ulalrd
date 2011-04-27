package com.ocbcmcd.monitoring.validator;

import org.apache.commons.lang.StringUtils;
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
	private EmailValidator emailValidator = EmailValidator.getInstance();
	
	@Override
	public boolean supports(Class<?> clazz) {
		return MailConfigCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MailConfigCommand command = (MailConfigCommand) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "host", "mail.host.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "port", "mail.port.required");
		if (StringUtils.isNotBlank(command.getPort()) && !StringUtils.isNumeric(command.getPort())) {
			errors.rejectValue("port", "mail.port.notnumeric");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "mail.userName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "smtpProtocol", "mail.smtpProtocol.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "smtpAuth", "mail.smtpAuth.required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "from", "mail.from.required");
		if (StringUtils.isNotBlank(command.getFrom()) && !emailValidator.validate(command.getFrom())) {
			errors.rejectValue("from", "mail.from.invalid");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toEod", "mail.toEod.required");
		if (StringUtils.isNotBlank(command.getToEod()) && !emailValidator.validate(command.getToEod())) {
			errors.rejectValue("toEod", "mail.toEod.invalid");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "eodSubject","mail.eodSubject.required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toProcessedFailed", "mail.toProcessedFailed.required");
		if (StringUtils.isNotBlank(command.getToProcessedFailed()) && !emailValidator.validate(command.getToProcessedFailed())) {
			errors.rejectValue("toProcessedFailed", "mail.toProcessedFailed.invalid");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "processedFailedSubject", "mail.processedFailedSubject.required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toSentFailed", "mail.toSentFailed.required");
		if (StringUtils.isNotBlank(command.getToSentFailed()) && !emailValidator.validate(command.getToSentFailed())) {
			errors.rejectValue("toSentFailed", "mail.toSentFailed.invalid");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sentFailedSubject", "mail.sentFailedSubject.required");
	}

}