package com.ocbcmcd.monitoring.command;

import java.util.ArrayList;
import java.util.List;

public class UserType {
	private String name;
	private String description;
	
	public final static String ADMIN_TYPE = "ROLE_ADMIN";
	public final static String REGULAR_TYPE = "ROLE_REGULAR";

	public UserType(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	public UserType() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static List<UserType> getUserTypes() {
		List<UserType> userTypes = new ArrayList<UserType>();
		UserType admin = new UserType("ADMIN_TYPE", "ADMIN USER");
		UserType regular = new UserType("REGULAR_TYPE", "REGULAE USER");
		
		userTypes.add(regular);
		userTypes.add(admin);
		
		return userTypes;
	}

}

