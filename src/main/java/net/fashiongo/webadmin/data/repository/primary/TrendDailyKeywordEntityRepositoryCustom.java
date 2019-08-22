package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.TrendDailyKeywordEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface TrendDailyKeywordEntityRepositoryCustom {
    List<TrendDailyKeywordEntity> findAllBetweenFromTo(LocalDateTime fromDate, LocalDateTime toDate);

    TrendDailyKeywordEntity findOneById(Long id);
}
