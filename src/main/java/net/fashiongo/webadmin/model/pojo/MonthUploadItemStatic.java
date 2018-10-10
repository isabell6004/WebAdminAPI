package net.fashiongo.webadmin.model.pojo;

import javax.persistence.Column;

/**
 * 
 * @author Incheol Jung
 */
public class MonthUploadItemStatic {
	@Column(name = "ThisItem")
	private Integer thisMonth;
	@Column(name = "LastItem")
	private Integer lastMonth;
	@Column(name = "ItemRate")
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
