package net.fashiongo.webadmin.model.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Incheol Jung
 */
@Entity
@Table(name = "[Security.List_IP]")
public class SecurityListIP {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IPID")
	private Integer ipID;
	
	@Column(name = "IPAddress")
	private String ipAddress;
	
	@Column(name = "Description")
	private String description;

	public Integer getIpID() {
		return ipID;
	}

	public void setIpID(Integer ipID) {
		this.ipID = ipID;
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
