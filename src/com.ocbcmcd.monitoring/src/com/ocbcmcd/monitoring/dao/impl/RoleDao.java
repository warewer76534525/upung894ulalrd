package com.ocbcmcd.monitoring.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.ocbcmcd.monitoring.command.UserType;
import com.ocbcmcd.monitoring.dao.IRoleDao;
import com.ocbcmcd.monitoring.domain.Role;

@Repository
@Scope("prototype")
public class RoleDao implements IRoleDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public Role getAdminRole() {
		List<Role> roles = hibernateTemplate.find("FROM Role r WHERE r.roleName=?", UserType.ADMIN_TYPE);
		return roles.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Role getRegularUserRole() {
		List<Role> roles = hibernateTemplate.find("FROM Role r WHERE r.roleName=?", UserType.REGULAR_TYPE);
		return roles.get(0);
	}

}
