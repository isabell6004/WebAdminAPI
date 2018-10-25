package net.fashiongo.webadmin.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Nayeon Kim
 */
public class BodySizeInfo {
	@JsonProperty("BodySizeID")
	private Integer bodySizeID;
	@JsonProperty("BodySizeName")
	private String bodySizeName;
	@JsonProperty("TitleImage")
	private String titleImage;
	@JsonProperty("CategoryID")
	private Integer categoryID;
	@JsonProperty("Active")
	private Boolean active;
	
	public Integer getBodySizeID() {
		return bodySizeID;
	}
	public void setBodySizeID(Integer bodySizeID) {
		this.bodySizeID = bodySizeID;
	}
	public String getBodySizeName() {
		return bodySizeName;
	}
	public void setBodySizeName(String bodySizeName) {
		this.bodySizeName = bodySizeName;
	}
	public String getTitleImage() {
		return titleImage;
	}
	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}
	public Integer getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
}
