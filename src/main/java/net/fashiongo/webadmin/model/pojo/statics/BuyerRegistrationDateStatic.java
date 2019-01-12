package net.fashiongo.webadmin.model.pojo.statics;

import javax.persistence.Column;

/**
 * 
 * @author Incheol Jung
 */
public class BuyerRegistrationDateStatic {
	@Column(name = "TodayBuyerRegCount")
	private Integer todayBuyerRegistrationCount;
	@Column(name = "YesterdayBuyerRegCount")
	private Integer yesterdayBuyerRegistrationCount;
	
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
