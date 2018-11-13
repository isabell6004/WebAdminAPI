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
public class GetCategoryAdItemSearchVendorResponse {
	@JsonProperty("Table")
	private List<CategoryAdCount> count;

	@JsonProperty("Table1")
	private List<SelectData> selectData;

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


	
}
