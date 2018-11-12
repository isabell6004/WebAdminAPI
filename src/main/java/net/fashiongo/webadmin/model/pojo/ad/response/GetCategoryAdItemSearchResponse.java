/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.ad.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.fashiongo.webadmin.model.pojo.ad.CategoryAdCount;
import net.fashiongo.webadmin.model.pojo.ad.SelectData;
import net.fashiongo.webadmin.model.pojo.ad.VendorCount;
import net.fashiongo.webadmin.model.pojo.ad.VendorData1;
import net.fashiongo.webadmin.model.pojo.ad.VendorData2;

/**
 * @author Jiwon Kim
 */
@JsonSerialize
public class GetCategoryAdItemSearchResponse {
	@JsonProperty("Table")
	private List<CategoryAdCount> count;

	@JsonProperty("Table1")
	private List<SelectData> selectData;

	@JsonProperty("Table2")
	private List<VendorCount> vendorCount;

	@JsonProperty("Table3")
	private List<VendorData1> vendorData1;
	
	@JsonProperty("Table4")
	private List<VendorData2> vendorData2;

	public List<CategoryAdCount> getCount() {
		return count;
	}

	public void setCount(List<CategoryAdCount> count) {
		this.count = count;
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

	public List<VendorData2> getVendorData2() {
		return vendorData2;
	}

	public void setVendorData2(List<VendorData2> vendorData2) {
		this.vendorData2 = vendorData2;
	}

	

	


	
}
