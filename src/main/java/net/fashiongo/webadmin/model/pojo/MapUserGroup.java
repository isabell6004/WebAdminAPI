package net.fashiongo.webadmin.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MapUserGroup {
	@JsonProperty("MapID")
	private Integer mapID;
	
	@JsonProperty("UserID")
	private Integer userID;
	
	@JsonProperty("GroupID")
	private Integer groupID;
	
	@JsonProperty("GroupName")
	private String groupName;

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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	
}
