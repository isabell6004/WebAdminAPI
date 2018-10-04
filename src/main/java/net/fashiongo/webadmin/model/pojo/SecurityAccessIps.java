package net.fashiongo.webadmin.model.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Dahye Jeong
 */
@Entity
@Table(name="security.List_IP")
public class SecurityAccessIps {
	private Integer ipid;
	private String ipAddress;
	private String description;
	public Integer getIpid() {
		return ipid;
	}
	public void setIpid(Integer ipid) {
		this.ipid = ipid;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
