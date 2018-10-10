package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.fgem.EmConfiguration;

/**
 * @author Nayeon Kim
 */
public class GetPaidCampaignResponse {
	@JsonProperty("PaidCampaignList")
	private List<EmConfiguration> configurationsList;

	public List<EmConfiguration> getPaidCampaignList() {
		return configurationsList;
	}

	public void setPaidCampaignList(List<EmConfiguration> configurationsList) {
		this.configurationsList = configurationsList;
	}
}
