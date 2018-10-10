package net.fashiongo.webadmin.model.pojo;

import java.math.BigDecimal;

import javax.persistence.Column;

/**
 * 
 * @author Incheol Jung
 */
public class MonthlyVendorStatic{
	@Column(name = "DateYM")
	private String month;
	@Column(name = "PurchaseAmount")
	private BigDecimal value;
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
}
