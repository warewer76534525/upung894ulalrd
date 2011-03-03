package com.ocbcmcd.confirmwatcher.checker;

import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.file.remote.session.Session;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.stereotype.Component;

@Component
public class FTPFileChecker {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session;
	
	public boolean fileExist(String fileName) throws Exception {
		
		Session session = getSession();
		
		for (FTPFile file : session.<FTPFile> list("/")) {
			if (file.isFile() && file.getName().equals(fileName)) return true;
		}
		
		return false;
	}

	private Session getSession() {
		if (session == null || !session.isOpen()) {
			session = sessionFactory.getSession();
		}
		
		return session;
	}

	public void deleteFile(String fileName) throws Exception {
		Session session = getSession();
		
		session.remove(fileName);
	}

	public void close() {
		session.close();
	}
}
