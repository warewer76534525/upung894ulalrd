package com.ocbcmcd.mailsender.mail;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;

public class When_send_email_to_mcdonaldclient {
	
	@Test
	public void Should_send_email_with_error_message() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"mail-sender-test.xml");
		MailSender mailSender = (MailSender)applicationContext.getBean("mailSender");	
		Assert.assertNotNull(mailSender);
		
		MailService mailService = applicationContext.getBean(MailService.class);
		mailService.sendErrorMessage("error file yg mengerikan", "", "");
	}
}
