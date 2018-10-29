/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.fashiongo.webadmin.model.pojo.BidList;

/**
 * @author Jiwon Kim
 */
@JsonSerialize
public class GetCategoryAdItemForBidVendorResponse {
	@JsonProperty("Table")
	private List<BidList> bidList;

	public List<BidList> getBidList() {
		return bidList;
	}

	public void setBidList(List<BidList> bidList) {
		this.bidList = bidList;
	}

	


	
}
