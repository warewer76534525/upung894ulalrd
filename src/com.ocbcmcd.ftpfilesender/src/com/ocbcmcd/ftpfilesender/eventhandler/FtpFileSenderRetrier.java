package com.ocbcmcd.ftpfilesender.eventhandler;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javax.jms.Destination;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.Message;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.ocbcmcd.message.OcbcFileSendingFailed;
import com.ocbcmcd.message.OcbcFileSent;

@Component
public class FtpFileSenderRetrier {
	protected Log log = LogFactory.getLog(getClass());
	
	@Value("${encrypted.ext}")
	private String encryptedExt;
	
	@Value("${encrypted.dir}")
	private String encryptedDirectory;
	
	@Autowired
	@Qualifier("outChannel")
	private DirectChannel ftpChannel;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Value("${watcher.maxretry}")
	private int maxRetry;
	
	@Value("${watcher.interval}")
	private long intervalBetweenRetry;
	
	@Autowired
	@Qualifier("sendingFailedDestination")
	private Destination sendingFailedDestination;
	
	public void guaranteedSendFile(String fileName) {
		Timer timer = new Timer();

		TimerTask timerTask = new RetryJobTask(maxRetry, fileName);

		timer.schedule(timerTask, 0, intervalBetweenRetry);
	}
	
	private class RetryJobTask extends TimerTask {
		private int maxRetry;
		private String fileName;
		private int currentRetryCount;
		
		public RetryJobTask(int maxRetry, String fileName) {
			this.maxRetry = maxRetry;
			this.fileName = fileName;
		}
		
		@Override
		public void run() {
			currentRetryCount++;
			
			try {
					
				Message<File> fileMessage =  MessageBuilder.withPayload(new File(encryptedDirectory, fileName + encryptedExt)).build();
				ftpChannel.send(fileMessage);
				
				log.info("Ftp file successfully : sent");
				
				this.cancel();
				
				jmsTemplate.convertAndSend(new OcbcFileSent(fileName));
			} catch (Exception e) {
				log.error(e);
				
				if (currentRetryCount == maxRetry) {
					log.warn("Retry send file to ftp is reached maximum : " + fileName);
					log.warn(ExceptionUtils.getRootCauseMessage(e));
					jmsTemplate.convertAndSend(sendingFailedDestination, new OcbcFileSendingFailed(fileName, ExceptionUtils.getRootCauseMessage(e), ExceptionUtils.getFullStackTrace(e)));
					this.cancel();
				}
			}
		}
	}
}
