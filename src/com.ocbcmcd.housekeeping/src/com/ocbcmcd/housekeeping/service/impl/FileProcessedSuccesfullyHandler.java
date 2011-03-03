package com.ocbcmcd.housekeeping.service.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Component;

import com.ocbcmcd.housekeeping.service.IHouseKeepingService;
import com.ocbcmcd.message.OcbcFileProcessedSucessfully;

@Component
public class FileProcessedSuccesfullyHandler implements MessageListener {
	protected Log log = LogFactory.getLog(getClass());
	
	@Autowired
	IHouseKeepingService houseKeepingService;

	@Override
	public void onMessage(Message message) {
		ObjectMessage mapMessage = (ObjectMessage) message;
		OcbcFileProcessedSucessfully event;
		try {
			event = (OcbcFileProcessedSucessfully) mapMessage.getObject();
			log.info("incoming event: " + event);
			houseKeepingService.moveSuccessFile(event);
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		}

	}
}
