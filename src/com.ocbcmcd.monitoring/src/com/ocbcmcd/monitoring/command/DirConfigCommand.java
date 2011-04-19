package com.ocbcmcd.monitoring.command;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.ocbcmcd.monitoring.service.impl.IConfigCommand;

public class DirConfigCommand implements IConfigCommand {

	private String incomingDir;
	private String encryptedDir;
	private String processingDir;
	private String outgoingDir;
	private String dailyReportDir;
	private String failedDir;
	private String eomScheduler;
	private String eomSchedulerStatus;
	private String eodScheduler;

	public DirConfigCommand(Map<String, String> map) {
		init(map);
	}

	public DirConfigCommand() {
		
	}

	private void init(Map<String, String> map) {
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String value = map.get(key);

			if (key.equals(ConfigType.INCOMING_DIR)) {
				incomingDir = value;
			} else if (key.equals(ConfigType.ENCRYPTED_DIR)) {
				encryptedDir = value;
			} else if (key.equals(ConfigType.PROCESSING_DIR)) {
				processingDir = value;
			} else if (key.equals(ConfigType.OUTGOING_DIR)) {
				outgoingDir = value;
			} else if (key.equals(ConfigType.DAILY_REPORT_DIR)) {
				dailyReportDir = value;
			} else if (key.equals(ConfigType.FAILED_DIR)) {
				failedDir = value;
			} else if (key.equals(ConfigType.END_OF_MONTH_CRON)) {
				eomScheduler = value;
			} else if (key.equals(ConfigType.END_OF_MONTH_START)) {
				eomSchedulerStatus = value;
			} else if (key.equals(ConfigType.END_OF_DAY_CRON)) {
				eodScheduler = value;
			} 
		}
	}

	public String getIncomingDir() {
		return incomingDir;
	}

	public void setIncomingDir(String incomingDir) {
		this.incomingDir = incomingDir;
	}

	public String getEncryptedDir() {
		return encryptedDir;
	}

	public void setEncryptedDir(String encryptedDir) {
		this.encryptedDir = encryptedDir;
	}

	public String getProcessingDir() {
		return processingDir;
	}

	public void setProcessingDir(String processingDir) {
		this.processingDir = processingDir;
	}

	public String getOutgoingDir() {
		return outgoingDir;
	}

	public void setOutgoingDir(String outgoingDir) {
		this.outgoingDir = outgoingDir;
	}

	public String getDailyReportDir() {
		return dailyReportDir;
	}

	public void setDailyReportDir(String dailyReportDir) {
		this.dailyReportDir = dailyReportDir;
	}

	public String getFailedDir() {
		return failedDir;
	}

	public void setFailedDir(String failedDir) {
		this.failedDir = failedDir;
	}

	public String getEomScheduler() {
		return eomScheduler;
	}

	public void setEomScheduler(String eomScheduler) {
		this.eomScheduler = eomScheduler;
	}

	public String getEomSchedulerStatus() {
		return eomSchedulerStatus;
	}

	public void setEomSchedulerStatus(String eomSchedulerStatus) {
		this.eomSchedulerStatus = eomSchedulerStatus;
	}
	
	public String getEodScheduler() {
		return eodScheduler;
	}

	public void setEodScheduler(String eodScheduler) {
		this.eodScheduler = eodScheduler;
	}
	
	

	@Override
	public String toString() {
		return "DirConfigCommand [incomingDir=" + incomingDir
				+ ", encryptedDir=" + encryptedDir + ", processingDir="
				+ processingDir + ", outgoingDir=" + outgoingDir
				+ ", dailyReportDir=" + dailyReportDir + ", failedDir="
				+ failedDir + ", eomScheduler=" + eomScheduler
				+ ", eomSchedulerStatus=" + eomSchedulerStatus
				+ ", eodScheduler=" + eodScheduler + "]";
	}

	public Map<String, String> getEncryptedConfigs() {
		Map<String, String> map = new HashMap<String, String>();
		
		
		return map;
	}
	
	public Map<String, String> getPlainConfigs() {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put(ConfigType.INCOMING_DIR, incomingDir);
		map.put(ConfigType.ENCRYPTED_DIR, encryptedDir);
		map.put(ConfigType.PROCESSING_DIR, processingDir);
		map.put(ConfigType.OUTGOING_DIR, outgoingDir);
		map.put(ConfigType.DAILY_REPORT_DIR, dailyReportDir);
		map.put(ConfigType.FAILED_DIR, failedDir);
		map.put(ConfigType.END_OF_MONTH_CRON, eomScheduler);
		map.put(ConfigType.END_OF_MONTH_START, eomSchedulerStatus);
		map.put(ConfigType.END_OF_DAY_CRON, eodScheduler);
		
		return map;
	}

	
	
	

}
