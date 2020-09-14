package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.NewsEntity;

public interface NewsEntityRepositoryCustom {
    NewsEntity getActiveNews(Integer consolidationId, Integer orderId);
}
