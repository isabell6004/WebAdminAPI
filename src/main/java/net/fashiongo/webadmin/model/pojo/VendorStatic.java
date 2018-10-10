package net.fashiongo.webadmin.model.pojo;
/**
 * 
 * @author Incheol Jung
 */

import java.util.List;

public class VendorStatic {
	private MonthNewVendorStatic newVendors;
	private MonthUploadItemStatic uploadedItems;
	private List<MonthlyStatic> vendorsMonthly;
	private Count orderNumOfVendorCount;
	private MonthClosedVendorStatic closedVendors;
	private Avg avgVendorDuration;
	
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
	public MonthNewVendorStatic getNewVendors() {
		return newVendors;
	}
	public void setNewVendors(MonthNewVendorStatic newVendors) {
		this.newVendors = newVendors;
	}
	public MonthUploadItemStatic getUploadedItems() {
		return uploadedItems;
	}
	public void setUploadedItems(MonthUploadItemStatic uploadedItems) {
		this.uploadedItems = uploadedItems;
	}
	public MonthClosedVendorStatic getClosedVendors() {
		return closedVendors;
	}
	public void setClosedVendors(MonthClosedVendorStatic closedVendors) {
		this.closedVendors = closedVendors;
	}
	public Avg getAvgVendorDuration() {
		return avgVendorDuration;
	}
	public void setAvgVendorDuration(Avg avgVendorDuration) {
		this.avgVendorDuration = avgVendorDuration;
	}
}
