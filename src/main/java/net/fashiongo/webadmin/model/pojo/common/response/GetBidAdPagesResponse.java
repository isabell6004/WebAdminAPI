package net.fashiongo.webadmin.model.pojo.common.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.primary.AdPage;

public class GetBidAdPagesResponse {
	
	@JsonProperty("Table")
	private List<AdPage> adPage;

	public List<AdPage> getAdPage() {
		return adPage;
	}

	public void setAdPage(List<AdPage> adPage) {
		this.adPage = adPage;
	}
}
