package com.ocbcmcd.mailsender.listener;

import javax.jms.ObjectMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.annotation.ServiceActivator;

import com.ocbcmcd.mailsender.mail.MailService;
import com.ocbcmcd.message.OcbcFileProcessFailed;

public class ErrorProcessMessageHandler {
	protected Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private MailService mailService;
	
	@ServiceActivator
	public void handleMessage(Message<javax.jms.Message> message) {
		ObjectMessage objectMessage = (ObjectMessage) message.getPayload();
		
		try {
			OcbcFileProcessFailed event = (OcbcFileProcessFailed) objectMessage.getObject();
			log.info("Received event file error processed : " + event.getFileName());
			
			mailService.sendErrorMessage(event.getFileName());
			
			log.info("Sending email : " + event.getFileName());
		} catch (Exception e) {
			log.error("Error sending mail", e);
		}
	}
}
