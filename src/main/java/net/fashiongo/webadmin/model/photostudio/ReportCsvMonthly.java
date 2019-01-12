package net.fashiongo.webadmin.model.photostudio;

import java.math.BigDecimal;

import javax.persistence.Column;

public class ReportCsvMonthly {

	@Column(name = "PhotoshootDate")
	private String photoshootDate;
	public String getPhotoshootDate() {
		return photoshootDate;
	}

	@Column(name = "Vendor")
	private String wholeSalerCompanyName;
	public String getWholeSalerCompanyName() {
		return wholeSalerCompanyName;
	}
	
	@Column(name = "OrderAmount")
	private BigDecimal orderAmount;
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}
	
	@Column(name = "Daily Total Order Amounts")
	private BigDecimal dailyOrderAmount;
	public BigDecimal getDailyOrderAmount() {
		return dailyOrderAmount;
	}

	@Column(name = "Daily # of Orders")
	private Integer dailyOrders;
	public Integer getDailyOrders() {
		return dailyOrders;
	}

	@Column(name = "Avg Daily Order Amounts")
	private BigDecimal avgDailyOrderAmounts;
	public BigDecimal getAvgDailyOrderAmounts() {
		return avgDailyOrderAmounts;
	}

	@Column(name = "Units")
	private BigDecimal units;
	public BigDecimal getUnits() {
		return units;
	}

	@Column(name = "Daily Total Units")
	private BigDecimal dailyTotalUnits;
	public BigDecimal getDailyTotalUnits() {
		return dailyTotalUnits;
	}

	@Column(name = "styleQty")
	private Integer styleQty;
	public Integer getStyleQty() {
		return styleQty;
	}
	
	@Column(name = "Daily Total Styles")
	private Integer dailyTotalStyles;
	public Integer getDailyTotalStyles() {
		return dailyTotalStyles;
	}
}
