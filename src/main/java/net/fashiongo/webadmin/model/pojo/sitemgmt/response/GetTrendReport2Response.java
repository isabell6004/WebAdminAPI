package net.fashiongo.webadmin.model.pojo.sitemgmt.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.message.Total;
import net.fashiongo.webadmin.model.pojo.sitemgmt.TrendReportList;

/**
* @author Nayeon Kim
*/
public class GetTrendReport2Response {
	@JsonProperty("Table")
	private List<Total> total;
	
	@JsonProperty("Table1")
	private List<TrendReportList> trendReportList;

	public List<Total> getTotal() {
		return total;
	}

	public void setTotal(List<Total> total) {
		this.total = total;
	}

	public List<TrendReportList> getTrendReportList() {
		return trendReportList;
	}

	public void setTrendReportList(List<TrendReportList> trendReportList) {
		this.trendReportList = trendReportList;
	}
}
