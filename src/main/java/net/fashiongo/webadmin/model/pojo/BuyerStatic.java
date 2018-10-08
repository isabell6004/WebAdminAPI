package net.fashiongo.webadmin.model.pojo;

import java.util.List;

/**
 * 
 * @author Incheol Jung
 */
public class BuyerStatic {
	private ActiveBuyerStatic activeBuyes;
	private PurchasingBuyerStatic purchasingBuyers;
	private BuyerRegistrationStatic buyerRegistration;
	private List<MonthlyStatic> buyerRegMonthly;
	private DailyStatic buyerPOMonthly;
	
	public ActiveBuyerStatic getActiveBuyes() {
		return activeBuyes;
	}
	public void setActiveBuyes(ActiveBuyerStatic activeBuyes) {
		this.activeBuyes = activeBuyes;
	}
	public PurchasingBuyerStatic getPurchasingBuyers() {
		return purchasingBuyers;
	}
	public void setPurchasingBuyers(PurchasingBuyerStatic purchasingBuyers) {
		this.purchasingBuyers = purchasingBuyers;
	}
	public BuyerRegistrationStatic getBuyerRegistration() {
		return buyerRegistration;
	}
	public void setBuyerRegistration(BuyerRegistrationStatic buyerRegistration) {
		this.buyerRegistration = buyerRegistration;
	}
	public List<MonthlyStatic> getBuyerRegMonthly() {
		return buyerRegMonthly;
	}
	public void setBuyerRegMonthly(List<MonthlyStatic> buyerRegMonthly) {
		this.buyerRegMonthly = buyerRegMonthly;
	}
	public DailyStatic getBuyerPOMonthly() {
		return buyerPOMonthly;
	}
	public void setBuyerPOMonthly(DailyStatic buyerPOMonthly) {
		this.buyerPOMonthly = buyerPOMonthly;
	}
}
