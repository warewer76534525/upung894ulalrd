package com.ocbcmcd.sapfilewatcher.listener;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Headers;
import org.springframework.integration.annotation.Payload;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.ocbcmcd.message.SapFileEncrypted;
import com.ocbcmcd.sapfilewatcher.encrypt.EncryptedFileTransformer;

@Component
public class ERPFileListener {
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@ServiceActivator
	public void onNewFileArrival(@Headers Map<String, Object> headers,
			@Payload File file) throws Exception {
		
		for (String k : headers.keySet())
			System.out.println(String.format("%s=%s", k, headers.get(k)));
		
		EncryptedFileTransformer transformer = new EncryptedFileTransformer();
		transformer.create(file);
		
		File encryptedFile = new File("C:\\erpfile\\encrypted\\", file.getName());
		
		jmsTemplate.convertAndSend(new SapFileEncrypted(encryptedFile.getAbsolutePath()));
		
	}
}
