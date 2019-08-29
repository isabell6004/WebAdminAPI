package net.fashiongo.webadmin.data.repository.primary.show;

import net.fashiongo.webadmin.data.entity.primary.show.ShowScheduleWithPromotionEntity;
import org.springframework.data.repository.CrudRepository;

public interface ShowScheduleWithPromotionEntityRepository extends CrudRepository<ShowScheduleWithPromotionEntity, Integer>, ShowScheduleWithPromotionEntityRepositoryCustom {
}
