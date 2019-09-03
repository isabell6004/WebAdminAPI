package net.fashiongo.webadmin.data.model.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class TodayDealCalendarDetail {

	@JsonProperty("TodayDealID")
	private Integer todayDealID;
	@JsonProperty("Dom")
	private Integer dom;
	@JsonProperty("CompanyName")
	private String companyName;
	@JsonProperty("FromDate")
	private LocalDate fromDate;
	@JsonProperty("Active")
	private Boolean active;
	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;
	@JsonProperty("CreatedByVendor")
	private Boolean createdByVendor;

	public TodayDealCalendarDetail(Integer todayDealID, Integer dom, String companyName, Timestamp fromDate, Boolean active, Integer wholeSalerID, Boolean createdByVendor) {
		this.todayDealID = todayDealID;
		this.dom = dom;
		this.companyName = companyName;
		this.fromDate = fromDate.toLocalDateTime().toLocalDate();
		this.active = active;
		this.wholeSalerID = wholeSalerID;
		this.createdByVendor = createdByVendor;
	}
}
