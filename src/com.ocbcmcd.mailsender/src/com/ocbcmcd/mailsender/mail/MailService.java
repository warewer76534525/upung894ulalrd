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
	
	public void sendErrorMessage(String fileName) {
		log.info("sendErrorMessage" + fileName);
		
        SimpleMailMessage mailMessage = new SimpleMailMessage(alertMailMessage);
        mailMessage.setText(getMessageContent(fileName));
        mailSender.send(mailMessage);
        
        log.info("email sent " + fileName);
	}

	private String getMessageContent(String fileName) {
		Map model = new HashMap();
        model.put("fileName", fileName);
		return VelocityEngineUtils.mergeTemplateIntoString(
		           velocityEngine, "error-notification.vm", model);
	}	
}
