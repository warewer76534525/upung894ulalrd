package com.ocbcmcd.monitoring.stub;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.ocbcmcd.monitoring.validator.EmailValidator;


public class EmailValidatorTest {
	
	private EmailValidator validator;
	
	@Before
	public void setUp() {
		validator = EmailValidator.getInstance();
	}
	
	@Test
	public void testSimpleEmail() {
		String simpleEmail = "sembiring.adi@gmail.com";
		Assert.assertTrue(validator.validate(simpleEmail));
	}
	
	@Test
	public void testMultipleEmail() {
		String simpleEmail = "adi@gmail.com,sembiring.adi@gmail.com";
		Assert.assertTrue(validator.validate(simpleEmail));
	}
}
