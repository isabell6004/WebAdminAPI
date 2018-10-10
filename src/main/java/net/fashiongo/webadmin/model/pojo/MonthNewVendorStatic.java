package net.fashiongo.webadmin.model.pojo;

import javax.persistence.Column;

/**
 * 
 * @author Incheol Jung
 */
public class MonthNewVendorStatic {
	@Column(name = "ThisVendor")
	private Integer thisMonth;
	@Column(name = "LastVendor")
	private Integer lastMonth;
	@Column(name = "VendorRate")
	private Double rate;
	public Integer getThisMonth() {
		return thisMonth;
	}
	public void setThisMonth(Integer thisMonth) {
		this.thisMonth = thisMonth;
	}
	public Integer getLastMonth() {
		return lastMonth;
	}
	public void setLastMonth(Integer lastMonth) {
		this.lastMonth = lastMonth;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	
}
