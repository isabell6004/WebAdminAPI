package net.fashiongo.webadmin.model.pojo;
/**
 * 
 * @author Incheol Jung
 */

import javax.persistence.Column;

public class YearlyStatic {
	@Column(name = "VisitorsYYYY")
	private String year;
	@Column(name = "VisitorsYearCount")
	private Integer value;
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	
}
