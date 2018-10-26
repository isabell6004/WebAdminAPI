package net.fashiongo.webadmin.model.primary;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "[aspnet_membership]")
public class AspnetMembership {
	@Id
	@JsonProperty("ApplicationId")
	@Column(name = "ApplicationId", columnDefinition="uniqueidentifier")
	private String applicationId; 
	
	@JsonProperty("UserId")
	@Column(name = "UserId", columnDefinition="uniqueidentifier")
	private String userId;
	
	@JsonProperty("Password")
	@Column(name = "Password")
	private String password; 
	
	@JsonProperty("PasswordFormat")
	@Column(name = "PasswordFormat")
	private Integer passwordFormat;
	
	@JsonProperty("PasswordSalt")
	@Column(name = "PasswordSalt")
	private String passwordSalt;
	
	@JsonProperty("MobilePIN")
	@Column(name = "MobilePIN")
	private String mobilePIN;
	
	@JsonProperty("Email")
	@Column(name = "Email")
	private String email;
	
	@JsonProperty("LoweredEmail")
	@Column(name = "LoweredEmail")
	private String loweredEmail;
	
	@JsonProperty("PasswordQuestion")
	@Column(name = "PasswordQuestion")
	private String passwordQuestion;
	
	@JsonProperty("PasswordAnswer")
	@Column(name = "PasswordAnswer")
	private String passwordAnswer;
	
	@JsonProperty("IsApproved")
	@Column(name = "IsApproved")
	private Boolean isApproved;
	
	@JsonProperty("IsLockedOut")
	@Column(name = "IsLockedOut")
	private Boolean isLockedOut;
	
	@JsonProperty("CreateDate")
	@Column(name = "CreateDate")
	private LocalDateTime createDate;
	
	@JsonProperty("LastLoginDate")
	@Column(name = "LastLoginDate")
	private LocalDateTime lastLoginDate; 
	
	@JsonProperty("LastPasswordChangedDate")
	@Column(name = "LastPasswordChangedDate")
	private LocalDateTime lastPasswordChangedDate;
	
	@JsonProperty("LastLockoutDate")
	@Column(name = "LastLockoutDate")
	private LocalDateTime lastLockoutDate;
	
	@JsonProperty("FailedPasswordAttemptCount")
	@Column(name = "FailedPasswordAttemptCount")
	private Integer failedPasswordAttemptCount;
	
	@JsonProperty("FailedPasswordAttemptWindowStart")
	@Column(name = "FailedPasswordAttemptWindowStart")
	private LocalDateTime failedPasswordAttemptWindowStart; 
	
	@JsonProperty("FailedPasswordAnswerAttemptCount")
	@Column(name = "FailedPasswordAnswerAttemptCount")
	private Integer failedPasswordAnswerAttemptCount;
	
	@JsonProperty("FailedPasswordAnswerAttemptWindowStart")
	@Column(name = "FailedPasswordAnswerAttemptWindowStart")
	private LocalDateTime failedPasswordAnswerAttemptWindowStart;
	
	@JsonProperty("Comment")
	@Column(name = "Comment")
	private String comment;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPasswordFormat() {
		return passwordFormat;
	}

	public void setPasswordFormat(Integer passwordFormat) {
		this.passwordFormat = passwordFormat;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getMobilePIN() {
		return mobilePIN;
	}

	public void setMobilePIN(String mobilePIN) {
		this.mobilePIN = mobilePIN;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoweredEmail() {
		return loweredEmail;
	}

	public void setLoweredEmail(String loweredEmail) {
		this.loweredEmail = loweredEmail;
	}

	public String getPasswordQuestion() {
		return passwordQuestion;
	}

	public void setPasswordQuestion(String passwordQuestion) {
		this.passwordQuestion = passwordQuestion;
	}

	public String getPasswordAnswer() {
		return passwordAnswer;
	}

	public void setPasswordAnswer(String passwordAnswer) {
		this.passwordAnswer = passwordAnswer;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	public Boolean getIsLockedOut() {
		return isLockedOut;
	}

	public void setIsLockedOut(Boolean isLockedOut) {
		this.isLockedOut = isLockedOut;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(LocalDateTime lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public LocalDateTime getLastPasswordChangedDate() {
		return lastPasswordChangedDate;
	}

	public void setLastPasswordChangedDate(LocalDateTime lastPasswordChangedDate) {
		this.lastPasswordChangedDate = lastPasswordChangedDate;
	}

	public LocalDateTime getLastLockoutDate() {
		return lastLockoutDate;
	}

	public void setLastLockoutDate(LocalDateTime lastLockoutDate) {
		this.lastLockoutDate = lastLockoutDate;
	}

	public Integer getFailedPasswordAttemptCount() {
		return failedPasswordAttemptCount;
	}

	public void setFailedPasswordAttemptCount(Integer failedPasswordAttemptCount) {
		this.failedPasswordAttemptCount = failedPasswordAttemptCount;
	}

	public LocalDateTime getFailedPasswordAttemptWindowStart() {
		return failedPasswordAttemptWindowStart;
	}

	public void setFailedPasswordAttemptWindowStart(LocalDateTime failedPasswordAttemptWindowStart) {
		this.failedPasswordAttemptWindowStart = failedPasswordAttemptWindowStart;
	}

	public Integer getFailedPasswordAnswerAttemptCount() {
		return failedPasswordAnswerAttemptCount;
	}

	public void setFailedPasswordAnswerAttemptCount(Integer failedPasswordAnswerAttemptCount) {
		this.failedPasswordAnswerAttemptCount = failedPasswordAnswerAttemptCount;
	}

	public LocalDateTime getFailedPasswordAnswerAttemptWindowStart() {
		return failedPasswordAnswerAttemptWindowStart;
	}

	public void setFailedPasswordAnswerAttemptWindowStart(LocalDateTime failedPasswordAnswerAttemptWindowStart) {
		this.failedPasswordAnswerAttemptWindowStart = failedPasswordAnswerAttemptWindowStart;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	
	
}
