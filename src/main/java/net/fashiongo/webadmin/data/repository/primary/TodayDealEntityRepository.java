package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.TodayDealEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodayDealEntityRepository extends JpaRepository<TodayDealEntity, Integer>, TodayDealEntityRepositoryCustom {
}
