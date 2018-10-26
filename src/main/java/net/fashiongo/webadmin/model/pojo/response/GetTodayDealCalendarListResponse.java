package net.fashiongo.webadmin.model.pojo.response;

import java.io.Serializable;
import java.util.List;

import net.fashiongo.webadmin.model.pojo.ActiveTodayDealDetail;
import net.fashiongo.webadmin.model.pojo.InactiveTodayDealDetail;

public class GetTodayDealCalendarListResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<ActiveTodayDealDetail> activeTodayDeals;
	private List<InactiveTodayDealDetail> inactiveTodayDeals;
	
	public List<ActiveTodayDealDetail> getActiveTodayDeals() {
		return activeTodayDeals;
	}
	public void setActiveTodayDeals(List<ActiveTodayDealDetail> activeTodayDeals) {
		this.activeTodayDeals = activeTodayDeals;
	}
	public List<InactiveTodayDealDetail> getInactiveTodayDeals() {
		return inactiveTodayDeals;
	}
	public void setInactiveTodayDeals(List<InactiveTodayDealDetail> inactiveTodayDeals) {
		this.inactiveTodayDeals = inactiveTodayDeals;
	}

	
}