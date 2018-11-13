package net.fashiongo.webadmin.model.pojo.statics;

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
		return month.substring(4, 6);
	}
	public String getYear() {
		return month.substring(0, 4);
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
