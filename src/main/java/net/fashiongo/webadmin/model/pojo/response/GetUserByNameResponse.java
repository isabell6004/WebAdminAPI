package net.fashiongo.webadmin.model.pojo.response;

import java.util.Date;

public class GetUserByNameResponse {
	private String Email;
	private String PasswordQuestion;
	private String Comment;
	private Boolean IsApproved;
	private Date CreateDate;
	private Date LastLoginDate;
	private Date LastActivityDate;
	private Date LastPasswordChangedDate;
	private String UserId;
	private Boolean IsLockedOut;
	private Date LastLockoutDate;
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
	public Date getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}
	public Date getLastLoginDate() {
		return LastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		LastLoginDate = lastLoginDate;
	}
	public Date getLastActivityDate() {
		return LastActivityDate;
	}
	public void setLastActivityDate(Date lastActivityDate) {
		LastActivityDate = lastActivityDate;
	}
	public Date getLastPasswordChangedDate() {
		return LastPasswordChangedDate;
	}
	public void setLastPasswordChangedDate(Date lastPasswordChangedDate) {
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
	public Date getLastLockoutDate() {
		return LastLockoutDate;
	}
	public void setLastLockoutDate(Date lastLockoutDate) {
		LastLockoutDate = lastLockoutDate;
	}
	
	
}
