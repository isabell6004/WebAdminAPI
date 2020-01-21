package net.fashiongo.webadmin.data.repository.primary.vendor;

import net.fashiongo.webadmin.data.model.vendor.ListSocialMedia;

import java.util.List;

public interface ListSocialMediaEntityRepositoryCustom {
    List<ListSocialMedia> findSocialMediaByWholeSalerID(Integer wholeSalerID);

    long deleteByIds(List<Integer> ids);
}
