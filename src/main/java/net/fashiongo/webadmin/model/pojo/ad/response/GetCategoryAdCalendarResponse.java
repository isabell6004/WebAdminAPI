/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.ad.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.fashiongo.webadmin.model.pojo.ad.BiddingList;
import net.fashiongo.webadmin.model.pojo.ad.CollectionCategory;
import net.fashiongo.webadmin.model.pojo.ad.CuratedList;

/**
 * @author Jiwon Kim
 */
@JsonSerialize
public class GetCategoryAdCalendarResponse {
	@JsonProperty("Table")
	private List<CollectionCategory> collectionCategory;

	@JsonProperty("Table1")
	private List<BiddingList> biddingList;
	
	@JsonProperty("Table2")
	private List<CuratedList> curatedList;

	public List<CollectionCategory> getCollectionCategory() {
		return collectionCategory;
	}

	public void setCollectionCategory(List<CollectionCategory> collectionCategory) {
		this.collectionCategory = collectionCategory;
	}

	public List<BiddingList> getBiddingList() {
		return biddingList;
	}

	public void setBiddingList(List<BiddingList> biddingList) {
		this.biddingList = biddingList;
	}

	public List<CuratedList> getCuratedList() {
		return curatedList;
	}

	public void setCuratedList(List<CuratedList> curatedList) {
		this.curatedList = curatedList;
	}



	
}
