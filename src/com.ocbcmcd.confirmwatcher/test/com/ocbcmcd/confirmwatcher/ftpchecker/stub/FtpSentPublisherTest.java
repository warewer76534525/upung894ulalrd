package com.ocbcmcd.confirmwatcher.ftpchecker.stub;

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

import com.ocbcmcd.message.OcbcFileSent;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:jms-context.xml")
public class FtpSentPublisherTest {
	private static final String FILE_NAME = "duplicated.txt";
	
	@Autowired
	@Qualifier("ftpSentTemplate")
	JmsTemplate ftpSentTemplate;

	@Before
	public void setUp() {
		Logger.getRootLogger().setLevel(Level.INFO);
		Logger.getLogger("org.springframework").setLevel(Level.WARN);
		BasicConfigurator.configure();
	}

	@Test
	public void shoud_file_sent_message_to_topic() {
		ftpSentTemplate.convertAndSend(new OcbcFileSent(FILE_NAME));
	}

}
