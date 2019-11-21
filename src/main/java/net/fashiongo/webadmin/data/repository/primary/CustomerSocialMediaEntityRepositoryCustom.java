package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.CustomerSocialMediaEntity;

public interface CustomerSocialMediaEntityRepositoryCustom {
    CustomerSocialMediaEntity findOneByMapID(Integer mapID);
}
