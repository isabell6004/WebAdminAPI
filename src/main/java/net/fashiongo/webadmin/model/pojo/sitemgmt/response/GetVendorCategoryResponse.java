package net.fashiongo.webadmin.model.pojo.sitemgmt.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.sitemgmt.VendorCategorySummary;

/**
 * 
 * @author Incheol Jung
 */
public class GetVendorCategoryResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	@JsonProperty("VCategoryList")
	private List<VendorCategorySummary> vendorCategorySummaryList;
	public List<VendorCategorySummary> getVendorCategorySummaryList() {
		return vendorCategorySummaryList;
	}
	public void setVendorCategorySummaryList(List<VendorCategorySummary> vendorCategorySummaryList) {
		this.vendorCategorySummaryList = vendorCategorySummaryList;
	}
	
}
