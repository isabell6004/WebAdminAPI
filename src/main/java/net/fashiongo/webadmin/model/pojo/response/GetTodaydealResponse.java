package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.TodayDealDetail;
import net.fashiongo.webadmin.model.pojo.Total;

public class GetTodaydealResponse {
	@JsonProperty("Table")
	private Total total;
	@JsonProperty("Table1")
	private List<TodayDealDetail> todayDealDetail;
	public Total getTotal() {
		return total;
	}
	public void setTotal(Total total) {
		this.total = total;
	}
	public List<TodayDealDetail> getTodayDealDetail() {
		return todayDealDetail;
	}
	public void setTodayDealDetail(List<TodayDealDetail> todayDealDetail) {
		this.todayDealDetail = todayDealDetail;
	}
}
