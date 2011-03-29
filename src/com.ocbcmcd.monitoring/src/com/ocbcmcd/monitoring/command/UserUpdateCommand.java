package com.ocbcmcd.monitoring.command;

import com.ocbcmcd.monitoring.common.MD5;
import com.ocbcmcd.monitoring.domain.User;


public class UserUpdateCommand {
	private int id;
	private String userName;
	private String currentPassword;
	private String password;
	private String confirmPassword;
	
	public UserUpdateCommand() {
		
	}
	
	public UserUpdateCommand(User user) {
		id = user.getId();
		userName = user.getUserName();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public boolean isEqualsToConfirmPassword() {
		return password.equals(confirmPassword);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.currentPassword = oldPassword;
	}

	public String getHashedCurrentPassword() {
		try {
			return MD5.hash(currentPassword);
		} catch (Exception e) {
		}
		return confirmPassword;
	}

	public String getHashedPassword() {
		try { 
			return MD5.hash(password);
		} catch (Exception e) {
		}
		return password;
	}
	
	
	
	
}
