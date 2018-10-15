package net.fashiongo.webadmin.model.pojo.parameter;

import io.swagger.annotations.ApiModelProperty;

public class SetUserMappingVendorParameter {
	@ApiModelProperty(required = true, example="1")
	private Integer userID;
	@ApiModelProperty(required = true, example="1,3")
	private String saveWholeSalerIDs;
	@ApiModelProperty(required = true, example="1,3")
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
