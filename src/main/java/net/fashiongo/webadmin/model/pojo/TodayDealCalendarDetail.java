package net.fashiongo.webadmin.model.pojo;

import java.io.Serializable;

import javax.persistence.Column;

public class TodayDealCalendarDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column(name="TodayDealID")
	private Integer todayDealID;
	private Integer dom;
	private String companyName;
	private String fromDate;
	private Boolean active;
	private Integer wholeSalerID;
	private Boolean createdByVendor;
	
	public Integer getTodayDealID() {
		return todayDealID;
	}
	public void setTodayDealID(Integer todayDealID) {
		this.todayDealID = todayDealID;
	}
	public Integer getDom() {
		return dom;
	}
	public void setDom(Integer dom) {
		this.dom = dom;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Integer getWholeSalerID() {
		return wholeSalerID;
	}
	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}
	public Boolean getCreatedByVendor() {
		return createdByVendor;
	}
	public void setCreatedByVendor(Boolean createdByVendor) {
		this.createdByVendor = createdByVendor;
	}
	
}
