package net.fashiongo.webadmin.model.pojo.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Nayeon Kim
 */
public class FeaturedItemCount {
	@JsonProperty("FeaturedItemDate")
	private String featuredItemDate;
	@JsonProperty("TotalCount")
	private Integer totalCount;
	@JsonProperty("ActiveSum")
	private Integer activeSum;
	@JsonProperty("NullTotal")
	private Integer nullTotal;
	@JsonProperty("BestItemCount")
	private Integer bestItemCount;
	
	public String getFeaturedItemDate() {
		return featuredItemDate;
	}
	public void setFeaturedItemDate(String featuredItemDate) {
		this.featuredItemDate = featuredItemDate;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getActiveSum() {
		return activeSum;
	}
	public void setActiveSum(Integer activeSum) {
		this.activeSum = activeSum;
	}
	public Integer getNullTotal() {
		return nullTotal;
	}
	public void setNullTotal(Integer nullTotal) {
		this.nullTotal = nullTotal;
	}
	public Integer getBestItemCount() {
		return bestItemCount;
	}
	public void setBestItemCount(Integer bestItemCount) {
		this.bestItemCount = bestItemCount;
	}
}
