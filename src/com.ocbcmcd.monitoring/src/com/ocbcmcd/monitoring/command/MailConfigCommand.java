package com.ocbcmcd.monitoring.command;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ocbcmcd.monitoring.service.impl.IConfigCommand;

public class MailConfigCommand implements IConfigCommand {
	
	private String host;
	private String port;
	private String userName;
	private String password;
	private String smtpProtocol;
	private String smtpAuth;
	private String from;
	private String toEod;
	private String eodSubject;
	private String toProcessedFailed;
	private String processedFailedSubject;
	private String toSentFailed;
	private String sentFailedSubject;
	
	public MailConfigCommand() {
		
	}
	
	public MailConfigCommand(Map<String, String> map) {
		init(map);
	}

	private void init(Map<String, String> map) {
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String value = map.get(key);
			
			if (key.equals(ConfigType.MAIL_HOST)) {
				host = value;
			} else if (key.equals(ConfigType.MAIL_PORT)) {
				port = value;
			} else if (key.equals(ConfigType.MAIL_USERNAME)) {
				userName = value;
			} else if (key.equals(ConfigType.MAIL_PASSWORD)) {
				password = value;
			} else if (key.equals(ConfigType.MAIL_TRANSPORT_PROTOCOL)) {
				smtpProtocol = value;
			} else if (key.equals(ConfigType.MAIL_SMTP_AUTH)) {
				smtpAuth = value;
			} else if (key.equals(ConfigType.MAIL_FROM)) {
				from = value;
			} else if (key.equals(ConfigType.MAIL_TO_EOD)) {
				toEod = value;
			} else if (key.equals(ConfigType.MAIL_EOD_SUBJECT)) {
				eodSubject = value;
			} else if (key.equals(ConfigType.MAIL_TO_PROCESSED_FAILED)) {
				toProcessedFailed = value;
			} else if (key.equals(ConfigType.MAIL_PROCESSED_FAILED_SUBJECT)) {
				processedFailedSubject = value;
			}  else if (key.equals(ConfigType.MAIL_TO_SENT_FAILED)) {
				toSentFailed = value;
			} else if (key.equals(ConfigType.MAIL_SENT_FAILED_SUBJECT)) {
				sentFailedSubject = value;
			}  
		}
		
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSmtpProtocol() {
		return smtpProtocol;
	}

	public void setSmtpProtocol(String smtpProtocol) {
		this.smtpProtocol = smtpProtocol;
	}

	public String getSmtpAuth() {
		return smtpAuth;
	}

	public void setSmtpAuth(String smtpAuth) {
		this.smtpAuth = smtpAuth;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getToEod() {
		return toEod;
	}

	public void setToEod(String toEod) {
		this.toEod = toEod;
	}

	public String getEodSubject() {
		return eodSubject;
	}

	public void setEodSubject(String eodSubject) {
		this.eodSubject = eodSubject;
	}

	public String getToProcessedFailed() {
		return toProcessedFailed;
	}

	public void setToProcessedFailed(String toProcessedFailed) {
		this.toProcessedFailed = toProcessedFailed;
	}

	public String getProcessedFailedSubject() {
		return processedFailedSubject;
	}

	public void setProcessedFailedSubject(String processedFailedSubject) {
		this.processedFailedSubject = processedFailedSubject;
	}

	public String getToSentFailed() {
		return toSentFailed;
	}

	public void setToSentFailed(String toSentFailed) {
		this.toSentFailed = toSentFailed;
	}

	public String getSentFailedSubject() {
		return sentFailedSubject;
	}

	public void setSentFailedSubject(String sentFailedSubject) {
		this.sentFailedSubject = sentFailedSubject;
	}

	public Map<String, String> getEncryptedConfigs() {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put(ConfigType.MAIL_HOST, host);
		map.put(ConfigType.MAIL_PORT, port);
		map.put(ConfigType.MAIL_USERNAME, userName);
		map.put(ConfigType.MAIL_TRANSPORT_PROTOCOL, smtpProtocol);
		
		if (!StringUtils.isBlank(password)) {
			map.put(ConfigType.MAIL_PASSWORD, password);
		}
		
		return map;
	}
	
	public Map<String, String> getPlainConfigs() {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put(ConfigType.MAIL_SMTP_AUTH, smtpAuth);
		map.put(ConfigType.MAIL_FROM, from);
		map.put(ConfigType.MAIL_TO_EOD, toEod);
		map.put(ConfigType.MAIL_EOD_SUBJECT, eodSubject);
		map.put(ConfigType.MAIL_TO_PROCESSED_FAILED, toProcessedFailed);
		map.put(ConfigType.MAIL_PROCESSED_FAILED_SUBJECT, processedFailedSubject);
		map.put(ConfigType.MAIL_TO_SENT_FAILED, toSentFailed);
		map.put(ConfigType.MAIL_SENT_FAILED_SUBJECT, sentFailedSubject);
		
		return map;
	}

	@Override
	public String toString() {
		return "MailConfigCommand [host=" + host + ", port=" + port
				+ ", userName=" + userName + ", password=" + password
				+ ", smtpProtocol=" + smtpProtocol + ", smtpAuth=" + smtpAuth
				+ ", from=" + from + ", toEod=" + toEod + ", eodSubject="
				+ eodSubject + ", toProcessedFailed=" + toProcessedFailed
				+ ", processedFailedSubject=" + processedFailedSubject
				+ ", toSentFailed=" + toSentFailed + ", sentFailedSubject="
				+ sentFailedSubject + "]";
	}
	
}
