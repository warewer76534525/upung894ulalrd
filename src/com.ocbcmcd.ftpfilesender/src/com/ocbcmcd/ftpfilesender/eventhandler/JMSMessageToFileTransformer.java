package com.ocbcmcd.ftpfilesender.eventhandler;

import java.io.File;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.springframework.integration.Message;
import org.springframework.integration.annotation.Transformer;
import org.springframework.jms.support.JmsUtils;

import com.ocbcmcd.message.SapFileEncrypted;

public class JMSMessageToFileTransformer {
	
	@Transformer
	public File transformJMSMessageToFile(Message<javax.jms.Message> message) {
		ObjectMessage objectMessage = (ObjectMessage) message.getPayload();
		System.out.println("tranform");
		try {
			SapFileEncrypted event = (SapFileEncrypted) objectMessage
					.getObject();
			System.out.println(event.getFileName());
			return new File(event.getFileName());
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		}
	}
}
