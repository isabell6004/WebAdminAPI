package net.fashiongo.webadmin.dao.primary.show;

import java.util.List;

public interface ShowSchedulePromotionPlanRepositoryCustom {

    List<Integer> getShowParticipatingVendorIds(Integer showScheduleId);
}
