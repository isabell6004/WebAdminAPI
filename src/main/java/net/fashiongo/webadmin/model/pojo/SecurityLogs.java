package net.fashiongo.webadmin.model.pojo;

import java.time.LocalDateTime;

import javax.persistence.Column;

/**
 * @author Nayeon Kim
 */
public class SecurityLogs {
	@Column(name = "LogID")
	private Integer logID;
	@Column(name = "UserName")
	private String userName;
	@Column(name = "IP")
	private String ip;
	@Column(name = "LoginOn")
	private LocalDateTime loginOn;
	
	public Integer getLogID() {
		return logID;
	}
	public void setLogID(Integer logID) {
		this.logID = logID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public LocalDateTime getLoginOn() {
		return loginOn;
	}
	public void setLoginOn(LocalDateTime loginOn) {
		this.loginOn = loginOn;
	}
}
