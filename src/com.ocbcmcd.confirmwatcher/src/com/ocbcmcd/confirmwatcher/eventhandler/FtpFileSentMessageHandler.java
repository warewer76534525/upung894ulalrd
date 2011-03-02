package com.ocbcmcd.confirmwatcher.eventhandler;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.springframework.integration.Message;
import org.springframework.integration.MessageHandlingException;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Component;

import com.ocbcmcd.message.OcbcFileSent;

@Component
public class FtpFileSentMessageHandler {

	@ServiceActivator
	public void handleMessage(Message<javax.jms.Message> message) {
		ObjectMessage objectMessage = (ObjectMessage) message.getPayload();
		
		try {
			OcbcFileSent event = (OcbcFileSent) objectMessage.getObject();
			System.out.println(event.getFileName());
			
			System.out.println("start timer");
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		} catch (MessageHandlingException e) {
			System.out.println("error send message");
		}
	}
}
