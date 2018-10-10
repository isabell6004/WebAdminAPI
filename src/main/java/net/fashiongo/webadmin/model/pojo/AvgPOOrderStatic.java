package net.fashiongo.webadmin.model.pojo;

import javax.persistence.Column;

/**
 * 
 * @author Incheol Jung
 */
public class AvgPOOrderStatic {
	@Column(name = "ThisOrderAmount")
	private Double totalOrderAmount;
	@Column(name = "OrderAmountRate")
	private Double rate;
	public Double getTotalOrderAmount() {
		return totalOrderAmount;
	}
	public void setTotalOrderAmount(Double totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
}
