package com.ocbcmcd.monitoring.command;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ocbcmcd.monitoring.service.impl.IConfigCommand;

public class DbConfigCommand implements IConfigCommand {

	private String url;
	private String userName;
	private String password;

	public DbConfigCommand(Map<String, String> map) {
		init(map);
	}

	private void init(Map<String, String> map) {
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String value = map.get(key);

			if (key.equals(ConfigType.JDBC_URL)) {
				url = value;
			} else if (key.equals(ConfigType.JDBC_USERNAME)) {
				userName = value;
			} else if (key.equals(ConfigType.JDBC_PASSWORD)) {
				password = value;
			}
		}
	}

	public DbConfigCommand() {

	}

	public Map<String, String> getEncryptedConfigs() {
		Map<String, String> map = new HashMap<String, String>();
		
		if (!StringUtils.isBlank(password)) {
			map.put(ConfigType.JDBC_PASSWORD, password);
		}
		
		return map;
	}
	
	public Map<String, String> getPlainConfigs() {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put(ConfigType.JDBC_URL, url);
		map.put(ConfigType.JDBC_USERNAME, userName);
		
		return map;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	@Override
	public String toString() {
		return "DbConfigCommand [url=" + url + ", username=" + userName + "]";
	}

}
