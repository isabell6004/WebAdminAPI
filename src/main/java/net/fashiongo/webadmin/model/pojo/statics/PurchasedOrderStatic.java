package net.fashiongo.webadmin.model.pojo.statics;

import java.math.BigDecimal;

import javax.persistence.Column;

/**
 * 
 * @author Incheol Jung
 */
public class PurchasedOrderStatic {
	@Column(name = "ThisOrderCount")
	private Integer totalPurchasedOrders;
	@Column(name = "OrderRate")
    private BigDecimal rate;
    
    public Integer getTotalPurchasedOrders() {
		return totalPurchasedOrders;
	}
	public void setTotalPurchasedOrders(Integer totalPurchasedOrders) {
		this.totalPurchasedOrders = totalPurchasedOrders;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
}