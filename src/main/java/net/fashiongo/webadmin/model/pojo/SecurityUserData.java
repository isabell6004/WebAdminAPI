package net.fashiongo.webadmin.model.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Reo
 *
 */
public class SecurityUserData  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> accesstimes;
	private List<String> delaccesstimes;
	private List<String> delgroupnames;
	private List<String> groupnames;
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
