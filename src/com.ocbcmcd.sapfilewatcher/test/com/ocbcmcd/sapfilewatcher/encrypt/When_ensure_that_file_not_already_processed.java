package com.ocbcmcd.sapfilewatcher.encrypt;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ocbcmcd.sapfilewatcher.duplicatechecker.ErpFileDuplicationGuard;
import com.ocbcmcd.sapfilewatcher.duplicatechecker.FileAlreadySentException;
import com.ocbcmcd.sapfilewatcher.duplicatechecker.TodayFileSentStorage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/sap-file-watcher.xml")
public class When_ensure_that_file_not_already_processed {
	@Autowired
	private ErpFileDuplicationGuard erpFileDuplicationGuard;
	
	@Autowired
	private TodayFileSentStorage todayFileSentStorage;
	
	private File storageFile = new File("file-sent-today.txt");
	
	@Test
	@ExpectedException(FileAlreadySentException.class)
	public void should_failed_if_already_processed() {
		erpFileDuplicationGuard.ensureFileNotDuplicate(new File("110303065116_0001_00001.txt"));
		erpFileDuplicationGuard.ensureFileNotDuplicate(new File("110303065116_0001_00001.txt"));
	}
	
	@Test
	public void should_persist_storage_to_file() {
		erpFileDuplicationGuard.ensureFileNotDuplicate(new File("110303065116_0001_00001.txt"));
		Assert.assertTrue(storageFile.exists());
	}
	
	@Test
	public void Should_save_set_to_file() {
		todayFileSentStorage.saveNewFile("110303065116_0001_00002.txt");
		Assert.assertTrue(new File("file-sent-today.txt").exists());
	}
}
