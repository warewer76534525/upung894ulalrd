package com.ocbcmcd.confirmwatcher.ftpchecker;

import org.apache.commons.net.ftp.FTPFile;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.file.remote.session.Session;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;

import com.ocbcmcd.confirmwatcher.checker.FTPFileChecker;

public class When_get_all_file_in_directory {
	@Test
	public void Should_list_all_files_in_directory() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("ftp-retriever.xml");
		DefaultFtpSessionFactory sessionFactory = context.getBean(DefaultFtpSessionFactory.class);
		Session session = sessionFactory.getSession();
		
		for (FTPFile file : session.<FTPFile>list("/fromnisp/CHK")) {
			System.out.println(file.isFile());
			System.out.println(file.getName());
		}
	}
	
	@Test
	public void Should_check_if_file_exist_in_directory() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("ftp-retriever.xml");
		FTPFileChecker fileChecker = context.getBean(FTPFileChecker.class);
		Assert.assertTrue(fileChecker.fileExist("movie-hunt.txt"));
	}
	
	@Test
	public void Should_check_if_file_not_exist_in_directory() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("ftp-retriever.xml");
		FTPFileChecker fileChecker = context.getBean(FTPFileChecker.class);
		Assert.assertFalse(fileChecker.fileExist("movie-hunt2.txt"));
	}
	
	@Test
	public void Should_delete_file_in_server() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("ftp-retriever.xml");
		FTPFileChecker fileChecker = context.getBean(FTPFileChecker.class);
		Assert.assertTrue(fileChecker.fileExist("movie-hunt.txt.chk"));
		
		fileChecker.deleteFile("movie-hunt.txt.chk");
		
		Assert.assertFalse(fileChecker.fileExist("movie-hunt.txt.chk"));
	}
	
	@Test
	public void Should_delete_file_in_server_txt() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("ftp-retriever.xml");
		FTPFileChecker fileChecker = context.getBean(FTPFileChecker.class);
		Assert.assertTrue(fileChecker.fileExist("movie-hunt.txt"));
		
		fileChecker.deleteFile("movie-hunt.txt");
		
		Assert.assertFalse(fileChecker.fileExist("movie-hunt.txt"));
	}
	
	
	@Test
	public void Should_check_session_different() {
		ApplicationContext context = new ClassPathXmlApplicationContext("ftp-retriever.xml");
		DefaultFtpSessionFactory sessionFactory = context.getBean(DefaultFtpSessionFactory.class);
		Session session = sessionFactory.getSession();
		Session session2 = sessionFactory.getSession();
		
		Assert.assertNotSame(session, session2);
		
		session.close();
		session2.close();
	}
	
	
}
