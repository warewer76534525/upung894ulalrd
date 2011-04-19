package com.ocbcmcd.monitoring;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ocbcmcd.monitoring.service.impl.ConfigurerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/configtest-context.xml")
public class When_mail_config_update {
	
	@Autowired
	private ConfigurerService mailConfigurerService;
	private MailConfigCommand _mailConfig;
	
	@Before
	public void setUp() {
		_mailConfig =  new MailConfigCommand();
		
		_mailConfig.setHost("smtp.gmail.comx");
		_mailConfig.setPort("587x");
		_mailConfig.setUserName("triplelands@gmail.comx");
		_mailConfig.setPassword("tripl3landsx");
		_mailConfig.setSmtpProtocol("smtpx");
		_mailConfig.setSmtpAuth("true");
		_mailConfig.setFrom("triplelands@gmail.comx");
		_mailConfig.setToEod("exception.smsdong@gmail.comx");
		_mailConfig.setEodSubject("mail.eod.subject=EOD Subjectx");
		_mailConfig.setToProcessedFailed("exception.smsdong@gmail.comx");
		_mailConfig.setProcessedFailedSubject("Process Failed Subjectx");
		_mailConfig.setToSentFailed("exception.smsdong@gmail.comx");
		_mailConfig.setSentFailedSubject("Sent Failed Subjectx");
	}
	
	@Test
	public void should_able_to_display_list_smtp_config() {
		MailConfigCommand mailConfig = mailConfigurerService.getMailConfig();
		
		System.out.println(mailConfig);
		
		
		Assert.assertNotNull(mailConfig.getHost());
		Assert.assertNotNull(mailConfig.getPort());
		Assert.assertNotNull(mailConfig.getUserName());
		Assert.assertNotNull(mailConfig.getPassword());
		
		Assert.assertNotNull(mailConfig.getSmtpProtocol());
		Assert.assertNotNull(mailConfig.getSmtpAuth());
		Assert.assertNotNull(mailConfig.getFrom());
		Assert.assertNotNull(mailConfig.getToEod());
		Assert.assertNotNull(mailConfig.getToProcessedFailed());
		Assert.assertNotNull(mailConfig.getToSentFailed());
		Assert.assertNotNull(mailConfig.getEodSubject());
		Assert.assertNotNull(mailConfig.getProcessedFailedSubject());
		Assert.assertNotNull(mailConfig.getSentFailedSubject());
	}
	
	@Test
	public void should_change_mail_config_value() {
		mailConfigurerService.saveConfig(_mailConfig);
		MailConfigCommand mailConfig = mailConfigurerService.getMailConfig();
		
		Assert.assertEquals(mailConfig.getHost(), _mailConfig.getHost());
		Assert.assertEquals(mailConfig.getPort(), _mailConfig.getPort());
		Assert.assertEquals(mailConfig.getUserName(), _mailConfig.getUserName());
		Assert.assertEquals(mailConfig.getPassword(), _mailConfig.getPassword());
		
		Assert.assertEquals(mailConfig.getSmtpProtocol(), _mailConfig.getSmtpProtocol());
		Assert.assertEquals(mailConfig.getSmtpAuth(), _mailConfig.getSmtpAuth());
		Assert.assertEquals(mailConfig.getFrom(), _mailConfig.getFrom());
		Assert.assertEquals(mailConfig.getToEod(), _mailConfig.getToEod());
		Assert.assertEquals(mailConfig.getToProcessedFailed(), _mailConfig.getToProcessedFailed());
		Assert.assertEquals(mailConfig.getToSentFailed(), _mailConfig.getToSentFailed());
		Assert.assertEquals(mailConfig.getEodSubject(), _mailConfig.getEodSubject());
		Assert.assertEquals(mailConfig.getProcessedFailedSubject(), _mailConfig.getProcessedFailedSubject());
		Assert.assertEquals(mailConfig.getSentFailedSubject(), _mailConfig.getSentFailedSubject());
	}
}
