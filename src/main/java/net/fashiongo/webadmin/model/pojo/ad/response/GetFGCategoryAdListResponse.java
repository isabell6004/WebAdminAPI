/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.ad.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.fashiongo.webadmin.model.pojo.ad.FGListADList;

/**
 * @author Jiwon Kim
 */
@JsonSerialize
public class GetFGCategoryAdListResponse {
	@JsonProperty("Table")
	private List<FGListADList> fgCategoryList;

	public List<FGListADList> getFgCategoryList() {
		return fgCategoryList;
	}

	public void setFgCategoryList(List<FGListADList> fgCategoryList) {
		this.fgCategoryList = fgCategoryList;
	}	
}
