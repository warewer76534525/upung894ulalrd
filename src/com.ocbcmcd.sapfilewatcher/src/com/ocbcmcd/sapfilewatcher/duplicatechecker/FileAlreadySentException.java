package com.ocbcmcd.sapfilewatcher.duplicatechecker;

public class FileAlreadySentException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6924186040326321681L;
	
	public FileAlreadySentException(String name) {	
		super("Can not send the File duplication : " + name);
	}

}
