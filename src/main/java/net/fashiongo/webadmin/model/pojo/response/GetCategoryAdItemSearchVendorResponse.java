/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.fashiongo.webadmin.model.pojo.CategoryAdCount;
import net.fashiongo.webadmin.model.pojo.SelectData;
import net.fashiongo.webadmin.model.pojo.VendorCount;
import net.fashiongo.webadmin.model.pojo.VendorData1;
import net.fashiongo.webadmin.model.pojo.VendorData2;

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
