package com.ocbcmcd.monitoring.stub;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	@Test
	public void convertDate() {
		DateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println(formater.format(new Date()));
	}
}
