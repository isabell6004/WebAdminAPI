package net.fashiongo.webadmin.model.pojo.statics;

import java.math.BigDecimal;

import javax.persistence.Column;

/**
 * 
 * @author Incheol Jung
 */
public class PurchasingBuyerStatic {
	@Column(name = "PurchasingBuyerCount")
	private Integer purchasingBuyerCount;
	@Column(name = "ActiveBuyerCount")
	private Integer activeBuyerCount;
	@Column(name = "LifetimeRate")
	private BigDecimal rate;
	
	public Integer getPurchasingBuyerCount() {
		return purchasingBuyerCount;
	}
	public void setPurchasingBuyerCount(Integer purchasingBuyerCount) {
		this.purchasingBuyerCount = purchasingBuyerCount;
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