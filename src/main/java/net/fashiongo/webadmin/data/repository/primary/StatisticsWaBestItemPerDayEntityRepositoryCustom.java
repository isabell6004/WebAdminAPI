package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.statistics.BestItems;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticsWaBestItemPerDayEntityRepositoryCustom {

    @Deprecated
    List<BestItems> getBestItems(Integer pageNo, Integer pageSize, LocalDateTime fromDate, LocalDateTime toDate,
                                 Integer statisticsType, Integer lastCategoryID, Integer wholeSalerId, String orderBy);

    List<BestItems> selectBestItems(Integer pageNo, Integer pageSize, LocalDateTime fromDate, LocalDateTime toDate,
                                 Integer statisticsType, Integer lastCategoryID, Integer wholeSalerId);
}
