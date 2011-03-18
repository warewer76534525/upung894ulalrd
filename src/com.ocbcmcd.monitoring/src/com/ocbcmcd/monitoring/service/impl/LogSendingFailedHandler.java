package com.ocbcmcd.monitoring.service.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Component;

import com.ocbcmcd.message.OcbcFileSendingFailed;
import com.ocbcmcd.monitoring.service.IMonitoringService;

@Component
public class LogSendingFailedHandler implements MessageListener {
	protected Log log = LogFactory.getLog(getClass());
	
	@Autowired
	IMonitoringService monitoringService;

	@Override
	public void onMessage(Message message) {
		ObjectMessage mapMessage = (ObjectMessage) message;
		OcbcFileSendingFailed event;
		try {
			event = (OcbcFileSendingFailed) mapMessage.getObject();
			log.info("incoming event: " + event);
			monitoringService.logSendingFailed(event);
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		}

	}
}
