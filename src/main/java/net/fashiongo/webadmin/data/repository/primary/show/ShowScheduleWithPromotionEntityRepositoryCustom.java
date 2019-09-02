package net.fashiongo.webadmin.data.repository.primary.show;

import net.fashiongo.webadmin.data.entity.primary.show.ShowPromotionWholesalerJoinRow;
import net.fashiongo.webadmin.data.entity.primary.show.ShowScheduleWithPromotionEntity;
import org.springframework.data.domain.Page;

public interface ShowScheduleWithPromotionEntityRepositoryCustom {
	Page<ShowScheduleWithPromotionEntity> getShowSchedules(ScheduleSelectParameter param);

	Page<ShowPromotionWholesalerJoinRow> getPromotionVendor(PromotionVendorSelectParameter parameter);
}
