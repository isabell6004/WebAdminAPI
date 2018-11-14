package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;
import java.sql.Clob;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
@Entity
@Table(name = "aspnet_Membership")
public class AspnetMembership implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@JsonProperty("UserId")
	@Column(name = "UserId")
	private String userId;
	
	@JsonProperty("ApplicationID")
	@Column(name = "ApplicationID")
	private String applicationID;
	
	@Column(name = "Password")
	private String password;
	
	@Column(name = "Passwordformat")
	private Integer passwordformat;
	
	@Column(name = "PasswordSalt")
	private String passwordSalt;
	
	@Column(name = "MobilePIN")
	private String mobilePIN;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "LoweredEmail")
	private String loweredEmail;
	
	@Column(name = "PasswordQuestion")
	private String passwordQuestion;
	
	@Column(name = "PasswordAnswer")
	private String passwordAnswer;
	
	@Column(name = "IsApproved")
	private Boolean isApproved;
	
	@Column(name = "IsLockedOut")
	private Boolean isLockedOut;
	
	@Column(name = "CreateDate")
	private LocalDateTime createDate;
	
	@Column(name = "LastLoginDate")
	private LocalDateTime lastLoginDate;
	
	@Column(name = "LastPasswordChangedDate")
	private LocalDateTime lastPasswordChangedDate;
	
	@Column(name = "LastLockoutDate")
	private LocalDateTime lastLockoutDate;
	
	@Column(name = "FailedPasswordAttemptCount")
	private Integer failedPasswordAttemptCount;
	
	@Column(name = "FailedPasswordAttemptWindowStart")
	private LocalDateTime failedPasswordAttemptWindowStart;
	
	@Column(name = "FailedPasswordAnswerAttemptCount")
	private Integer failedPasswordAnswerAttemptCount;
	
	@Column(name = "FailedPasswordAnswerAttemptWindowStart")
	private LocalDateTime failedPasswordAnswerAttemptWindowStart;
	
	@Column(name = "Comment")
	private Clob comment;

	public String getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPasswordformat() {
		return passwordformat;
	}

	public void setPasswordformat(Integer passwordformat) {
		this.passwordformat = passwordformat;
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

	public Clob getComment() {
		return comment;
	}

	public void setComment(Clob comment) {
		this.comment = comment;
	}
	
	
}
