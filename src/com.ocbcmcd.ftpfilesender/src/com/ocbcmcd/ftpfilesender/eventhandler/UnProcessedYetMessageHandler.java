package com.ocbcmcd.ftpfilesender.eventhandler;

import java.io.File;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.Message;
import org.springframework.integration.MessageHandlingException;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.file.remote.session.Session;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.jms.support.JmsUtils;

import com.ocbcmcd.message.OcbcFileUnProcessYet;

public class UnProcessedYetMessageHandler {
	protected Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Value("${encrypted.ext}")
	private String encryptedExt;
	
	@Value("${encrypted.dir}")
	private String encryptedDirectory;
	
	@Value("${ftp.remote.dir}")
	private String ftpRemoteDir;
	
	@Autowired
	@Qualifier("outChannel")
	private DirectChannel ftpChannel;
	
	@ServiceActivator
	public void handleMessage(Message<javax.jms.Message> message) {
		ObjectMessage objectMessage = (ObjectMessage) message.getPayload();
		
		try {
			OcbcFileUnProcessYet event = (OcbcFileUnProcessYet) objectMessage.getObject();
			log.info("Received event unprocessed yet : " + event.getFileName());
			
			Message<File> fileMessage =  MessageBuilder.withPayload(new File(encryptedDirectory, event.getFileName() + encryptedExt)).build();
			
			log.info("Remove already exists ftp file : " + event.getFileName());
			
			Session ftpSession = sessionFactory.getSession();
			ftpSession.remove(ftpRemoteDir + File.separator + event.getFileName() + encryptedExt);
			ftpSession.close();
			
			log.info("Send file again : " + event.getFileName());
			
			ftpChannel.send(fileMessage);
			log.info("Ftp file successfully : sent");
			
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		} catch (MessageHandlingException e) {
			log.error("Error send file", e);
		} catch (Exception e) {
			log.error("Error delete file", e);
		}
	}
}
