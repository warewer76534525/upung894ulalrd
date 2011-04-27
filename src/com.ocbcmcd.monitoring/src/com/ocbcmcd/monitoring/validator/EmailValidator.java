package com.ocbcmcd.monitoring.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
	
	private static EmailValidator _emailValidator;
	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String EMAIL_SPARATOR = ",";
	

	private EmailValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	public static EmailValidator getInstance() {
		if (_emailValidator == null) {
			_emailValidator = new EmailValidator();
		}
		
		return _emailValidator;
	}


	public boolean validate(final String email) {
		if (email.contains(EMAIL_SPARATOR)) {
			String[] tempMails = email.split(EMAIL_SPARATOR);
			for (String tempMail : tempMails) {
				matcher = pattern.matcher(tempMail.trim());
				
				if (!matcher.matches()) {
					return false;
				}
			}
			
			return true;
		}
		
		matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
