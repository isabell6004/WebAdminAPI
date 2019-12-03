package net.fashiongo.webadmin.data.model.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GetTrendReportParameter {

	@JsonProperty("pagenum")
	private Integer pagenum;

	@JsonProperty("pagesize")
	private Integer pagesize;

	@JsonProperty("searchtxt")
	private String searchtxt;

	@JsonProperty("fromdate")
	private String fromdate;

	@JsonProperty("todate")
	private String todate;

	@JsonProperty("active")
	private String active;

	@JsonProperty("orderby")
	private String orderby;

	@JsonProperty("orderbygubn")
	private String orderbygubn;
}
