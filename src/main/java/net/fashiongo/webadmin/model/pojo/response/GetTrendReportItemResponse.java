package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.Total;
import net.fashiongo.webadmin.model.pojo.TrendReportItem;

/**
* @author Nayeon Kim
*/
public class GetTrendReportItemResponse {
	@JsonProperty("Table")
	private List<Total> total;
	
	@JsonProperty("Table1")
	private List<TrendReportItem> trendReportItem;

	public List<Total> getTotal() {
		return total;
	}

	public void setTotal(List<Total> total) {
		this.total = total;
	}

	public List<TrendReportItem> getTrendReportItem() {
		return trendReportItem;
	}

	public void setTrendReportItem(List<TrendReportItem> trendReportItem) {
		this.trendReportItem = trendReportItem;
	}
}
