package net.fashiongo.webadmin.model.pojo;
/**
 * 
 * @author Incheol Jung
 */
public class PurchasedOrderStatic {
	private Integer totalPurchasedOrders;
    private Double rate;
    
    public Integer getTotalPurchasedOrders() {
		return totalPurchasedOrders;
	}
	public void setTotalPurchasedOrders(Integer totalPurchasedOrders) {
		this.totalPurchasedOrders = totalPurchasedOrders;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
}