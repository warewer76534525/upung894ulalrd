package com.ocbcmcd.monitoring.command;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ocbcmcd.monitoring.service.impl.IConfigCommand;

public class FtpConfigCommand implements IConfigCommand {
	private String host;
	private String port;
	private String userName;
	private String password;
	private String remoteDir;
	private String chkDir;
	private String reportDir;
	private String maxRetry;
	private String retryInterval;
	private String watcherRetry;
	private String watcherInterval;
	
	public FtpConfigCommand(Map<String, String> map) {
		init(map);
	}
	
	
	public FtpConfigCommand() {
		
	}


	private void init(Map<String, String> map) {
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String value = map.get(key);
			
			if (key.equals(ConfigType.FTP_HOST)) {
				host = value;
			} else if (key.equals(ConfigType.FTP_PORT)) {
				port = value;
			} else if (key.equals(ConfigType.FTP_USERNAME)) {
				userName = value;
			} else if (key.equals(ConfigType.FTP_PASSWORD)) {
				password = value;
			} else if (key.equals(ConfigType.FTP_REMOTE_DIR)) {
				remoteDir = value;
			} else if (key.equals(ConfigType.CHECKED_DIR)) {
				chkDir = value;
			} else if (key.equals(ConfigType.FTP_REPORT_DIR)) {
				reportDir = value;
			} else if (key.equals(ConfigType.FTP_MAXRETRY)) {
				maxRetry = value;
			} else if (key.equals(ConfigType.FTP_INTERVAL)) {
				retryInterval = value;
			} else if (key.equals(ConfigType.WATCHER_MAXRETRY)) {
				watcherRetry = value;
			} else if (key.equals(ConfigType.WATCHER_INTERVAL)) {
				watcherInterval = value;
			}  
		}
	}

	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
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
	public String getRemoteDir() {
		return remoteDir;
	}
	public void setRemoteDir(String remoteDir) {
		this.remoteDir = remoteDir;
	}
	public String getChkDir() {
		return chkDir;
	}
	public void setChkDir(String chkDir) {
		this.chkDir = chkDir;
	}
	public String getReportDir() {
		return reportDir;
	}
	public void setReportDir(String reportDir) {
		this.reportDir = reportDir;
	}
	public String getMaxRetry() {
		return maxRetry;
	}
	public void setMaxRetry(String maxRetry) {
		this.maxRetry = maxRetry;
	}
	public String getRetryInterval() {
		return retryInterval;
	}
	public void setRetryInterval(String interval) {
		this.retryInterval = interval;
	}
	public String getWatcherRetry() {
		return watcherRetry;
	}
	public void setWatcherRetry(String watcherRetry) {
		this.watcherRetry = watcherRetry;
	}
	public String getWatcherInterval() {
		return watcherInterval;
	}
	public void setWatcherInterval(String watcherInterval) {
		this.watcherInterval = watcherInterval;
	}

	public Map<String, String> getEncryptedConfigs() {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put(ConfigType.FTP_HOST, host);
		map.put(ConfigType.FTP_PORT, port);
		map.put(ConfigType.FTP_USERNAME, userName);
		
		if (!StringUtils.isEmpty(password)) {
			map.put(ConfigType.FTP_PASSWORD, password);
		}
		
		return map;
	}
	
	public Map<String, String> getPlainConfigs() {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put(ConfigType.FTP_REMOTE_DIR, remoteDir);
		map.put(ConfigType.CHECKED_DIR, chkDir);
		map.put(ConfigType.FTP_REPORT_DIR, reportDir);
		map.put(ConfigType.FTP_MAXRETRY, maxRetry);
		map.put(ConfigType.FTP_INTERVAL, retryInterval);
		map.put(ConfigType.WATCHER_MAXRETRY, watcherRetry);
		map.put(ConfigType.WATCHER_INTERVAL, watcherInterval);
		
		return map;
	}

	@Override
	public String toString() {
		return "FtpConfigCommand [host=" + host + ", port=" + port
				+ ", userName=" + userName + ", password=" + password
				+ ", remoteDir=" + remoteDir + ", chkDir=" + chkDir
				+ ", reportDir=" + reportDir + ", maxRetry=" + maxRetry
				+ ", retryInterval=" + retryInterval + ", watcherRetry="
				+ watcherRetry + ", watcherInterval=" + watcherInterval + "]";
	}
	
	

}
