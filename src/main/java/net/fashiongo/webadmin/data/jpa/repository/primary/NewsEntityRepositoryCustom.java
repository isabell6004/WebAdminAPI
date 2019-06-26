package net.fashiongo.webadmin.data.jpa.repository.primary;

import net.fashiongo.webadmin.data.jpa.entity.primary.NewsEntity;

public interface NewsEntityRepositoryCustom {
    NewsEntity findOneByNewsID(Integer newsID);
}
