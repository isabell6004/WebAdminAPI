package net.fashiongo.webadmin.model.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author DAHYE
 *
 */
@Entity
@Table(name="[security.List_IP]")
public class SecurityAccessIp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IPID")
	@JsonProperty("IPID")
	private Integer ipid;
	
	@Column(name="IPAddress")
	@JsonProperty("IPAddress")
	private String ipAddress;
	
	@Column(name="Description")
	@JsonProperty("Description")
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
