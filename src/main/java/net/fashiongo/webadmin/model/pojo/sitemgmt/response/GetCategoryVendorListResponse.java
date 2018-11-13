package net.fashiongo.webadmin.model.pojo.sitemgmt.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.sitemgmt.CategoryCount;
import net.fashiongo.webadmin.model.pojo.sitemgmt.CategoryVendor;
import net.fashiongo.webadmin.model.pojo.sitemgmt.CategoryVendorInfo;

/**
 * @author Nayeon Kim
 */
public class GetCategoryVendorListResponse {
	@JsonProperty("Table")
	private List<CategoryCount> categoryCountlist;
	
	@JsonProperty("Table1")
	private List<CategoryVendor> categoryVendorList;
	
	@JsonProperty("Table2")
	private List<CategoryVendorInfo> categoryVendorInfoList;

	public List<CategoryCount> getCategoryCountlist() {
		return categoryCountlist;
	}

	public void setCategoryCountlist(List<CategoryCount> categoryCountlist) {
		this.categoryCountlist = categoryCountlist;
	}

	public List<CategoryVendor> getCategoryVendorList() {
		return categoryVendorList;
	}

	public void setCategoryVendorList(List<CategoryVendor> categoryVendorList) {
		this.categoryVendorList = categoryVendorList;
	}

	public List<CategoryVendorInfo> getCategoryVendorInfoList() {
		return categoryVendorInfoList;
	}

	public void setCategoryVendorInfoList(List<CategoryVendorInfo> categoryVendorInfoList) {
		this.categoryVendorInfoList = categoryVendorInfoList;
	}
}
