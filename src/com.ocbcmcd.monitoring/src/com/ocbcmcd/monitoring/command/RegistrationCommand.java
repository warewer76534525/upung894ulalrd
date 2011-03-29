package com.ocbcmcd.monitoring.command;

public class RegistrationCommand {
	private String userName;
	private String password;
	private String confirmPassword;
	private String userType;
	
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
}
