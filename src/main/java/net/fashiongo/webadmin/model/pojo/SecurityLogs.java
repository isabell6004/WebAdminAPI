package net.fashiongo.webadmin.model.pojo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Nayeon Kim
 */
public class SecurityLogs {
	@JsonProperty("LogID")
	private Integer logID;
	@JsonProperty("UserName")
	private String userName;
	@JsonProperty("IP")
	private String ip;
	@JsonProperty("LoginOn")
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
