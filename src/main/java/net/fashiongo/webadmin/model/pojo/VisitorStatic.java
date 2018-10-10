package net.fashiongo.webadmin.model.pojo;

import java.util.List;
/**
 * 
 * @author Incheol Jung
 */
public class VisitorStatic {
	private TotalRate totalVisitors;
	private TotalRate avgVisitors;
	private List<YearlyStatic> visitorsYearly;
	private List<MonthlyStatic> visitorsMonthly;
	private List<DailyStatic> visitorsDaily;
	public TotalRate getTotalVisitors() {
		return totalVisitors;
	}
	public void setTotalVisitors(TotalRate totalVisitors) {
		this.totalVisitors = totalVisitors;
	}
	public TotalRate getAvgVisitors() {
		return avgVisitors;
	}
	public void setAvgVisitors(TotalRate avgVisitors) {
		this.avgVisitors = avgVisitors;
	}
	public List<YearlyStatic> getVisitorsYearly() {
		return visitorsYearly;
	}
	public void setVisitorsYearly(List<YearlyStatic> visitorsYearly) {
		this.visitorsYearly = visitorsYearly;
	}
	public List<MonthlyStatic> getVisitorsMonthly() {
		return visitorsMonthly;
	}
	public void setVisitorsMonthly(List<MonthlyStatic> visitorsMonthly) {
		this.visitorsMonthly = visitorsMonthly;
	}
	public List<DailyStatic> getVisitorsDaily() {
		return visitorsDaily;
	}
	public void setVisitorsDaily(List<DailyStatic> visitorsDaily) {
		this.visitorsDaily = visitorsDaily;
	}
}