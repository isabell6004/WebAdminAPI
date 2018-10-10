/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.response;

import net.fashiongo.webadmin.model.pojo.BuyerStatic;
import net.fashiongo.webadmin.model.pojo.OrderStatic;
import net.fashiongo.webadmin.model.pojo.VendorStatic;
import net.fashiongo.webadmin.model.pojo.VisitorStatic;

/**
 * @author Incheol Jung
 */
public class GetDashboardResponse {
	private VisitorStatic visitors;
	private BuyerStatic buyers;
	private OrderStatic orders;
	private VendorStatic vendors;
	public VisitorStatic getVisitors() {
		return visitors;
	}
	public void setVisitors(VisitorStatic visitors) {
		this.visitors = visitors;
	}
	public BuyerStatic getBuyers() {
		return buyers;
	}
	public void setBuyers(BuyerStatic buyers) {
		this.buyers = buyers;
	}
	public OrderStatic getOrders() {
		return orders;
	}
	public void setOrders(OrderStatic orders) {
		this.orders = orders;
	}
	public VendorStatic getVendors() {
		return vendors;
	}
	public void setVendors(VendorStatic vendors) {
		this.vendors = vendors;
	}
}
