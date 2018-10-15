package net.fashiongo.webadmin.model.pojo;

import java.util.Date;
import java.util.List;

import net.fashiongo.webadmin.model.primary.SecurityUser;

public class SecurityUserData {
	private Date accesstime;
	private List<String> delaccesstimes;
	private List<String> delgroupnames;
	private List<String> groupnames;
	private SecurityUserCreate user;
	
	public List<String> getDelaccesstimes() {
		return delaccesstimes;
	}
	public void setDelaccesstimes(List<String> delaccesstimes) {
		this.delaccesstimes = delaccesstimes;
	}
	public Date getAccesstime() {
		return accesstime;
	}
	public void setAccesstime(Date accesstime) {
		this.accesstime = accesstime;
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
