package net.fashiongo.webadmin.model.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "[aspnet_UsersInRoles]")
public class AspnetUserInRoles {
	@Id
	@JsonProperty("UserID")
	@Column(name = "UserID")
	private String userId;
	
	@JsonProperty("RoleId")
	@Column(name = "RoleId")
	private String roleId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	
}
