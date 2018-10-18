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
@Table(name = "[security.Resource]")
public class SecurityResource {
	@Id
	@Column(name = "ResourceID")
	@JsonProperty("ResourceID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer resourceID;
	
	@Column(name = "ApplicationID")
	@JsonProperty("ApplicationID")
	private Integer applicationID;
	
	@Column(name = "Name")
	@JsonProperty("Name")
	private String name;
	
	@Column(name = "ParentID")
	@JsonProperty("ParentID")
	private Integer parentID;
	
	@Column(name = "Active")
	@JsonProperty("Active")
	private Boolean active;
	
	@Column(name = "ResourceType")
	@JsonProperty("ResourceType")
	private String resourceType;
	
	@Column(name = "URL")
	@JsonProperty("URL")
	private String url;

	public Integer getResourceID() {
		return resourceID;
	}

	public void setResourceID(Integer resourceID) {
		this.resourceID = resourceID;
	}

	public Integer getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(Integer applicationID) {
		this.applicationID = applicationID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentID() {
		return parentID;
	}

	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
