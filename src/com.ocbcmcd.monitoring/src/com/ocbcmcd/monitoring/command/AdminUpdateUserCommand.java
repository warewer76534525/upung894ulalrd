package com.ocbcmcd.monitoring.command;

import com.ocbcmcd.monitoring.common.MD5;
import com.ocbcmcd.monitoring.domain.User;
 

public class AdminUpdateUserCommand {
	private int id;
	private String userName;
	private String password;
	private String confirmPassword;
	private String userType;
	
	
	public AdminUpdateUserCommand() {
		
	}
	
	public AdminUpdateUserCommand(User user) {
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

	public String getHashedPassword() {
		try {
			return MD5.hash(password);
		} catch (Exception e) {
		}
		return password;
	}
	

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public boolean isAdminType() {
		return UserType.ADMIN_TYPE.equals(userType);
	}
	
	public boolean isRegularUserType() {
		return UserType.REGULAR_TYPE.equals(userType);
	}

	@Override
	public String toString() {
		return "AdminUpdateUserCommand [id=" + id + ", userName=" + userName
				+ ", userType=" + userType + "]";
	}
	
}
