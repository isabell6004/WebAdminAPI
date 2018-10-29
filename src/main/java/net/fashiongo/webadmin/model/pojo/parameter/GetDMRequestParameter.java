package net.fashiongo.webadmin.model.pojo.parameter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author Incheol Jung
 */
public class GetDMRequestParameter implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = false, example="1")
	private String pagenum;
	@ApiModelProperty(required = false, example="10")
	private String pagesize;
	@ApiModelProperty(required = false, example="2858")
	private String wholesalerid;
	@ApiModelProperty(required = false, example="2,1,3")
	private String companytypecd;
	@ApiModelProperty(required = false, example="Requested")
	private String status;
	@ApiModelProperty(required = false, example="1/1/2018 00:00:00")
	private String datefrom;
	@ApiModelProperty(required = false, example="12/31/2018 23:59:59")
	private String dateto;
	@ApiModelProperty(required = false, example="")
	private String orderby;
	
	public Integer getPagenum() {
		return StringUtils.isEmpty(this.pagenum) ? 1 : Integer.parseInt(this.pagenum);
	}
	public void setPagenum(String pagenum) {
		this.pagenum = pagenum;
	}
	public Integer getPagesize() {
		return StringUtils.isEmpty(this.pagesize) ? 10 : Integer.parseInt(this.pagesize);
	}
	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}
	public Integer getWholesalerid() {
		return StringUtils.isEmpty(this.wholesalerid) ? 0 : Integer.parseInt(this.wholesalerid);
	}
	public void setWholesalerid(String wholesalerid) {
		this.wholesalerid = wholesalerid;
	}
	public String getCompanytypecd() {
		return StringUtils.isEmpty(this.companytypecd) ? null : this.companytypecd;
	}
	public void setCompanytypecd(String companytypecd) {
		this.companytypecd = companytypecd;
	}
	public String getStatus() {
		return StringUtils.isEmpty(this.status) ? null : this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getDatefrom() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
        return StringUtils.isEmpty(this.datefrom) ? null : LocalDateTime.parse(this.datefrom, formatter);
	}
	public void setDatefrom(String datefrom) {
		this.datefrom = datefrom;
	}
	public LocalDateTime getDateto() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
        return StringUtils.isEmpty(this.dateto) ? null : LocalDateTime.parse(this.dateto, formatter);
	}
	public void setDateto(String dateto) {
		this.dateto = dateto;
	}
	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	
}
