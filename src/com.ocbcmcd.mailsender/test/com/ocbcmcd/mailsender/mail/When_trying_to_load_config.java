package com.ocbcmcd.mailsender.mail;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/config-test.xml")
public class When_trying_to_load_config {
	
	@Value("${mail.password}")
	private String mailPassword;
	
	@Test
	public void Should_return_value() {
		Assert.assertEquals("tripl3lands", mailPassword);
	}
}
