package com.biz.lesson.model.user;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.biz.lesson.model.Persistent;

/**
 * 登陆日志
 */
@Entity
@Table(name="usr_loginlog")
public class UserLoginLog extends Persistent {
	
	public static final Integer LOGIN=0;
	public static final Integer LOGOUT=1;
	

	private static final long serialVersionUID = 7278220785401986077L;

	@ManyToOne(fetch=FetchType.LAZY)
	private User user;

	@Column
	private Timestamp loginTime;

	@Column
	private Timestamp logoutTime;

	@Column(length=50)
	private String loginip;

	@Column(length=40)
	private String companyId;

	@Column
	private Integer actionType=LOGIN;

	public Timestamp getLogoutTime() {
		return logoutTime;
	}

	public Timestamp getLoginTime() {
		return loginTime;
	}

	public User getUser() {
		return user;
	}

	public String getLoginip() {
		return loginip;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	public void setLogoutTime(Timestamp logoutTime) {
		this.logoutTime = logoutTime;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Integer getActionType() {
		return actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}

}
