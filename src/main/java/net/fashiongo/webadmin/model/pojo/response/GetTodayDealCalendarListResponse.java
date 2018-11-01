package net.fashiongo.webadmin.model.pojo.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.ActiveTodayDealDetail;
import net.fashiongo.webadmin.model.pojo.InactiveTodayDealDetail;

public class GetTodayDealCalendarListResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("Table")
	private List<ActiveTodayDealDetail> activeTodayDeals;
	@JsonProperty("Table1")
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