package net.fashiongo.webadmin.dao.primary.custom;

import net.fashiongo.webadmin.model.primary.NewsRecipient;

public interface NewsEntityRepositoryCustom {
    NewsRecipient findOneByNewsID(Integer newsID);
}
