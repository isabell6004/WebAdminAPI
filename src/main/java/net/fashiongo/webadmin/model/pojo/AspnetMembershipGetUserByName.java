package net.fashiongo.webadmin.model.pojo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
public class AspnetMembershipGetUserByName {
	@JsonProperty("Email")
	private String Email;
	@JsonProperty("PasswordQuestion")
	private String PasswordQuestion;
	@JsonProperty("Comment")
	private String Comment;
	@JsonProperty("IsApproved")
	private Boolean IsApproved;
	@JsonProperty("CreateDate")
	private LocalDateTime CreateDate;
	@JsonProperty("LastLoginDate")
	private LocalDateTime LastLoginDate;
	@JsonProperty("LastActivityDate")
	private LocalDateTime LastActivityDate;
	@JsonProperty("LastPasswordChangedDate")
	private LocalDateTime LastPasswordChangedDate;
	@JsonProperty("UserId")
	private String UserId;
	@JsonProperty("IsLockedOut")
	private Boolean IsLockedOut;
	@JsonProperty("LastLockoutDate")
	private LocalDateTime LastLockoutDate;
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPasswordQuestion() {
		return PasswordQuestion;
	}
	public void setPasswordQuestion(String passwordQuestion) {
		PasswordQuestion = passwordQuestion;
	}
	public String getComment() {
		return Comment;
	}
	public void setComment(String comment) {
		Comment = comment;
	}
	
	public Boolean getIsApproved() {
		return IsApproved;
	}
	public void setIsApproved(Boolean isApproved) {
		IsApproved = isApproved;
	}
	public LocalDateTime getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		CreateDate = createDate;
	}
	public LocalDateTime getLastLoginDate() {
		return LastLoginDate;
	}
	public void setLastLoginDate(LocalDateTime lastLoginDate) {
		LastLoginDate = lastLoginDate;
	}
	public LocalDateTime getLastActivityDate() {
		return LastActivityDate;
	}
	public void setLastActivityDate(LocalDateTime lastActivityDate) {
		LastActivityDate = lastActivityDate;
	}
	public LocalDateTime getLastPasswordChangedDate() {
		return LastPasswordChangedDate;
	}
	public void setLastPasswordChangedDate(LocalDateTime lastPasswordChangedDate) {
		LastPasswordChangedDate = lastPasswordChangedDate;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public Boolean getIsLockedOut() {
		return IsLockedOut;
	}
	public void setIsLockedOut(Boolean isLockedOut) {
		IsLockedOut = isLockedOut;
	}
	public LocalDateTime getLastLockoutDate() {
		return LastLockoutDate;
	}
	public void setLastLockoutDate(LocalDateTime lastLockoutDate) {
		LastLockoutDate = lastLockoutDate;
	}
	
}
