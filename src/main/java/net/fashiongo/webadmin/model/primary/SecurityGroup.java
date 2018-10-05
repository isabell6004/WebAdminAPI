package net.fashiongo.webadmin.model.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
@Entity
@Table(name = "[Security.Group]")
public class SecurityGroup {
	@Id
	@Column(name = "GroupID")
	@JsonProperty("GroupID")
	private Integer groupID;
	
	@Column(name = "GroupName")
	@JsonProperty("GroupName")
	private String groupName;
	
	@Column(name = "Description")
	@JsonProperty("Description")
	private String description;
	
	@Column(name = "Active")
	@JsonProperty("Active")
	private Boolean active;

	public Integer getGroupID() {
		return groupID;
	}

	public void setGroupID(Integer groupID) {
		this.groupID = groupID;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	

	
	
	
}
