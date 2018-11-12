/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.statics.response;

import net.fashiongo.webadmin.model.pojo.statics.BuyerStatic;
import net.fashiongo.webadmin.model.pojo.statics.OrderStatic;
import net.fashiongo.webadmin.model.pojo.statics.VendorStatic;
import net.fashiongo.webadmin.model.pojo.statics.VisitorStatic;

/**
 * @author Incheol Jung
 */
public class GetDashboardResponse {
	
	public GetDashboardResponse(){
		this.visitors = new VisitorStatic();
		this.buyers = new BuyerStatic();
		this.orders = new OrderStatic();
		this.vendors = new VendorStatic();
	}
	
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