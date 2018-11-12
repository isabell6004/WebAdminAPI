package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetProductAttributesParameter {
	@JsonProperty("tabno")
	private Integer tabNo;
	
	@JsonProperty("prevtab")
	private Integer prevTab;
	
	@JsonProperty("categoryid")
	private Integer categoryID;
	
	@JsonProperty("attrname")
	private String attrName;
	
	@JsonProperty("active")
	private Boolean active;

	public Integer getTabNo() {
		return tabNo;
	}

	public void setTabNo(Integer tabNo) {
		this.tabNo = tabNo;
	}

	public Integer getPrevTab() {
		return prevTab;
	}

	public void setPrevTab(Integer prevTab) {
		this.prevTab = prevTab;
	}

	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}
	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
