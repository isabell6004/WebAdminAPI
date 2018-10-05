package net.fashiongo.webadmin.model.pojo;

/**
 * @author Nayeon Kim
 */
public class SecurityLogs {
	private Integer logID;
	private String userName;
	private String ip;
	private String loginOn;
	
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
	public String getLoginOn() {
		return loginOn;
	}
	public void setLoginOn(String loginOn) {
		this.loginOn = loginOn;
	}
}
