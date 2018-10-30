/**
 * 
 */
package net.fashiongo.webadmin.model.fgem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Incheol Jung
 */
@Entity
@Table(name = "\"EM.Application\"")
public class EmApplication {
	@Id
	@Column(name = "AppID")
	private Integer appID;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Code")
	private String code;
	
	@Column(name = "Guid")
	private String guid;

	public Integer getAppID() {
		return appID;
	}

	public void setAppID(Integer appID) {
		this.appID = appID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
	
}