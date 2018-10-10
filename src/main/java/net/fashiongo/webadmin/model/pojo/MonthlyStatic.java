package net.fashiongo.webadmin.model.pojo;

import javax.persistence.Column;

/**
 * 
 * @author Incheol Jung
 */
public class MonthlyStatic{
	@Column(name = "VisitorsYM")
	private String month;
	@Column(name = "VisitorsMonthCount")
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