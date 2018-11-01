package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.CategoryAdCount;
import net.fashiongo.webadmin.model.pojo.FeaturedVendorDaily;
import net.fashiongo.webadmin.model.pojo.SelectData;
import net.fashiongo.webadmin.model.pojo.VendorCount;
import net.fashiongo.webadmin.model.pojo.VendorData1;

/**
* @author Junghwan Lee
*/
public class GetFeaturedItemSearchResponse {
	@JsonProperty("Table")
	private List<CategoryAdCount> categoryAdCount;
	
	@JsonProperty("Table1")
	private List<SelectData> selectData;
	
	@JsonProperty("Table2")
	private List<VendorCount> vendorCount;
	
	@JsonProperty("Table3")
	private List<VendorData1> vendorData1;
	
	@JsonProperty("Table4")
	private List<FeaturedVendorDaily> featuredVendorDaily;

	public List<CategoryAdCount> getCategoryAdCount() {
		return categoryAdCount;
	}

	public void setCategoryAdCount(List<CategoryAdCount> categoryAdCount) {
		this.categoryAdCount = categoryAdCount;
	}

	public List<SelectData> getSelectData() {
		return selectData;
	}

	public void setSelectData(List<SelectData> selectData) {
		this.selectData = selectData;
	}

	public List<VendorCount> getVendorCount() {
		return vendorCount;
	}

	public void setVendorCount(List<VendorCount> vendorCount) {
		this.vendorCount = vendorCount;
	}

	public List<VendorData1> getVendorData1() {
		return vendorData1;
	}

	public void setVendorData1(List<VendorData1> vendorData1) {
		this.vendorData1 = vendorData1;
	}

	public List<FeaturedVendorDaily> getFeaturedVendorDaily() {
		return featuredVendorDaily;
	}

	public void setFeaturedVendorDaily(List<FeaturedVendorDaily> featuredVendorDaily) {
		this.featuredVendorDaily = featuredVendorDaily;
	}
}
