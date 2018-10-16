package net.fashiongo.webadmin.model.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "[Map_Wa_User_Vendor]")
public class MapWaUserVendor {
	@Id
	@Column(name = "MapID")
	@JsonProperty("MapID")
	private Integer mapID;
	
	@Column(name = "UserID")
	@JsonProperty("UserID")
	private Integer userID;
	
	@Column(name = "VendorID")
	@JsonProperty("VendorID")
	private Integer vendorID;

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

	public Integer getVendorID() {
		return vendorID;
	}

	public void setVendorID(Integer vendorID) {
		this.vendorID = vendorID;
	}
	
	
}
