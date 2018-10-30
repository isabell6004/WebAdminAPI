package net.fashiongo.webadmin.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryCount {
	private Integer categoryID;
	@JsonProperty("VendorCount")
    private Integer vendorCount;
    
	public Integer getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}
	public Integer getVendorCount() {
		return vendorCount;
	}
	public void setVendorCount(Integer vendorCount) {
		this.vendorCount = vendorCount;
	}
}