package net.fashiongo.webadmin.model.pojo;
/**
 * 
 * @author Incheol Jung
 */
public class OrderStatic {
	private PurchasedOrderStatic purchasedOrders;
	private PoOrderStatic poOrders;
	private AvgPOOrderStatic avgPOOrders;
	private OrderMonthlyStatic orderMonthly;
	public PurchasedOrderStatic getPurchasedOrders() {
		return purchasedOrders;
	}
	public void setPurchasedOrders(PurchasedOrderStatic purchasedOrders) {
		this.purchasedOrders = purchasedOrders;
	}
	public PoOrderStatic getPoOrders() {
		return poOrders;
	}
	public void setPoOrders(PoOrderStatic poOrders) {
		this.poOrders = poOrders;
	}
	public AvgPOOrderStatic getAvgPOOrders() {
		return avgPOOrders;
	}
	public void setAvgPOOrders(AvgPOOrderStatic avgPOOrders) {
		this.avgPOOrders = avgPOOrders;
	}
	public OrderMonthlyStatic getOrderMonthly() {
		return orderMonthly;
	}
	public void setOrderMonthly(OrderMonthlyStatic orderMonthly) {
		this.orderMonthly = orderMonthly;
	}
}
