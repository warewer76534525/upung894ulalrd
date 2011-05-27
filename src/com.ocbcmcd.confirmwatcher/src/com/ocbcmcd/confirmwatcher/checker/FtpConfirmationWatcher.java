package com.ocbcmcd.confirmwatcher.checker;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.jms.Destination;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.ocbcmcd.message.OcbcFileProcessFailed;
import com.ocbcmcd.message.OcbcFileProcessedSucessfully;
import com.ocbcmcd.message.OcbcFileUnProcessYet;

@Component
public class FtpConfirmationWatcher {
	protected Log log = LogFactory.getLog(getClass());

	@Autowired
	private ChkConfirmationChecker confirmationChecker;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	@Qualifier("notProcessYetDestination")
	private Destination notProcessedYetDestination;

	@Autowired
	@Qualifier("processFailedDestination")
	private Destination processFailedDestination;

	@Value("${watcher.maxretry}")
	private int maxRetry;

	@Value("${watcher.interval}")
	private long intervalBetweenRetry;

	public void watchForFile(final String fileName) throws Exception {

		Timer timer = new Timer();

		TimerTask timerTask = new RetryJobTask(maxRetry, fileName);

		timer.schedule(timerTask, intervalBetweenRetry, intervalBetweenRetry);
	}

	private class RetryJobTask extends TimerTask {
		private int maxRetry;
		private int currentRetryCount;
		private String fileName;

		public RetryJobTask(int maxRetry, String fileName) {
			this.maxRetry = maxRetry;
			this.fileName = fileName;
		}

		@Override
		public void run() {

			log.info("Run file watcher");

			currentRetryCount++;

			log.info("retry : " + currentRetryCount);

			try {
				ConfirmationStatus status = confirmationChecker
						.getStatus(fileName);

				if (status == ConfirmationStatus.Success) {
					log.info("Confirmation file received for : " + fileName);

					jmsTemplate
							.convertAndSend(new OcbcFileProcessedSucessfully(
									fileName));

					this.cancel();

				} else if (currentRetryCount == maxRetry) {
					log.info("Retry reached max publish ocbcfileprocecssfailed for : "
							+ fileName);

					try {
						confirmationChecker.removeOriginalFile(fileName);
					} catch (IOException e) {
						log.error(e.getMessage(), e);
					}

					jmsTemplate.convertAndSend(processFailedDestination,
							new OcbcFileProcessFailed(fileName));

					this.cancel();
				} else {
					log.info("Confirmation file not exist yet for : "
							+ fileName);

					jmsTemplate.convertAndSend(notProcessedYetDestination,
							new OcbcFileUnProcessYet(fileName));
				}

				log.info("Status : " + status);
			} catch (Exception e) {
				log.error("Error check file", e);
			}
			log.info("Exit file watcher");
		}
	}
}
