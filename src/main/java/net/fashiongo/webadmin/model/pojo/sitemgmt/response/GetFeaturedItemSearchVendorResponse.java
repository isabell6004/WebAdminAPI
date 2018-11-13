package net.fashiongo.webadmin.model.pojo.sitemgmt.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.ad.CategoryAdCount;
import net.fashiongo.webadmin.model.pojo.ad.SelectData;

/**
* @author Junghwan Lee
*/
public class GetFeaturedItemSearchVendorResponse {
	@JsonProperty("Table")
	private List<CategoryAdCount> categoryAdCount;
	
	@JsonProperty("Table1")
	private List<SelectData> selectData;

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
}
