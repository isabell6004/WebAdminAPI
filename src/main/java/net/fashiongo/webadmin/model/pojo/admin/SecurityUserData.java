package net.fashiongo.webadmin.model.pojo.admin;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
public class SecurityUserData  implements Serializable {
	@JsonProperty("accesstimes")
	private List<String> accesstimes;
	@JsonProperty("delaccesstimes")
	private List<String> delaccesstimes;
	@JsonProperty("delgroupnames")
	private List<String> delgroupnames;
	@JsonProperty("groupnames")
	private List<String> groupnames;
	@JsonProperty("user")
	private SecurityUserCreate user;

	public List<String> getAccesstimes() {
		return accesstimes;
	}
	public void setAccesstimes(List<String> accesstimes) {
		this.accesstimes = accesstimes;
	}
	public List<String> getDelaccesstimes() {
		return delaccesstimes;
	}
	public void setDelaccesstimes(List<String> delaccesstimes) {
		this.delaccesstimes = delaccesstimes;
	}
	public List<String> getDelgroupnames() {
		return delgroupnames;
	}
	public void setDelgroupnames(List<String> delgroupnames) {
		this.delgroupnames = delgroupnames;
	}
	public List<String> getGroupnames() {
		return groupnames;
	}
	public void setGroupnames(List<String> groupnames) {
		this.groupnames = groupnames;
	}
	public SecurityUserCreate getUser() {
		return user;
	}
	public void setUser(SecurityUserCreate user) {
		this.user = user;
	}
	
}
