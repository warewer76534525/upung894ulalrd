package com.ocbcmcd.ftpfilesender.eventhandler;

import java.io.File;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.Message;
import org.springframework.integration.MessageHandlingException;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsUtils;

import com.ocbcmcd.message.OcbcFileSent;
import com.ocbcmcd.message.SapFileEncrypted;

public class EncryptedFileMessageHandler {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	@Qualifier("outChannel")
	private DirectChannel ftpChannel;
	
	@ServiceActivator
	public void handleMessage(Message<javax.jms.Message> message) {
		ObjectMessage objectMessage = (ObjectMessage) message.getPayload();
		
		try {
			SapFileEncrypted event = (SapFileEncrypted) objectMessage.getObject();
			System.out.println(event.getFileName());
			
			Message<File> fileMessage =  MessageBuilder.withPayload(new File(event.getFileName())).build();
			ftpChannel.send(fileMessage);
			System.out.println("sent");
			
			jmsTemplate.convertAndSend(new OcbcFileSent(event.getFileName()));
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		} catch (MessageHandlingException e) {
			System.out.println("error send message");
		}
	}
}
