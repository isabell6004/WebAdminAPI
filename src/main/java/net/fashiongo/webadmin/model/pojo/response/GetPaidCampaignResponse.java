package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.PaidCampaignList;

/**
 * @author Nayeon Kim
 */
public class GetPaidCampaignResponse {
	@JsonProperty("PaidCampaignList")
	private List<PaidCampaignList> paidCampaignList;

	public List<PaidCampaignList> getPaidCampaignList() {
		return paidCampaignList;
	}

	public void setPaidCampaignList(List<PaidCampaignList> paidCampaignList) {
		this.paidCampaignList = paidCampaignList;
	}
}
