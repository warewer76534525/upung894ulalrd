package com.ocbcmcd.sapfilewatcher.listener;

import java.io.File;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.annotation.Headers;
import org.springframework.integration.annotation.Payload;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.ocbcmcd.message.SapFileEncrypted;
import com.ocbcmcd.sapfilewatcher.encrypt.EncryptedFileTransformer;

@Component
public class ERPFileListener {
	protected Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private EncryptedFileTransformer transformer;
	
	@Value("${encrypted.ext}")
	private String encryptedExt;
	
	@ServiceActivator
	public void onNewFileArrival(@Headers Map<String, Object> headers,
			@Payload File file) throws Exception {
		
		log.info("Received : " + file.getAbsolutePath());
		
		File encryptedFile = transformer.create(file);
		
		log.info("Created Encrypted File : " + encryptedFile.getAbsolutePath());
		
		jmsTemplate.convertAndSend(new SapFileEncrypted(encryptedFile.getName().replaceAll(encryptedExt,"")));
	}
}
