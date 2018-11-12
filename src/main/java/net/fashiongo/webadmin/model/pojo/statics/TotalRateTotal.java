package net.fashiongo.webadmin.model.pojo.statics;

import java.math.BigDecimal;

import javax.persistence.Column;

/**
 * 
 * @author Incheol Jung
 */
public class TotalRateTotal {
	@Column(name = "TotalVisitorsCount")
	private Integer total;
	@Column(name = "TotalVisitorsRate")
	private BigDecimal rate;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
}