package net.fashiongo.webadmin.model.pojo.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Nayeon Kim
 */
public class StyleInfo {
	@JsonProperty("StyleID")
	private Integer styleID;
	@JsonProperty("StyleName")
	private String styleName;
	@JsonProperty("Active")
	private Boolean active;
	
	public Integer getStyleID() {
		return styleID;
	}
	public void setStyleID(Integer styleID) {
		this.styleID = styleID;
	}
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
}
