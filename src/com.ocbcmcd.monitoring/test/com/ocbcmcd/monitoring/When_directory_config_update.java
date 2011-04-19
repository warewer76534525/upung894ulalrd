package com.ocbcmcd.monitoring;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ocbcmcd.monitoring.command.DirConfigCommand;
import com.ocbcmcd.monitoring.service.impl.ConfigurerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/configtest-context.xml")
public class When_directory_config_update {
	
	@Autowired
	private ConfigurerService dirConfigurerService;
	private DirConfigCommand _dirConfig;
	
	@Before
	public void setUp() {
		_dirConfig =  new DirConfigCommand();
		
		_dirConfig.setIncomingDir("bin/file-dir/incomingx");
		_dirConfig.setEncryptedDir("bin/file-dir/encryptedx");
		_dirConfig.setProcessingDir("bin/file-dir/processingx");
		_dirConfig.setOutgoingDir("bin/file-dir/outgoingx");
		_dirConfig.setDailyReportDir("bin/file-dir/dailyreportx");
		_dirConfig.setFailedDir("bin/file-dir/failedx");
		_dirConfig.setEncryptedFilePath("bin/file-dir/encryptedx");
		_dirConfig.setIncomingFilePath("bin/file-dir/incomingx");
		_dirConfig.setEomScheduler("0 55 23 L * ?x");
		_dirConfig.setEomSchedulerStatus("1x");
		_dirConfig.setEodScheduler("0 10 22 * * ?x");
	}
	
	@Test
	public void should_able_to_display_list_smtp_config() {
		DirConfigCommand dirConfig = dirConfigurerService.getDirConfig();
		
		System.out.println(dirConfig);
		
		
		Assert.assertNotNull(dirConfig.getIncomingDir());
		Assert.assertNotNull(dirConfig.getEncryptedDir());
		Assert.assertNotNull(dirConfig.getProcessingDir());
		Assert.assertNotNull(dirConfig.getOutgoingDir());
		Assert.assertNotNull(dirConfig.getDailyReportDir());
		Assert.assertNotNull(dirConfig.getFailedDir());
		Assert.assertNotNull(dirConfig.getEncryptedFilePath());
		Assert.assertNotNull(dirConfig.getIncomingFilePath());
		Assert.assertNotNull(dirConfig.getEomScheduler());
		Assert.assertNotNull(dirConfig.getEomSchedulerStatus());
		Assert.assertNotNull(dirConfig.getEodScheduler());
	}
	
	@Test
	public void should_change_mail_config_value() {
		dirConfigurerService.saveConfig(_dirConfig);
		DirConfigCommand dirConfig = dirConfigurerService.getDirConfig();
		
		Assert.assertEquals(dirConfig.getIncomingDir(), _dirConfig.getIncomingDir());
		Assert.assertEquals(dirConfig.getEncryptedDir(), _dirConfig.getEncryptedDir());
		Assert.assertEquals(dirConfig.getProcessingDir(), _dirConfig.getProcessingDir());
		Assert.assertEquals(dirConfig.getOutgoingDir(), _dirConfig.getOutgoingDir());
		Assert.assertEquals(dirConfig.getDailyReportDir(), _dirConfig.getDailyReportDir());
		Assert.assertEquals(dirConfig.getFailedDir(), _dirConfig.getFailedDir());
		Assert.assertEquals(dirConfig.getEncryptedFilePath(), _dirConfig.getEncryptedFilePath());
		Assert.assertEquals(dirConfig.getIncomingFilePath(), _dirConfig.getIncomingFilePath());
		Assert.assertEquals(dirConfig.getEomScheduler(), _dirConfig.getEomScheduler());
		Assert.assertEquals(dirConfig.getEomSchedulerStatus(), _dirConfig.getEomSchedulerStatus());
		Assert.assertEquals(dirConfig.getEodScheduler(), _dirConfig.getEodScheduler());
	}
}
