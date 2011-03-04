package com.ocbcmcd.confirmwatcher.ftpchecker;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

public class When_check_chk_file {
	@Test
	public void Should_rename_file_to_chk() {
		File originalFile = new File("D:/ocbcsite/110221130912_1111_03702.txt.gpg");
		String chkFileName = originalFile.getName().replaceAll(".txt.gpg", ".chk");
		Assert.assertEquals("110221130912_1111_03702.chk", chkFileName);
	}
}
