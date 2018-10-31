package net.fashiongo.webadmin.model.pojo.parameter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
* @author Nayeon Kim
*/
public class GetTrendReport2Parameter {
	@ApiModelProperty(required = false, example = "1")
	public String pagenum;
	
	@ApiModelProperty(required = false, example = "10")
	public String pagesize;
	
	@ApiModelProperty(required = false, example = "")
	public String searchtxt;
	
	@ApiModelProperty(required = false, example = "9/1/2018 00:00:00")
	public String fromdate;
	
	@ApiModelProperty(required = false, example = "9/30/2018 23:59:59")
	public String todate;
	
	@ApiModelProperty(required = false, example = "true")
	public String active;
	
	@ApiModelProperty(required = false, example = "DateFrom")
	public String orderby;
	
	@ApiModelProperty(required = false, example = "Desc")
	public String orderbygubn;
	
	@ApiModelProperty(required = false, example = "1")
	@JsonProperty("CuratedType")
	public String curatedType;

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

	public String getSearchtxt() {
		return StringUtils.isEmpty(searchtxt) ? null : searchtxt;
	}

	public void setSearchtxt(String searchtxt) {
		this.searchtxt = searchtxt;
	}

	public LocalDateTime getFromdate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
		return StringUtils.isEmpty(this.fromdate) ? null : LocalDateTime.parse(this.fromdate, formatter);
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public LocalDateTime getTodate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
		return StringUtils.isEmpty(this.todate) ? null : LocalDateTime.parse(this.todate, formatter);
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

	public Boolean getActive() {
		return StringUtils.isEmpty(this.active) ? null : Boolean.parseBoolean(this.active);
	}

	public void setActive(String active) {
		this.active = active;
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

	public Integer getCuratedType() {
		return StringUtils.isEmpty(curatedType) ? null : Integer.parseInt(curatedType);
	}

	public void setCuratedType(String curatedType) {
		this.curatedType = curatedType;
	}
}






