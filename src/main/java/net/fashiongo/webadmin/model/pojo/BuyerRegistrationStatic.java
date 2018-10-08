package net.fashiongo.webadmin.model.pojo;
/**
 * 
 * @author Incheol Jung
 */
public class BuyerRegistrationStatic {
	private Integer todayBuyerRegistrationCount;
	private Integer yesterdayBuyerRegistrationCount;
	private Integer firstTimePurchaseCount;
	private Double rate;
	
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
	public Integer getFirstTimePurchaseCount() {
		return firstTimePurchaseCount;
	}
	public void setFirstTimePurchaseCount(Integer firstTimePurchaseCount) {
		this.firstTimePurchaseCount = firstTimePurchaseCount;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
}
