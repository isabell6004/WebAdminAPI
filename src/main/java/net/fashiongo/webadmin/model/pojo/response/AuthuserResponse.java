package net.fashiongo.webadmin.model.pojo.response;

import net.fashiongo.webadmin.model.pojo.admin.MenuDS;

/**
 * 
 * @author Incheol Jung
 */
public class AuthuserResponse {
	private String fullName;
	private MenuDS menuDS;
	private String message;
	private String role;
	private Integer sCodeNo;
	private Boolean sCodeYn;
	private String tokenID;
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public MenuDS getMenuDS() {
		return menuDS;
	}
	public void setMenuDS(MenuDS menuDS) {
		this.menuDS = menuDS;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getsCodeNo() {
		return sCodeNo;
	}
	public void setsCodeNo(Integer sCodeNo) {
		this.sCodeNo = sCodeNo;
	}
	public Boolean getsCodeYn() {
		return sCodeYn;
	}
	public void setsCodeYn(Boolean sCodeYn) {
		this.sCodeYn = sCodeYn;
	}
	public String getTokenID() {
		return tokenID;
	}
	public void setTokenID(String tokenID) {
		this.tokenID = tokenID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	private Integer userID;
	private String userName;
}