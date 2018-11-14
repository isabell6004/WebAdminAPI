/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.fashiongo.webadmin.model.pojo.BiddingList2;
import net.fashiongo.webadmin.model.pojo.CuratedBestList;

/**
 * @author Jiwon Kim
 */
@JsonSerialize
public class GetCategoryAdDetailResponse {
	@JsonProperty("Table")
	private List<BiddingList2> biddingList;

	@JsonProperty("Table1")
	private List<CuratedBestList> curatedBestList;

	public List<BiddingList2> getBiddingList() {
		return biddingList;
	}

	public void setBiddingList(List<BiddingList2> biddingList) {
		this.biddingList = biddingList;
	}

	public List<CuratedBestList> getCuratedBestList() {
		return curatedBestList;
	}

	public void setCuratedBestList(List<CuratedBestList> curatedBestList) {
		this.curatedBestList = curatedBestList;
	}
	
	


	
}
