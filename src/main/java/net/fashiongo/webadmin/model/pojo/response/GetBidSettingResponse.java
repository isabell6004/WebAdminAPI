package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.BidSetting;

public class GetBidSettingResponse {
	@JsonProperty("data")
	private List<BidSetting> bidSetting;

	public List<BidSetting> getBidSetting() {
		return bidSetting;
	}

	public void setBidSetting(List<BidSetting> bidSetting) {
		this.bidSetting = bidSetting;
	}
}
