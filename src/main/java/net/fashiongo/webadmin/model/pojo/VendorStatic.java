package net.fashiongo.webadmin.model.pojo;
/**
 * 
 * @author Incheol Jung
 */

import java.util.List;

public class VendorStatic {
	private MonthVendorStatic newVendors;
	private MonthVendorStatic uploadedItems;
	private List<MonthlyStatic> vendorsMonthly;
	private Count orderNumOfVendorCount;
	private MonthVendorStatic closedVendors;
	private Avg avgVendorDuration;
	public MonthVendorStatic getNewVendors() {
		return newVendors;
	}
	public void setNewVendors(MonthVendorStatic newVendors) {
		this.newVendors = newVendors;
	}
	public MonthVendorStatic getUploadedItems() {
		return uploadedItems;
	}
	public void setUploadedItems(MonthVendorStatic uploadedItems) {
		this.uploadedItems = uploadedItems;
	}
	public List<MonthlyStatic> getVendorsMonthly() {
		return vendorsMonthly;
	}
	public void setVendorsMonthly(List<MonthlyStatic> vendorsMonthly) {
		this.vendorsMonthly = vendorsMonthly;
	}
	public Count getOrderNumOfVendorCount() {
		return orderNumOfVendorCount;
	}
	public void setOrderNumOfVendorCount(Count orderNumOfVendorCount) {
		this.orderNumOfVendorCount = orderNumOfVendorCount;
	}
	public MonthVendorStatic getClosedVendors() {
		return closedVendors;
	}
	public void setClosedVendors(MonthVendorStatic closedVendors) {
		this.closedVendors = closedVendors;
	}
	public Avg getAvgVendorDuration() {
		return avgVendorDuration;
	}
	public void setAvgVendorDuration(Avg avgVendorDuration) {
		this.avgVendorDuration = avgVendorDuration;
	}
}
