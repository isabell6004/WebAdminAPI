package net.fashiongo.webadmin.model.pojo.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.primary.VendorNewsDetail;

public class SetVendorNewsParameter {
	@JsonProperty("vendornews")
	private VendorNewsDetail vendorNews;
	
	@JsonProperty("selectedvendor")
	private String selectedVendor;

	public VendorNewsDetail getVendorNews() {
		return vendorNews;
	}

	public String getSelectedVendor() {
		return selectedVendor;
	}

	public void setVendorNews(VendorNewsDetail vendorNews) {
		this.vendorNews = vendorNews;
	}

	public void setSelectedVendor(String selectedVendor) {
		this.selectedVendor = selectedVendor;
	}
	
	
}
