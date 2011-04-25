package com.ocbcmcd.confirmwatcher.checker;

import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.file.remote.session.Session;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.stereotype.Component;

@Component
public class FTPFileChecker {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session;
	
	@Value("${checked.dir}")
	private String checkDirectory;
	
	public boolean fileExist(String fileName) throws Exception {
		
		Session session = getSession();
		
		for (FTPFile file : session.<FTPFile>list(checkDirectory)) {
			if (file.isFile() && file.getName().toLowerCase().equals(fileName.toLowerCase())) return true;
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
		
		try { 
			session.remove(checkDirectory + "/" + fileName);
		} catch (Exception e) {
			session.remove(checkDirectory + "\\" + fileName);
		}
	}

	public void close() {
		session.close();
	}
}
