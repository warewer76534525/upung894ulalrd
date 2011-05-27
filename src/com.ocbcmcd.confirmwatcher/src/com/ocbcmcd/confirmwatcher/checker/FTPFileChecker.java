package com.ocbcmcd.confirmwatcher.checker;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.file.remote.session.Session;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.stereotype.Component;

@Component
public class FTPFileChecker {
	
	protected Log log = LogFactory.getLog(FTPFileChecker.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session;
	
	@Value("${checked.dir}")
	private String checkDirectory;
	
	@Value("${ftp.remote.dir}")
	private String uploadDirectory;
	
	@Value("${encrypted.ext}")
	private String encryptedExt;
	
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

	public void deleteConfirmationFile(String fileName) throws Exception {
		deleteFile(checkDirectory, fileName);
	}
	
	private void deleteFile(String dir, String fileName) throws Exception {
		Session session = getSession();
		
		try { 
			session.remove(dir + "/" + fileName);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			session.remove(dir + "\\" + fileName);
		}
	}

	public void close() {
		session.close();
	}

	public void deleteOriginalFile(String fileName) throws Exception {
		deleteFile(uploadDirectory, fileName + encryptedExt);
	}
}
