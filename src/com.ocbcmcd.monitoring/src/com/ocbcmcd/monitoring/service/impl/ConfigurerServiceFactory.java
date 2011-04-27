package com.ocbcmcd.monitoring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ocbcmcd.config.service.EncryptedConfigurationService;

public class ConfigurerServiceFactory {
	List<String> configList = new ArrayList<String>();
	ServiceConfigurationHolder serviceConfigurationList; 
	
	@Autowired()
	@Qualifier("monitoringConfigurationService")
	private EncryptedConfigurationService monitoringConfService;
	
	@Autowired()
	@Qualifier("confirmWatcherConfigurationService")
	private EncryptedConfigurationService confirmWatcherConfService;
	
	@Autowired()
	@Qualifier("ftpFileConfigurationService")
	private EncryptedConfigurationService ftpFileConfService;
	
	@Autowired()
	@Qualifier("mailSenderConfigurationService")
	private EncryptedConfigurationService mailSenderConfigurationService;
	
	@Autowired()
	private EncryptedConfigurationService sapConfigurationService;
	
	@Autowired()
	private EncryptedConfigurationService houseConfigurationService;
	
	public ConfigurerServiceFactory(List<String> configList) {
		this.configList = configList;
	}
	
	
	public ConfigurerService createConfigurerService() throws Exception {
		List<EncryptedConfigurationService> configurationServiceProviders = new ArrayList<EncryptedConfigurationService>();
		
		configurationServiceProviders.add(confirmWatcherConfService);
		configurationServiceProviders.add(ftpFileConfService);
		configurationServiceProviders.add(monitoringConfService);
		configurationServiceProviders.add(mailSenderConfigurationService);
		configurationServiceProviders.add(sapConfigurationService);
		configurationServiceProviders.add(houseConfigurationService);
		
		serviceConfigurationList = new ServiceConfigurationHolder(configurationServiceProviders);
		ConfigurerService service = new ConfigurerService(configList, serviceConfigurationList);
		return service;
	}

	
}
