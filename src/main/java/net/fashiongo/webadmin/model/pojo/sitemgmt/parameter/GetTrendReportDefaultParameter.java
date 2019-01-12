package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import org.apache.commons.lang3.StringUtils;

import io.swagger.annotations.ApiModelProperty;

/**
* @author Nayeon Kim
*/
public class GetTrendReportDefaultParameter {
	@ApiModelProperty(required = false, example = "1")
	public String pagenum;
	@ApiModelProperty(required = false, example = "10")
	public String pagesize;
	@ApiModelProperty(required = false, example = "DateFrom")
	public String orderby;
	@ApiModelProperty(required = false, example = "Desc")
	public String orderbygubn;
	
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
	public String getOrderby() {
		return StringUtils.isEmpty(orderby) ? null : orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	public String getOrderbygubn() {
		return StringUtils.isEmpty(orderbygubn) ? null : orderbygubn;
	}
	public void setOrderbygubn(String orderbygubn) {
		this.orderbygubn = orderbygubn;
	}
}
