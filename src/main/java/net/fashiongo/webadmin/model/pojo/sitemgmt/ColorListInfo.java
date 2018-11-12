package net.fashiongo.webadmin.model.pojo.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Nayeon Kim
 */
public class ColorListInfo {
	@JsonProperty("ColorListID")
	private Integer colorListID;
	@JsonProperty("MasterColorName")
	private String masterColorName;
	@JsonProperty("ColorName")
	private String colorName;
	@JsonProperty("Active")
	private Boolean active;
	
	public Integer getColorListID() {
		return colorListID;
	}
	public void setColorListID(Integer colorListID) {
		this.colorListID = colorListID;
	}
	public String getMasterColorName() {
		return masterColorName;
	}
	public void setMasterColorName(String masterColorName) {
		this.masterColorName = masterColorName;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
}
