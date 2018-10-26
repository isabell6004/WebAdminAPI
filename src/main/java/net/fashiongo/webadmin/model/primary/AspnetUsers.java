package net.fashiongo.webadmin.model.primary;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "[aspnet_Users]")
public class AspnetUsers {
	@Id
	@JsonProperty("ApplicationId")
	@Column(name = "ApplicationId", columnDefinition="uniqueidentifier")
	private String applicationId;
	
	@JsonProperty("UserId")
	@Column(name = "UserId", columnDefinition="uniqueidentifier")
	private String userId;
	
	@JsonProperty("UserName")
	@Column(name = "UserName")
	private String userName;
	
	@JsonProperty("LoweredUserName")
	@Column(name = "LoweredUserName")
	private String loweredUserName;
	
	@JsonProperty("MobileAlias")
	@Column(name = "MobileAlias")
	private String mobileAlias;
	
	@JsonProperty("IsAnonymous")
	@Column(name = "IsAnonymous")
	private Boolean isAnonymous;
	
	@JsonProperty("LastActivityDate")
	@Column(name = "LastActivityDate")
	private LocalDateTime lastActivityDate;

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoweredUserName() {
		return loweredUserName;
	}

	public void setLoweredUserName(String loweredUserName) {
		this.loweredUserName = loweredUserName;
	}

	public String getMobileAlias() {
		return mobileAlias;
	}

	public void setMobileAlias(String mobileAlias) {
		this.mobileAlias = mobileAlias;
	}

	public Boolean getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(Boolean isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public LocalDateTime getLastActivityDate() {
		return lastActivityDate;
	}

	public void setLastActivityDate(LocalDateTime lastActivityDate) {
		this.lastActivityDate = lastActivityDate;
	}
	
	
}
