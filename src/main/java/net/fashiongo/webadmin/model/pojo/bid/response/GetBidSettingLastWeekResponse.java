package net.fashiongo.webadmin.model.pojo.bid.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.bid.BidSettingLastWeek;

public class GetBidSettingLastWeekResponse {
	
	@JsonProperty("Table")
	private List<BidSettingLastWeek> bidSettingLastWeek;
	
	@JsonProperty("Table1")
	private List<BidSettingLastWeek> bidSettingLastWeek1;

	public List<BidSettingLastWeek> getBidSettingLastWeek() {
		return bidSettingLastWeek;
	}

	public void setBidSettingLastWeek(List<BidSettingLastWeek> bidSettingLastWeek) {
		this.bidSettingLastWeek = bidSettingLastWeek;
	}

	public List<BidSettingLastWeek> getBidSettingLastWeek1() {
		return bidSettingLastWeek1;
	}

	public void setBidSettingLastWeek1(List<BidSettingLastWeek> bidSettingLastWeek1) {
		this.bidSettingLastWeek1 = bidSettingLastWeek1;
	}
	
}
