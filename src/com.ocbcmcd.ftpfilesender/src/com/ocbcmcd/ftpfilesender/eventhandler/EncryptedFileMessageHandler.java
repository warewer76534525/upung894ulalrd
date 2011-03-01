package com.ocbcmcd.ftpfilesender.eventhandler;

import java.io.File;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.springframework.integration.Message;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.jms.support.JmsUtils;

import com.ocbcmcd.message.SapFileEncrypted;

public class EncryptedFileMessageHandler {
	
	@ServiceActivator
	public Message<File> handleMessage(Message<javax.jms.Message> message) {
//		ObjectMessage objectMessage = (ObjectMessage) message.getPayload();
//		
//		try {
//			SapFileEncrypted event = (SapFileEncrypted) objectMessage.getObject();
//			System.out.println(event.getFileName());
//			return null;
//		} catch (JMSException e) {
//			throw JmsUtils.convertJmsAccessException(e);
//		}
		return null;
	}
}
