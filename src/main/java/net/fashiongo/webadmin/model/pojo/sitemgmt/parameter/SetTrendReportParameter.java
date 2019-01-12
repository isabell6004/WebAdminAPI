package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.primary.TrendReport;

public class SetTrendReportParameter {
	@JsonProperty("type")
	private String type;

	@JsonProperty("obj")
	private TrendReport trendreport;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public TrendReport getTrendreport() {
		return trendreport;
	}

	public void setTrendreport(TrendReport trendreport) {
		this.trendreport = trendreport;
	}

}
