package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.BidSettingLastRecords;

public class GetBidSettingLastRecordsResponse {
	public GetBidSettingLastRecordsResponse() {
	}

	public GetBidSettingLastRecordsResponse(Boolean success, List<BidSettingLastRecords> bidSettingLastRecords) {
		this.success = success;
		this.bidSettingLastRecords = bidSettingLastRecords;
	}

	private Boolean success;

	@JsonProperty("data")
	private List<BidSettingLastRecords> bidSettingLastRecords;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public List<BidSettingLastRecords> getBidSettingLastRecords() {
		return bidSettingLastRecords;
	}

	public void setBidSettingLastRecords(List<BidSettingLastRecords> bidSettingLastRecords) {
		this.bidSettingLastRecords = bidSettingLastRecords;
	}

}
