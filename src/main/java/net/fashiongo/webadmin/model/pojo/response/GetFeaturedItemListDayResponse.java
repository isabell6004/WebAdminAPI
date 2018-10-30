package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.FeaturedItem;

public class GetFeaturedItemListDayResponse {
	@JsonProperty("Table")
	private List<FeaturedItem> featuredItemlist;

	public List<FeaturedItem> getFeaturedItemlist() {
		return featuredItemlist;
	}

	public void setFeaturedItemlist(List<FeaturedItem> featuredItemlist) {
		this.featuredItemlist = featuredItemlist;
	}
}
