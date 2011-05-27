package com.ocbcmcd.ftpfilesender.eventhandler;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.Message;
import org.springframework.integration.MessageHandlingException;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsUtils;

import com.ocbcmcd.message.EncryptedFileSending;
import com.ocbcmcd.message.SapFileEncrypted;

public class EncryptedFileMessageHandler {
	protected Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	@Qualifier("processingDestination")
	private Destination processingDestination;
	
	@Autowired
	private FtpFileSenderRetrier fileSenderRetrier;
	
	@ServiceActivator
	public void handleMessage(Message<javax.jms.Message> message) {
		ObjectMessage objectMessage = (ObjectMessage) message.getPayload();
		
		try {
			SapFileEncrypted event = (SapFileEncrypted) objectMessage.getObject();
			log.info("Received event encrypteed : " + event.getFileName());
			
			jmsTemplate.convertAndSend(processingDestination, new EncryptedFileSending(event.getFileName()));
			fileSenderRetrier.guaranteedSendFile(event.getFileName());
			
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		} catch (MessageHandlingException e) {
			log.error("Error send file", e);
		}
	}
}
