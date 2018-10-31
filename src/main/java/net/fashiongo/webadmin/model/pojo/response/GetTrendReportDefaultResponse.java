package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.Total;
import net.fashiongo.webadmin.model.pojo.TrendReportDefault;

/**
* @author Nayeon Kim
*/
public class GetTrendReportDefaultResponse {
	@JsonProperty("Table")
	private List<Total> total;
	
	@JsonProperty("Table1")
	private List<TrendReportDefault> trendReportDefault;

	public List<Total> getTotal() {
		return total;
	}

	public void setTotal(List<Total> total) {
		this.total = total;
	}

	public List<TrendReportDefault> getTrendReportDefault() {
		return trendReportDefault;
	}

	public void setTrendReportDefault(List<TrendReportDefault> trendReportDefault) {
		this.trendReportDefault = trendReportDefault;
	}
}
