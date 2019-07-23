package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.TrendDailyKeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TrendDailyKeywordEntityRepository extends JpaRepository<TrendDailyKeywordEntity, Long>, TrendDailyKeywordEntityRepositoryCustom {
    List<TrendDailyKeywordEntity> findAllByExposeDate(LocalDateTime exposeDate);

    List<TrendDailyKeywordEntity> findAllByExposeDateIn(List<LocalDateTime> exposeDateList);

    void deleteByTrendDailyKeywordID(long trendDailyKeywordID);
}
