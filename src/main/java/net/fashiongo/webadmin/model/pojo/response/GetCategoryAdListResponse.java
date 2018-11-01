/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.fashiongo.webadmin.model.pojo.BiddingList2;
import net.fashiongo.webadmin.model.pojo.CategoryList;
import net.fashiongo.webadmin.model.pojo.CuratedBestList;

/**
 * @author Jiwon Kim
 */
@JsonSerialize
public class GetCategoryAdListResponse {
	@JsonProperty("Table")
	private List<CategoryList> categoryList;

	@JsonProperty("Table1")
	private List<BiddingList2> biddingList;

	@JsonProperty("Table2")
	private List<CuratedBestList> CuratedBestList;

	public List<CategoryList> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<CategoryList> categoryList) {
		this.categoryList = categoryList;
	}

	public List<BiddingList2> getBiddingList() {
		return biddingList;
	}

	public void setBiddingList(List<BiddingList2> biddingList) {
		this.biddingList = biddingList;
	}

	public List<CuratedBestList> getCuratedBestList() {
		return CuratedBestList;
	}

	public void setCuratedBestList(List<CuratedBestList> curatedBestList) {
		CuratedBestList = curatedBestList;
	}



	
}
