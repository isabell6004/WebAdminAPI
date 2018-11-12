package net.fashiongo.webadmin.model.pojo.sitemgmt.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.sitemgmt.CategoryCount;
import net.fashiongo.webadmin.model.pojo.sitemgmt.VendorSummary;

public class GetVendorListResponse {
	@JsonProperty("Table")
	private List<CategoryCount> categoryCountlist;
	
	@JsonProperty("Table1")
	private List<VendorSummary> vendorSummarylist;
	
	public List<CategoryCount> getCategoryCountlist() {
		return categoryCountlist;
	}
	public void setCategoryCountlist(List<CategoryCount> categoryCountlist) {
		this.categoryCountlist = categoryCountlist;
	}
	public List<VendorSummary> getVendorSummarylist() {
		return vendorSummarylist;
	}
	public void setVendorSummarylist(List<VendorSummary> vendorSummarylist) {
		this.vendorSummarylist = vendorSummarylist;
	}
	
}