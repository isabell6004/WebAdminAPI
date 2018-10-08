package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.BidSetting;

public class GetBidSettingResponse {
	public GetBidSettingResponse() {
	}

	public GetBidSettingResponse(Boolean success, List<BidSetting> bidSetting) {
		this.success = success;
		this.bidSetting = bidSetting;
	}

	private Boolean success;

	@JsonProperty("data")
	private List<BidSetting> bidSetting;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public List<BidSetting> getBidSetting() {
		return bidSetting;
	}

	public void setBidSetting(List<BidSetting> bidSetting) {
		this.bidSetting = bidSetting;
	}
}
