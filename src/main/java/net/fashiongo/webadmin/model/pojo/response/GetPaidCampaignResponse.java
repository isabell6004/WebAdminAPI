package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.fashiongo.webadmin.model.fgem.EmConfiguration;

/**
 * @author Nayeon Kim
 */
@JsonSerialize
public class GetPaidCampaignResponse {
	@JsonProperty("PaidCampaignList")
	private List<EmConfiguration> emConfigurationsList;

	public List<EmConfiguration> getEmConfigurationsList() {
		return emConfigurationsList;
	}

	public void setConfigurationsList(List<EmConfiguration> emConfigurationsList) {
		this.emConfigurationsList = emConfigurationsList;
	}
}
