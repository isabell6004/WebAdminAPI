package net.fashiongo.webadmin.data.repository.stats;

import net.fashiongo.webadmin.data.model.statistics.HotSearchKeyword;

import java.time.LocalDateTime;
import java.util.List;

public interface SearchEntityRepositoryCustom {
    List<HotSearchKeyword> getHotSearchKeyword(Integer periodType, LocalDateTime fromDate, LocalDateTime toDate, String keyword);
}
