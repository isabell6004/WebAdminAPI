package net.fashiongo.webadmin.model.pojo;

import java.math.BigDecimal;

import javax.persistence.Column;

/**
 * 
 * @author Incheol Jung
 */
public class PoOrderStatic {
	@Column(name = "ThisOrderAmount")
	private BigDecimal totalOrderAmount;
	@Column(name = "OrderAmountRate")
	private BigDecimal rate;
	
	public BigDecimal getTotalOrderAmount() {
		return totalOrderAmount;
	}
	public void setTotalOrderAmount(BigDecimal totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
}