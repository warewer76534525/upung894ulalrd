package com.ocbcmcd.monitoring;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ocbcmcd.monitoring.command.FtpConfigCommand;
import com.ocbcmcd.monitoring.service.impl.ConfigurerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/configtest-context.xml")
public class When_ftp_config_update {
	
	@Autowired
	private ConfigurerService ftpConfigurerService;
	FtpConfigCommand _ftpConfig;
	
	@Before
	public void setUp() {
		_ftpConfig =  new FtpConfigCommand();
		_ftpConfig.setHost("localhostx");
		_ftpConfig.setPort("22");
		_ftpConfig.setUserName("wellyx");
		_ftpConfig.setPassword("1234");
		_ftpConfig.setRemoteDir("tonisp/McDx");
		_ftpConfig.setChkDir("/fromnisp/CHKx");
		_ftpConfig.setReportDir("fromnisp/REPORTx");
		_ftpConfig.setMaxRetry("5x");
		_ftpConfig.setRetryInterval("5000x");
		_ftpConfig.setWatcherRetry("5x");
		_ftpConfig.setWatcherInterval("5000x");
	}
	
	@Test
	public void should_able_to_display_list_smtp_config() {
		FtpConfigCommand ftpConfig = ftpConfigurerService.getFtpConfig();
		
		System.out.println(ftpConfig);
		Assert.assertNotNull(ftpConfig.getHost());
		Assert.assertNotNull(ftpConfig.getPort());
		Assert.assertNotNull(ftpConfig.getUserName());
		Assert.assertNotNull(ftpConfig.getPassword());
		Assert.assertNotNull(ftpConfig.getRemoteDir());
		Assert.assertNotNull(ftpConfig.getChkDir());
		Assert.assertNotNull(ftpConfig.getRemoteDir());
		Assert.assertNotNull(ftpConfig.getMaxRetry());
		Assert.assertNotNull(ftpConfig.getRetryInterval());
		Assert.assertNotNull(ftpConfig.getWatcherRetry());
		Assert.assertNotNull(ftpConfig.getWatcherInterval());
	}
	
	@Test
	public void should_change_ftp_config_value() {
		ftpConfigurerService.saveConfig(_ftpConfig);
		FtpConfigCommand ftpConfig = ftpConfigurerService.getFtpConfig();
		
		System.out.println(ftpConfig.getHost());
		Assert.assertEquals(ftpConfig.getHost(), _ftpConfig.getHost());
	}
}
