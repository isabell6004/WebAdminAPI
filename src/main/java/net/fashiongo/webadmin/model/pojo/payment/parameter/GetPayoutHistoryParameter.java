package net.fashiongo.webadmin.model.pojo.payment.parameter;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 
 * @author DAHYE
 *
 */
public class GetPayoutHistoryParameter {
	@JsonProperty("pagenum")
	private Integer pagenum;
	
	@JsonProperty("pagesize")
	private Integer pagesize;
	
	@JsonProperty("wholesalerid")
	private Integer wholesalerid;
	
	@JsonProperty("fromdate")
	private LocalDateTime fromdate;
	
	@JsonProperty("todate")
	private LocalDateTime todate;
	
	@JsonProperty("payoutstatus")
	private String payoutstatus;
	
	@JsonProperty("payoutschedule")
	private String payoutschedule;
	
	@JsonProperty("orderby")
	private String orderby;

	public Integer getPagenum() {
		return pagenum == null ? 0 : pagenum;
	}

	public Integer getPagesize() {
		return pagesize == null ? 0 : pagesize;
	}

	public Integer getWholesalerid() {
		return wholesalerid;
	}

	public LocalDateTime getFromdate() {
		return fromdate;
	}

	public LocalDateTime getTodate() {
		return todate;
	}

	public String getPayoutstatus() {
		return StringUtils.isEmpty(payoutstatus) ? null : payoutstatus;
	}

	public String getPayoutschedule() {
		return StringUtils.isEmpty(payoutschedule) ? null : payoutschedule;
	}

	public String getOrderby() {
		return StringUtils.isEmpty(orderby) ? null : orderby;
	}

	public void setPagenum(Integer pagenum) {
		this.pagenum = pagenum;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public void setWholesalerid(Integer wholesalerid) {
		this.wholesalerid = wholesalerid;
	}

	public void setFromdate(LocalDateTime fromdate) {
		this.fromdate = fromdate;
	}

	public void setTodate(LocalDateTime todate) {
		this.todate = todate;
	}

	public void setPayoutstatus(String payoutstatus) {
		this.payoutstatus = payoutstatus;
	}

	public void setPayoutschedule(String payoutschedule) {
		this.payoutschedule = payoutschedule;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	
	
}
