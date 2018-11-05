package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.FeaturedItemList;

public class GetFeaturedItemListDayResponse {
	@JsonProperty("Table")
	private List<FeaturedItemList> featuredItemList;

	public List<FeaturedItemList> getFeaturedItemList() {
		return featuredItemList;
	}

	public void setFeaturedItemList(List<FeaturedItemList> featuredItemList) {
		this.featuredItemList = featuredItemList;
	}
}
