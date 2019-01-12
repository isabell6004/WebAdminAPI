package net.fashiongo.webadmin.model.pojo.statics;

import java.util.List;

/**
 * 
 * @author Incheol Jung
 */
public class BuyerStatic {
	private ActiveBuyerStatic activeBuyers;
	private PurchasingBuyerStatic purchasingBuyers;
	private BuyerRegistrationStatic buyerRegistration;
	private List<MonthlyBuyerStatic> buyerRegMonthly;
	private List<DailyBuyerStatic> buyerPOMonthly;
	
	public ActiveBuyerStatic getActiveBuyers() {
		return activeBuyers;
	}
	public void setActiveBuyers(ActiveBuyerStatic activeBuyers) {
		this.activeBuyers = activeBuyers;
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
	public List<MonthlyBuyerStatic> getBuyerRegMonthly() {
		return buyerRegMonthly;
	}
	public void setBuyerRegMonthly(List<MonthlyBuyerStatic> buyerRegMonthly) {
		this.buyerRegMonthly = buyerRegMonthly;
	}
	public List<DailyBuyerStatic> getBuyerPOMonthly() {
		return buyerPOMonthly;
	}
	public void setBuyerPOMonthly(List<DailyBuyerStatic> buyerPOMonthly) {
		this.buyerPOMonthly = buyerPOMonthly;
	}
}
