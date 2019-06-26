package net.fashiongo.webadmin.dao.primary.custom;

import net.fashiongo.webadmin.model.primary.NewsEntity;

public interface NewsEntityRepositoryCustom {
    NewsEntity findOneByNewsID(Integer newsID);
}
