package net.fashiongo.webadmin.model.pojo;

import java.math.BigDecimal;

/**
 * 
 * @author Incheol Jung
 */
public class BuyerRegistrationStatic {
	public BuyerRegistrationStatic() {}
	
	public BuyerRegistrationStatic(BuyerRegistrationDateStatic buyerRegistrationDateStatic, BuyerFirtstTimeStatic buyerFirtstTimeStatic) {
		this.setTodayBuyerRegistrationCount(buyerRegistrationDateStatic.getTodayBuyerRegistrationCount());
		this.setYesterdayBuyerRegistrationCount(buyerRegistrationDateStatic.getYesterdayBuyerRegistrationCount());
		this.setFirstTimePurchaseCount(buyerFirtstTimeStatic.getFirstTimePurchaseCount());
		this.setRate(buyerFirtstTimeStatic.getRate());
	}
	
	private Integer todayBuyerRegistrationCount;
	private Integer yesterdayBuyerRegistrationCount;
	private Integer firstTimePurchaseCount;
	private BigDecimal rate;
	
	public Integer getFirstTimePurchaseCount() {
		return firstTimePurchaseCount;
	}
	public void setFirstTimePurchaseCount(Integer firstTimePurchaseCount) {
		this.firstTimePurchaseCount = firstTimePurchaseCount;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public Integer getTodayBuyerRegistrationCount() {
		return todayBuyerRegistrationCount;
	}
	public void setTodayBuyerRegistrationCount(Integer todayBuyerRegistrationCount) {
		this.todayBuyerRegistrationCount = todayBuyerRegistrationCount;
	}
	public Integer getYesterdayBuyerRegistrationCount() {
		return yesterdayBuyerRegistrationCount;
	}
	public void setYesterdayBuyerRegistrationCount(Integer yesterdayBuyerRegistrationCount) {
		this.yesterdayBuyerRegistrationCount = yesterdayBuyerRegistrationCount;
	}
}
