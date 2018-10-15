package net.fashiongo.webadmin.model.pojo;

import java.math.BigDecimal;

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
	private BigDecimal rate;
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
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	
}
