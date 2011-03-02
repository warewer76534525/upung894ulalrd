package com.ocbcmcd.confirmwatcher.eventhandler;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.MessageHandlingException;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Component;

import com.ocbcmcd.confirmwatcher.checker.ChkConfirmationChecker;
import com.ocbcmcd.confirmwatcher.checker.ConfirmationStatus;
import com.ocbcmcd.message.OcbcFileProcessedSucessfully;
import com.ocbcmcd.message.OcbcFileSent;

@Component
public class FtpFileSentMessageHandler {
	@Autowired
	private ChkConfirmationChecker confirmationChecker;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@ServiceActivator
	public void handleMessage(Message<javax.jms.Message> message) {
		ObjectMessage objectMessage = (ObjectMessage) message.getPayload();
		
		try {
			OcbcFileSent event = (OcbcFileSent) objectMessage.getObject();
			System.out.println(event.getFileName());
			
			System.out.println("check file exist");
//			System.out.println("start timer");
			
			ConfirmationStatus status = confirmationChecker.getStatus(event.getFileName());
			
			if (status == ConfirmationStatus.Success) {
				jmsTemplate.convertAndSend(new OcbcFileProcessedSucessfully(event.getFileName()));
			}
			
			System.out.println(status);
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		} catch (MessageHandlingException e) {
			System.out.println("error send message");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
