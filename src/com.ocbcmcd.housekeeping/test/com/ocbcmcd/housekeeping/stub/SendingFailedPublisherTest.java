package com.ocbcmcd.housekeeping.stub;

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
public class SendingFailedPublisherTest {
	private static final String FILE_NAME = "sendingFailed.txt";
	
	@Autowired
	@Qualifier("sendingFailedTemplate")
	JmsTemplate sendingFailedTemplate;

	@Before
	public void setUp() {
		Logger.getRootLogger().setLevel(Level.INFO);
		Logger.getLogger("org.springframework").setLevel(Level.WARN);
		BasicConfigurator.configure();
	}

	@Test
	public void shoud_publish_messsage_to_sending_failed_topic() {
		sendingFailedTemplate.convertAndSend(new OcbcFileSendingFailed(FILE_NAME, "Error Message", "Stacktrace"));
	}

}
