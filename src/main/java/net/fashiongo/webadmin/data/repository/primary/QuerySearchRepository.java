package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.statistics.HotSearch;

import java.time.LocalDateTime;
import java.util.List;

public interface QuerySearchRepository {

    List<HotSearch> getHotSearch(Integer top, LocalDateTime fromDate, LocalDateTime toDate, String orderBy, String searchfield, String searchkeyword);
}
