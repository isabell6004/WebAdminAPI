package net.fashiongo.webadmin.model.pojo.sitemgmt.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.sitemgmt.TodayDealCalendarDetail;
import net.fashiongo.webadmin.model.pojo.sitemgmt.VendorSummaryDetail;

public class GetTodayDealCalendarResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	@JsonProperty("Table")
	private List<TodayDealCalendarDetail> calendarDetails;
	@JsonProperty("Table1")
	private List<VendorSummaryDetail> vendors;
	
	public List<TodayDealCalendarDetail> getCalendarDetails() {
		return calendarDetails;
	}
	public void setCalendarDetails(List<TodayDealCalendarDetail> calendarDetails) {
		this.calendarDetails = calendarDetails;
	}
	public List<VendorSummaryDetail> getVendors() {
		return vendors;
	}
	public void setVendors(List<VendorSummaryDetail> vendors) {
		this.vendors = vendors;
	}
}
