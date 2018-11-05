package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.FeaturedItemCount;
import net.fashiongo.webadmin.model.pojo.FeaturedItemList;

/**
 * @author Nayeon Kim
 */
public class GetFeaturedItemCountResponse {
	@JsonProperty("Table")
	private List<FeaturedItemCount> featuredItemCount;
	
	@JsonProperty("Table1")
	private List<FeaturedItemList> featuredItemList;

	public List<FeaturedItemCount> getFeaturedItemCount() {
		return featuredItemCount;
	}

	public void setFeaturedItemCount(List<FeaturedItemCount> featuredItemCount) {
		this.featuredItemCount = featuredItemCount;
	}

	public List<FeaturedItemList> getFeaturedItemList() {
		return featuredItemList;
	}

	public void setFeaturedItemList(List<FeaturedItemList> featuredItemList) {
		this.featuredItemList = featuredItemList;
	}
}
