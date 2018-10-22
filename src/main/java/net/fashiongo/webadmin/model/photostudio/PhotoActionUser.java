/**
 * 
 */
package net.fashiongo.webadmin.model.photostudio;

import javax.persistence.Column;

/**
 * @author XiaoChuan.Gao
 *  PhotoActionUser: Photo Action User
 */

public class PhotoActionUser {
	
	@Column(name = "UserID")
	private Integer userID;
	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}


	@Column(name = "UserName")
	private String userName;
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
