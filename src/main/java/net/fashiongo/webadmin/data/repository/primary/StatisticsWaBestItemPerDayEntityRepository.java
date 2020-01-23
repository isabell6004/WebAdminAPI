package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.StatisticsWaBestItemPerDayEntity;
import net.fashiongo.webadmin.data.entity.primary.StatisticsWaBestItemPerDayEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsWaBestItemPerDayEntityRepository extends JpaRepository<StatisticsWaBestItemPerDayEntity, StatisticsWaBestItemPerDayEntityPK>, StatisticsWaBestItemPerDayEntityRepositoryCustom {
}
