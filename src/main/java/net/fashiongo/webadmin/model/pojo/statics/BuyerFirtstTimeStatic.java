package net.fashiongo.webadmin.model.pojo.statics;

import java.math.BigDecimal;

import javax.persistence.Column;

/**
 * 
 * @author Incheol Jung
 */
public class BuyerFirtstTimeStatic {
	@Column(name = "ThisFirstTimePurchaseCount")
	private Integer firstTimePurchaseCount;
	@Column(name = "FirstTimePurchaseRate")
	private BigDecimal rate;
	
	public Integer getFirstTimePurchaseCount() {
		return firstTimePurchaseCount;
	}
	public void setFirstTimePurchaseCount(Integer firstTimePurchaseCount) {
		this.firstTimePurchaseCount = firstTimePurchaseCount;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
}
