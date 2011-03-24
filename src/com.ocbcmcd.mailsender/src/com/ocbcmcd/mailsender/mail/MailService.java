package com.ocbcmcd.mailsender.mail;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Service
public class MailService {
	protected Log log = LogFactory.getLog(getClass());
	
    @Autowired
    private MailSender mailSender;
    
    @Autowired
    private VelocityEngine velocityEngine;
    
    @Autowired
    private SimpleMailMessage alertMailMessage;
	
	private String getMessageContent(String fileName, String errorMessage, String stackTrace) {
		Map model = new HashMap();
        model.put("fileName", fileName);
        model.put("errorMessage", errorMessage);
        model.put("stackTrace", stackTrace);
		return VelocityEngineUtils.mergeTemplateIntoString(
		           velocityEngine, "error-notification.vm", model);
	}

	public void sendErrorMessage(String fileName, String errorMessage, String stackTrace) {
		log.info("sendErrorMessage" + fileName);
		
        SimpleMailMessage mailMessage = new SimpleMailMessage(alertMailMessage);
        String messageContent = getMessageContent(fileName, errorMessage, stackTrace);
        System.out.println(messageContent);
        mailMessage.setText(messageContent);
        mailSender.send(mailMessage);
        
        log.info("email sent " + fileName);	
	}
}
