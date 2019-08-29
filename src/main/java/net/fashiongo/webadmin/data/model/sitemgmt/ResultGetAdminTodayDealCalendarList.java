package net.fashiongo.webadmin.data.model.sitemgmt;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ResultGetAdminTodayDealCalendarList {
    List<ActiveTodayDealDetail> activeTodayDealDetails;

    List<InactiveTodayDealDetail> inactiveTodayDealDetails;
}
