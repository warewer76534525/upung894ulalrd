package com.ocbcmcd.monitoring;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ocbcmcd.monitoring.command.DbConfigCommand;
import com.ocbcmcd.monitoring.service.impl.ConfigurerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/configtest-context.xml")
public class When_database_config_update {
	
	@Autowired
	private ConfigurerService dbConfigurerService;
	private DbConfigCommand _dbConfig;
	
	@Before
	public void setUp() {
		_dbConfig =  new DbConfigCommand();
		
		_dbConfig.setUrl("jdbc.url=jdbc:mysql://localhost/ocbcmcdx");
		_dbConfig.setUserName("ocbcx");
		_dbConfig.setPassword("ocbcx");
	}
	
	@Test
	public void should_able_to_display_list_database_config() {
		DbConfigCommand dbConfig = dbConfigurerService.getDbConfig();
		
		System.out.println(dbConfig);
		
		Assert.assertNotNull(dbConfig.getUrl());
		Assert.assertNotNull(dbConfig.getUserName());
		Assert.assertNotNull(dbConfig.getPassword());
		
		
	}
	
	@Test
	public void should_change_mail_config_value() {
		dbConfigurerService.saveConfig(_dbConfig);
		DbConfigCommand dbConfig = dbConfigurerService.getDbConfig();
		
		Assert.assertEquals(dbConfig.getUrl(), _dbConfig.getUrl());
		Assert.assertEquals(dbConfig.getUserName(), _dbConfig.getUserName());
		Assert.assertEquals(dbConfig.getPassword(), _dbConfig.getPassword());
		
	}
}
