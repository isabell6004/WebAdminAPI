package net.fashiongo.webadmin.model.pojo.statics;

import java.math.BigDecimal;

import javax.persistence.Column;

/**
 * 
 * @author Incheol Jung
 */
public class ActiveBuyerStatic {
	@Column(name = "TotalBuyerCount")
	private Integer totalBuyerCount;
	@Column(name = "ActiveBuyerCount")
	private Integer activeBuyerCount;
	@Column(name = "LifetimeRate")
	private BigDecimal rate;
	public Integer getTotalBuyerCount() {
		return totalBuyerCount;
	}
	public void setTotalBuyerCount(Integer totalBuyerCount) {
		this.totalBuyerCount = totalBuyerCount;
	}
	public Integer getActiveBuyerCount() {
		return activeBuyerCount;
	}
	public void setActiveBuyerCount(Integer activeBuyerCount) {
		this.activeBuyerCount = activeBuyerCount;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
}
