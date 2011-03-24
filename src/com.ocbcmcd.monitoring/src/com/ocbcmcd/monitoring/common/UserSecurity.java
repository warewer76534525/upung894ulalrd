package com.ocbcmcd.monitoring.common;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserSecurity {
	
	public static String getAuthenticatedUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	public static boolean isGranted(String permission) {
		boolean granted = false;
		
		Collection<GrantedAuthority> authorites = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for (GrantedAuthority grantedAuthority : authorites) {
			if (grantedAuthority.getAuthority().equals(permission))
				granted = true;
		}
		
		return granted;
	}
	
	public static boolean isGrantedToAdminUserEdit() {
		return UserSecurity.isGranted(UserPermission.ROLE_PRS_ADM_UPDATE_USER);
	}
}
