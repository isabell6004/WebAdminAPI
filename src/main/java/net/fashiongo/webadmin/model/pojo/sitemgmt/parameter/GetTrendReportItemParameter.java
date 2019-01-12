package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import org.apache.commons.lang3.StringUtils;

import io.swagger.annotations.ApiModelProperty;

/**
* @author Nayeon Kim
*/
public class GetTrendReportItemParameter {
	@ApiModelProperty(required = true, example="1")
	private String pagenum;
	@ApiModelProperty(required = true, example="10")
	private String pagesize;
	@ApiModelProperty(required = true, example="328")
	private String trendreportid;
	@ApiModelProperty(hidden=true)
	private String orderby;
	
	public Integer getPagenum() {
		return StringUtils.isEmpty(pagenum) ? 1 : Integer.parseInt(pagenum);
	}
	public void setPagenum(String pagenum) {
		this.pagenum = pagenum;
	}
	public Integer getPagesize() {
		return StringUtils.isEmpty(pagesize) ? 10 : Integer.parseInt(pagesize);
	}
	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}
	public Integer getTrendreportid() {
		return StringUtils.isEmpty(trendreportid) ? 0 : Integer.parseInt(trendreportid);
	}
	public void setTrendreportid(String trendreportid) {
		this.trendreportid = trendreportid;
	}
	public String getOrderby() {
		return StringUtils.isEmpty(orderby) ? "created desc" : orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
}
