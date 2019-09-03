package net.fashiongo.webadmin.data.repository.primary.procedure;

import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.sitemgmt.TodayDealCalendarDetail;
import net.fashiongo.webadmin.data.model.sitemgmt.VendorSummaryDetail;

import java.util.List;

@Getter
@Builder
public class GetAdminTodayDealCalendarResult {

	private List<TodayDealCalendarDetail> calendarDetails;

	private List<VendorSummaryDetail> vendors;
}
