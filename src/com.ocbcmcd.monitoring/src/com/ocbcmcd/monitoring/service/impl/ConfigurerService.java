package com.ocbcmcd.monitoring.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ocbcmcd.monitoring.MailConfigCommand;
import com.ocbcmcd.monitoring.command.DbConfigCommand;
import com.ocbcmcd.monitoring.command.DirConfigCommand;
import com.ocbcmcd.monitoring.command.FtpConfigCommand;

public class ConfigurerService {
	private List<String> configList = new ArrayList<String>();
	private ServiceConfigurationHolder serviceConfigurationHolder; 
	
	public ConfigurerService(List<String> ftpConfigList, ServiceConfigurationHolder serviceConfigurationHolder) {
		this.configList = ftpConfigList;
		this.serviceConfigurationHolder = serviceConfigurationHolder;  
	}
	
	public ConfigurerService() {
		
	}
	
	public FtpConfigCommand getFtpConfig() {
		Map<String, String> map = serviceConfigurationHolder.getProperties(configList);
		return new FtpConfigCommand(map);
	}
	
	public void saveConfig(IConfigCommand command) {
		serviceConfigurationHolder.setProperties(command.getPlainConfigs());
		serviceConfigurationHolder.setEncryptedProperties(command.getEncryptedConfigs());
	}

	public MailConfigCommand getMailConfig() {
		Map<String, String> map = serviceConfigurationHolder.getProperties(configList);
		return new MailConfigCommand(map);
	}

	public DbConfigCommand getDbConfig() {
		Map<String, String> map = serviceConfigurationHolder.getProperties(configList);
		return new DbConfigCommand(map);
	}

	public DirConfigCommand getDirConfig() {
		Map<String, String> map = serviceConfigurationHolder.getProperties(configList);
		return new DirConfigCommand(map);
	}
	
	
}
