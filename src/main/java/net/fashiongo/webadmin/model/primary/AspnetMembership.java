package net.fashiongo.webadmin.model.primary;

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
	@Column(name = "ApplicationId")
	private String applicationId; 
	
	@JsonProperty("UserId")
	@Column(name = "UserId")
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
	private Date createDate;
	
	@JsonProperty("LastLoginDate")
	@Column(name = "LastLoginDate")
	private Date lastLoginDate; 
	
	@JsonProperty("LastPasswordChangedDate")
	@Column(name = "LastPasswordChangedDate")
	private Date lastPasswordChangedDate;
	
	@JsonProperty("LastLockoutDate")
	@Column(name = "LastLockoutDate")
	private Date lastLockoutDate;
	
	@JsonProperty("FailedPasswordAttemptCount")
	@Column(name = "FailedPasswordAttemptCount")
	private Integer failedPasswordAttemptCount;
	
	@JsonProperty("FailedPasswordAttemptWindowStart")
	@Column(name = "FailedPasswordAttemptWindowStart")
	private Date failedPasswordAttemptWindowStart; 
	
	@JsonProperty("FailedPasswordAnswerAttemptCount")
	@Column(name = "FailedPasswordAnswerAttemptCount")
	private Integer failedPasswordAnswerAttemptCount;
	
	@JsonProperty("FailedPasswordAnswerAttemptWindowStart")
	@Column(name = "FailedPasswordAnswerAttemptWindowStart")
	private Integer failedPasswordAnswerAttemptWindowStart;
	
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Date getLastPasswordChangedDate() {
		return lastPasswordChangedDate;
	}

	public void setLastPasswordChangedDate(Date lastPasswordChangedDate) {
		this.lastPasswordChangedDate = lastPasswordChangedDate;
	}

	public Date getLastLockoutDate() {
		return lastLockoutDate;
	}

	public void setLastLockoutDate(Date lastLockoutDate) {
		this.lastLockoutDate = lastLockoutDate;
	}

	public Integer getFailedPasswordAttemptCount() {
		return failedPasswordAttemptCount;
	}

	public void setFailedPasswordAttemptCount(Integer failedPasswordAttemptCount) {
		this.failedPasswordAttemptCount = failedPasswordAttemptCount;
	}

	public Date getFailedPasswordAttemptWindowStart() {
		return failedPasswordAttemptWindowStart;
	}

	public void setFailedPasswordAttemptWindowStart(Date failedPasswordAttemptWindowStart) {
		this.failedPasswordAttemptWindowStart = failedPasswordAttemptWindowStart;
	}

	public Integer getFailedPasswordAnswerAttemptCount() {
		return failedPasswordAnswerAttemptCount;
	}

	public void setFailedPasswordAnswerAttemptCount(Integer failedPasswordAnswerAttemptCount) {
		this.failedPasswordAnswerAttemptCount = failedPasswordAnswerAttemptCount;
	}

	public Integer getFailedPasswordAnswerAttemptWindowStart() {
		return failedPasswordAnswerAttemptWindowStart;
	}

	public void setFailedPasswordAnswerAttemptWindowStart(Integer failedPasswordAnswerAttemptWindowStart) {
		this.failedPasswordAnswerAttemptWindowStart = failedPasswordAnswerAttemptWindowStart;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
