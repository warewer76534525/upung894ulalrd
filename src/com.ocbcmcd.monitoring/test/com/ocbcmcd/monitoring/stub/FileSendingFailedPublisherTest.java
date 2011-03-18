package com.ocbcmcd.monitoring.stub;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ocbcmcd.message.OcbcFileSendingFailed;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:jms-context.xml")
public class FileSendingFailedPublisherTest {
	private static final String FILE_NAME = "processedFailed.txt";
	
	@Autowired
	@Qualifier("sendingFailedJmsTemplate")
	JmsTemplate sendingFailedJmsTemplate;

	@Before
	public void setUp() {
		Logger.getRootLogger().setLevel(Level.INFO);
		Logger.getLogger("org.springframework").setLevel(Level.WARN);
		BasicConfigurator.configure();
	}

	@Test
	public void shoud_publish_messsage_to_sending_failed_topic() {
		System.out.println("sent OcbcFileSent");
		sendingFailedJmsTemplate.convertAndSend(new OcbcFileSendingFailed(FILE_NAME, "FTP ERROR", "FTP ERROR"));
	}
	
}
