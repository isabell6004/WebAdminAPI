package net.fashiongo.webadmin.model.primary;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Incheol Jung
 */
@Entity
@Table(name = "[Security.Login_Log]")
public class SecurityLoginLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LogID")
	private Integer logID;
	
	@Column(name = "UserID")
	private Integer userID;
	
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

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
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
