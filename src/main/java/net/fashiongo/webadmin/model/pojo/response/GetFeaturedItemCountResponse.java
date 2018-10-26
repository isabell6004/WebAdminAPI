package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.FeaturedItem;
import net.fashiongo.webadmin.model.pojo.FeaturedItemCount;

/**
 * @author Nayeon Kim
 */
public class GetFeaturedItemCountResponse {
	@JsonProperty("Table")
	private List<FeaturedItemCount> featuredItemCountlist;
	
	@JsonProperty("Table1")
	private List<FeaturedItem> featuredItemlist;

	public List<FeaturedItemCount> getFeaturedItemCountlist() {
		return featuredItemCountlist;
	}

	public void setFeaturedItemCountlist(List<FeaturedItemCount> featuredItemCountlist) {
		this.featuredItemCountlist = featuredItemCountlist;
	}

	public List<FeaturedItem> getFeaturedItemlist() {
		return featuredItemlist;
	}

	public void setFeaturedItemlist(List<FeaturedItem> featuredItemlist) {
		this.featuredItemlist = featuredItemlist;
	}
}
