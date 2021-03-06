package net.fashiongo.webadmin.model.pojo.bid.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.bid.BidSettingLastRecords;

public class GetBidSettingLastRecordsResponse {
	@JsonProperty("data")
	private List<BidSettingLastRecords> bidSettingLastRecords;

	public List<BidSettingLastRecords> getBidSettingLastRecords() {
		return bidSettingLastRecords;
	}

	public void setBidSettingLastRecords(List<BidSettingLastRecords> bidSettingLastRecords) {
		this.bidSettingLastRecords = bidSettingLastRecords;
	}

}
