package net.fashiongo.webadmin.model.pojo.admin.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class SetUserMappingVendorParameter {
	@ApiModelProperty(required = true, example="1")
	@JsonProperty("userid")
	private Integer userID;
	@ApiModelProperty(required = true, example="1,3")
	@JsonProperty("swids")
	private String saveWholeSalerIDs;
	@ApiModelProperty(required = true, example="1,3")
	@JsonProperty("rwids")
	private String removeWholeSalerIDs;
	
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getSaveWholeSalerIDs() {
		return saveWholeSalerIDs;
	}
	public void setSaveWholeSalerIDs(String saveWholeSalerIDs) {
		this.saveWholeSalerIDs = saveWholeSalerIDs;
	}
	public String getRemoveWholeSalerIDs() {
		return removeWholeSalerIDs;
	}
	public void setRemoveWholeSalerIDs(String removeWholeSalerIDs) {
		this.removeWholeSalerIDs = removeWholeSalerIDs;
	}
	
	
}
