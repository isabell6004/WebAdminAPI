package net.fashiongo.webadmin.model.pojo;

import javax.persistence.Column;

/**
 * 
 * @author Incheol Jung
 */
public class MonthlyBuyerStatic{
	@Column(name = "RegYM")
	private String month;
	@Column(name = "BuyerRegCount")
	private Integer value;
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	
}