package net.fashiongo.webadmin.model.pojo.sitemgmt;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VendorCategorySummary implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public VendorCategorySummary() {}
	public VendorCategorySummary(Integer vendorCategoryID, String categoryName) {
		this.vendorCategoryID = vendorCategoryID;
		this.categoryName = categoryName;
	}
	
	@JsonProperty("VendorCategoryID")
	private Integer vendorCategoryID;
	
	@JsonProperty("CategoryName")
	private String categoryName;
	
	public Integer getVendorCategoryID() {
		return vendorCategoryID;
	}
	public void setVendorCategoryID(Integer vendorCategoryID) {
		this.vendorCategoryID = vendorCategoryID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}