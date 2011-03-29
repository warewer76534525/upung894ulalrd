package com.ocbcmcd.monitoring.dao;

import com.ocbcmcd.monitoring.domain.Role;

public interface IRoleDao {
	Role getAdminRole();
	Role getRegularUserRole();
}
