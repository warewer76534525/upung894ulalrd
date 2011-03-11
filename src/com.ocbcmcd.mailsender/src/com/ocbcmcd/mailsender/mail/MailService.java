package com.ocbcmcd.mailsender.mail;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	protected Log log = LogFactory.getLog(getClass());
	
    @Autowired
    private MailSender mailSender;
    
    @Autowired
    private SimpleMailMessage alertMailMessage;
	
	public void sendErrorMessage(String fileName) {
		log.info("sendErrorMessage" + fileName);
		
        SimpleMailMessage mailMessage = new SimpleMailMessage(alertMailMessage);
        mailMessage.setText(fileName);
        mailSender.send(mailMessage);
        
        log.info("email sent " + fileName);
	}	
}
