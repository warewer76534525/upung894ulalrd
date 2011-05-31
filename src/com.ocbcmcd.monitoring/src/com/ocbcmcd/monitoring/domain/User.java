package com.ocbcmcd.monitoring.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.ocbcmcd.monitoring.command.UserType;

@Entity 
@Table(name = "users")
public class User {
	private static final int ENABLED = 1;
	private static final int DISABLED = 0;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "enabled")
	private int enabled;

	@Column(name = "created_date")
	private Date createdDate;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user") }, inverseJoinColumns = { @JoinColumn(name = "role") })
	private Set<Role> userRoles = new HashSet<Role>(0);

	public User() {
		enable();
		createdDate = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public void enable() {
		if (!isEnable())
			enabled = User.ENABLED;
	}
	
	public boolean isEnable() {
		return enabled == User.ENABLED;
	}

	public void disable() {
		if (isEnable())
			enabled = User.DISABLED;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Set<Role> getUserRoles() {
		return userRoles;
	}
	
	public void addRole(Role role) {
		userRoles.add(role);
	}

	public boolean passwordEquals(String _password) {
		return password.equals(_password);
	}
	
	public boolean userNameEquals(String _userName) {
		return userName.equals(_userName);
	}
	
	public boolean isAdminUser() {
		for (Role role : userRoles) {
			if (UserType.ADMIN_TYPE.equals(role.getRoleName())) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isRegulerUser() {
		for (Role role : userRoles) {
			if (UserType.REGULAR_TYPE.equals(role.getRoleName())) {
				return true;
			}
		}
		
		return false;
	}

	public void updateRole(Role role) {
		userRoles = new HashSet<Role>();
		userRoles.add(role);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", enabled="
				+ enabled + ", createdDate=" + createdDate + ", userRoles="
				+ userRoles + "]";
	}
	
	
	
}
