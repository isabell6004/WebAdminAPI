package net.fashiongo.webadmin.model.pojo;

import java.time.LocalDateTime;

/**
 * @author JungHwan
 */
public class SecurityAccessCodes {
	private Integer codeID;
	private String accessCode;
	private LocalDateTime expiredOn;
	public Integer getCodeID() {
		return codeID;
	}
	public void setCodeID(Integer codeID) {
		this.codeID = codeID;
	}
	public String getAccessCode() {
		return accessCode;
	}
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	public LocalDateTime getExpiredOn() {
		return expiredOn;
	}
	public void setExpiredOn(LocalDateTime expiredOn) {
		this.expiredOn = expiredOn;
	}
	
}
