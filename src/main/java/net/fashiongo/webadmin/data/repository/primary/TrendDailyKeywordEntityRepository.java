package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.TrendDailyKeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrendDailyKeywordEntityRepository extends JpaRepository<TrendDailyKeywordEntity, Integer>, TrendDailyKeywordEntityRepositoryCustom {
    void deleteByTrendDailyKeywordID(long trendDailyKeywordID);
}
