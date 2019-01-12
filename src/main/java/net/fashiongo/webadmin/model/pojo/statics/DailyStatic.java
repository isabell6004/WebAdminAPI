package net.fashiongo.webadmin.model.pojo.statics;

import java.sql.Date;

import javax.persistence.Column;

/**
 * 
 * @author Incheol Jung
 */
public class DailyStatic {
	@Column(name = "VisitorsYMD")
	private Date period;
	@Column(name = "VisitorsDayCount")
	private Integer value;
	public Date getPeriod() {
		return period;
	}
	public void setPeriod(Date period) {
		this.period = period;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
}
