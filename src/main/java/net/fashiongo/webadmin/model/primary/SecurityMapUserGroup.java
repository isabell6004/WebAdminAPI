package net.fashiongo.webadmin.model.primary;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Incheol Jung
 */
@Entity
@Table(name = "[security.Map_User_Group]")
public class SecurityMapUserGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MapID")
	@JsonProperty("MapID")
	private Integer mapID;
	
	@Column(name = "UserID")
	@JsonProperty("UserID")
	private Integer userID;
	
	@Column(name = "GroupID")
	@JsonProperty("GroupID")
	private Integer groupID;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "GroupID", insertable = false, updatable = false)
	private List<SecurityGroup> mapUserGroup;

	public List<SecurityGroup> getMapUserGroup() {
		return mapUserGroup;
	}

	public void setMapUserGroup(List<SecurityGroup> mapUserGroup) {
		this.mapUserGroup = mapUserGroup;
	}

	public Integer getMapID() {
		return mapID;
	}

	public void setMapID(Integer mapID) {
		this.mapID = mapID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getGroupID() {
		return groupID;
	}

	public void setGroupID(Integer groupID) {
		this.groupID = groupID;
	}
}